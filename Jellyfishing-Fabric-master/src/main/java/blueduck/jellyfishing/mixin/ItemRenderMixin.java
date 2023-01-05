package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.client.ClientUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(ItemRenderer.class)
public abstract class ItemRenderMixin {
//    @Shadow @Final private ItemModels models;
//    @Shadow public abstract BakedModel getHeldItemModel(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed);
//
//    @Redirect(method = "innerRenderInGui(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;IIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;getHeldItemModel(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)Lnet/minecraft/client/render/model/BakedModel;"))
//    public BakedModel getPerspectiveGuiModel(ItemRenderer itemRenderer, ItemStack stack, World world, LivingEntity entity, int seed) {
//        if (ClientUtil.tridentPerspectiveItemList.contains(stack.getItem()) || ClientUtil.leftRightPerspectiveItemList.contains(stack.getItem())) {
//            var guiId = new ModelIdentifier(Registry.ITEM.getId(stack.getItem()), "inventory");
//            var clientWorld = world instanceof ClientWorld ? (ClientWorld) world : null;
//            var model = models.getModelManager().getModel(guiId);
//            var model2 = model.getOverrides().apply(model, stack, clientWorld, entity, seed);
//            return model2 == null ? models.getModelManager().getMissingModel() : model2;
//        }
//        return this.getHeldItemModel(stack, world, entity, seed);
//    }
//
//    @Redirect(method = "getHeldItemModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;"))
//    public BakedModel getTridentPerspectiveInHandModel(ItemModels itemModels, ItemStack stack) {
//        if (ClientUtil.tridentPerspectiveItemList.contains(stack.getItem())) {
//            var invID = new ModelIdentifier(Registry.ITEM.getId(stack.getItem()) + "_in_hand", "inventory");
//            return itemModels.getModelManager().getModel(invID);
//        }
//        return itemModels.getModel(stack);
//    }

//    //Lnet/minecraft/client/render/model/json/ModelTransformation/Mode;
//    @Redirect(method = "getHeldItemModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;"))
//    public BakedModel getLeftRightPerspectiveInHandModel(ItemModels itemModels, ItemStack stack, ModelTransformation.Mode mode) {
//        if (ClientUtil.leftRightPerspectiveItemList.contains(stack.getItem())) {
//            if(mode == ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND || mode == ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND) {
//                var invID = new ModelIdentifier(Registry.ITEM.getId(stack.getItem()) + "_left", "inventory");
//                return itemModels.getModelManager().getModel(invID);
//            } else {
//                if (mode == ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND || mode == ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND) {
//                    var invID = new ModelIdentifier(Registry.ITEM.getId(stack.getItem()) + "_right", "inventory");
//                    return itemModels.getModelManager().getModel(invID);
//                }
//            }
//        }
//        return itemModels.getModel(stack);
//    }
}