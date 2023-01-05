package com.jellyfishing.core.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin({ItemRenderer.class})
public abstract class ItemRenderMixin {
    @Shadow
    @Final
    private ItemModelShaper field_4732;

    public ItemRenderMixin() {
    }

    @Shadow
    public abstract BakedModel method_4019(ItemStack var1, @Nullable World var2, @Nullable LivingEntity var3, int var4);

    @Redirect(
            method = {"innerRenderInGui(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;IIII)V"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemRenderer;getHeldItemModel(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)Lnet/minecraft/client/render/model/BakedModel;"
            )
    )
    public BakedModel innerRenderInGui(ItemRenderer itemRenderer, ItemStack stack, World world, LivingEntity entity, int seed) {
        if (ClientUtil.itemList.contains(stack.getItem())) {
            ModelIdentifier guiId = new ModelIdentifier(Registry.ITEM.getId(stack.getItem()), "inventory");
            ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
            BakedModel model = this.field_4732.getModelManager().getModel(guiId);
            BakedModel model2 = model.getOverrides().apply(model, stack, clientWorld, entity, seed);
            return model2 == null ? this.field_4732.getModelManager().getMissingModel() : model2;
        } else {
            return this.method_4019(stack, world, entity, seed);
        }
    }

    @Redirect(
            method = {"getHeldItemModel"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;"
            )
    )
    public BakedModel getInventoryModel(ItemModels itemModels, ItemStack stack) {
        if (ClientUtil.itemList.contains(stack.getItem())) {
            DefaultedRegistry var10002 = Registry.ITEM;
            ModelIdentifier invID = new ModelIdentifier(var10002.getId(stack.getItem()) + "_in_hand", "inventory");
            return itemModels.getModelManager().getModel(invID);
        } else {
            return itemModels.getModel(stack);
        }
    }
}
