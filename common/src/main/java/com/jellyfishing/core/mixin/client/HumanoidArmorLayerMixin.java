package com.jellyfishing.core.mixin.client;

import com.jellyfishing.client.model.SuitModel;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.util.ArmorRenderer;
import com.jellyfishing.core.util.ArmorRendererRegistryImpl;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin extends RenderLayer<LivingEntity, HumanoidModel<LivingEntity>> {
    public HumanoidArmorLayerMixin(RenderLayerParent<LivingEntity, HumanoidModel<LivingEntity>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Inject(method = "getArmorLocation", at = @At("HEAD"), cancellable = true)
    private void jellyfishing$getArmorLocation(ArmorItem armorItem, boolean bl, String string, CallbackInfoReturnable<ResourceLocation> cir) {
//        if (armorItem.getMaterial().equals(JellyfishingItems.SUIT_MATERIAL)) {
//            if (armorItem.getSlot().equals(EquipmentSlot.LEGS)) {
//                cir.setReturnValue(Jellyfishing.id("textures/models/armor/air_suit_legs.png"));
//            } else {
//                cir.setReturnValue(Jellyfishing.id("textures/models/armor/air_suit.png"));
//            }
//        }
    }

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    private void renderArmor(PoseStack matrices, MultiBufferSource vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, HumanoidModel<LivingEntity> model, CallbackInfo ci) {
        ItemStack stack = entity.getItemBySlot(armorSlot);
        ArmorRenderer renderer = ArmorRendererRegistryImpl.get(stack.getItem());

        if (renderer != null) {
            renderer.render(matrices, vertexConsumers, stack, entity, armorSlot, light, getParentModel());
            ci.cancel();
        }
    }
}