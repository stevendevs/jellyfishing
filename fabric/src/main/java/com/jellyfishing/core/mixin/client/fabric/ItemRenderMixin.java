package com.jellyfishing.core.mixin.client.fabric;

import com.jellyfishing.core.util.fabric.ClientUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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
    @Shadow
    @Final
    private ItemModelShaper itemModelShaper;

    public ItemRenderMixin() {
    }

    @Shadow
    public abstract BakedModel getModel(ItemStack var1, @Nullable Level var2, @Nullable LivingEntity var3, int var4);

    @Redirect(
            method = {"tryRenderGuiItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;IIII)V"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;getModel(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)Lnet/minecraft/client/resources/model/BakedModel;"
            )
    )
    public BakedModel innerRenderInGui(ItemRenderer itemRenderer, ItemStack stack, Level world, LivingEntity entity, int seed) {
        if (ClientUtil.PERSPECTIVE_ITEM_LIST.contains(stack.getItem())) {
            ModelResourceLocation guiId = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(stack.getItem()), "inventory");
            ClientLevel clientWorld = world instanceof ClientLevel ? (ClientLevel)world : null;
            BakedModel model = this.itemModelShaper.getModelManager().getModel(guiId);
            BakedModel model2 = model.getOverrides().resolve(model, stack, clientWorld, entity, seed);
            return model2 == null ? this.itemModelShaper.getModelManager().getMissingModel() : model2;
        } else {
            return this.getModel(stack, world, entity, seed);
        }
    }

    @Redirect(
            method = {"getModel"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"
            )
    )
    public BakedModel getInventoryModel(ItemModelShaper itemModels, ItemStack stack) {
        if (ClientUtil.PERSPECTIVE_ITEM_LIST.contains(stack.getItem())) {
            ModelResourceLocation invID = new ModelResourceLocation(new ResourceLocation(BuiltInRegistries.ITEM.getKey(stack.getItem()) + "_in_hand"), "inventory");
            return itemModels.getModelManager().getModel(invID);
        } else {
            return itemModels.getItemModel(stack);
        }
    }
}
