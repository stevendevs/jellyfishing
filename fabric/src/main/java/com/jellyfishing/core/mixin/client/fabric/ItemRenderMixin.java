package com.jellyfishing.core.mixin.client.fabric;

import com.jellyfishing.client.model.ModelPerspectiveRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Environment(EnvType.CLIENT)
@Mixin({ItemRenderer.class})
public abstract class ItemRenderMixin {
    @Shadow public abstract BakedModel getModel(ItemStack var1, @Nullable Level var2, @Nullable LivingEntity var3, int var4);
    @Shadow @Final private ItemModelShaper itemModelShaper;
    @Shadow public abstract void render(ItemStack itemStack, ItemTransforms.TransformType transformType, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, BakedModel model);

    @Redirect(method = "tryRenderGuiItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;IIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;getModel(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)Lnet/minecraft/client/resources/model/BakedModel;"))
    public BakedModel jellyfishing$renderGuiCustomModel(ItemRenderer itemRenderer, ItemStack stack, Level world, LivingEntity entity, int seed) {
        if (ModelPerspectiveRegistry.GUI_PERSPECTIVE_LIST.containsKey(stack.getItem())) {
            ModelResourceLocation guiId = ModelPerspectiveRegistry.getGuiModel(stack.getItem());
            ClientLevel clientWorld = world instanceof ClientLevel ? (ClientLevel)world : null;
            BakedModel model = this.itemModelShaper.getModelManager().getModel(guiId);
            BakedModel model2 = model.getOverrides().resolve(model, stack, clientWorld, entity, seed);
            return model2 == null ? this.itemModelShaper.getModelManager().getMissingModel() : model2;
        } else {
            return this.getModel(stack, world, entity, seed);
        }
    }

    @Redirect(method = "renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V"))
    public void jellyfishing$renderLeftRightCustomModel(ItemRenderer instance, ItemStack itemStack, ItemTransforms.TransformType transformType, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, BakedModel model) {
        if (leftHand && ModelPerspectiveRegistry.LEFT_PERSPECTIVE_LIST.containsKey(itemStack.getItem())) {
            ModelResourceLocation leftModel = ModelPerspectiveRegistry.getLeftModel(itemStack.getItem());
            this.render(itemStack, transformType, true, poseStack, buffer, combinedLight, combinedOverlay, itemModelShaper.getModelManager().getModel(leftModel));
        } else if (!leftHand && ModelPerspectiveRegistry.RIGHT_PERSPECTIVE_LIST.containsKey(itemStack.getItem())) {
            ModelResourceLocation rightModel = ModelPerspectiveRegistry.getRightModel(itemStack.getItem());
            this.render(itemStack, transformType, false, poseStack, buffer, combinedLight, combinedOverlay, itemModelShaper.getModelManager().getModel(rightModel));
        } else {
            this.render(itemStack, transformType, leftHand, poseStack, buffer, combinedLight, combinedOverlay, model);
        }
    }

    @Redirect(method = "getModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"))
    public BakedModel jellyfishing$renderHandCustomModel(ItemModelShaper itemModels, ItemStack stack) {
        if (ModelPerspectiveRegistry.HAND_PERSPECTIVE_LIST.containsKey(stack.getItem())) {
            ModelResourceLocation handModel = ModelPerspectiveRegistry.getHandModel(stack.getItem());
            return itemModels.getModelManager().getModel(handModel);
        } else {
            return itemModels.getItemModel(stack);
        }
    }
}
