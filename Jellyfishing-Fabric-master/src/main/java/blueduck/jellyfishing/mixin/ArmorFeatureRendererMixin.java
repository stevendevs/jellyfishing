package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.client.JellyfishingClient;
import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    @Final
    @Shadow
    private static Map<String, Identifier> ARMOR_TEXTURE_CACHE;

    @Shadow protected abstract void setVisible(A bipedModel, EquipmentSlot slot);

    @Shadow protected abstract boolean usesSecondLayer(EquipmentSlot slot);

    @Shadow protected abstract void renderArmorParts(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorItem item, boolean usesSecondLayer, A model, boolean legs, float red, float green, float blue, @Nullable String overlay);

    public ArmorFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    //TODO put this in a lib
    @Inject(method = "getArmorTexture", at = @At(value = "HEAD"), cancellable = true)
    private void getArmorTexture(ArmorItem item, boolean legs, String overlay, CallbackInfoReturnable<Identifier> cir) {
        var itemMaterialNameId = new Identifier(item.getMaterial().getName());
        var id = new Identifier(itemMaterialNameId.getNamespace(), "textures/models/armor/" + itemMaterialNameId.getPath() + "_layer_" + (legs ? 2 : 1) + (overlay == null ? "" : "_" + overlay) + ".png");
        cir.setReturnValue(ARMOR_TEXTURE_CACHE.computeIfAbsent(id.toString(), Identifier::new));
    }

//    //     private void renderArmorPiece(MatrixStack matrices, IRenderTypeBuffer vertexConsumers, T entity, EquipmentSlotType armorSlot, int light, A model) {
//    @Inject(method = "renderArmor", at = @At(value = "HEAD"), cancellable = true)
//    private void renderArmorPiece(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
//        ItemStack itemstack = entity.getEquippedStack(armorSlot);
//        if (itemstack.getItem() instanceof ArmorItem armorItem && (itemstack.getItem() == JellyfishingItems.AIR_SUIT_HELMET || itemstack.getItem() == JellyfishingItems.AIR_SUIT_CHESTPLATE || itemstack.getItem() == JellyfishingItems.AIR_SUIT_LEGGINGS || itemstack.getItem() == JellyfishingItems.AIR_SUIT_BOOTS)) {
//            if (armorItem.getSlotType() == armorSlot) {
//                model = JellyfishingClient.getArmorModel(armorSlot, model);
//                this.getContextModel().setAttributes(model);
//                this.setVisible(model, armorSlot);
//                boolean bl = this.usesSecondLayer(armorSlot);
//                boolean bl2 = itemstack.hasGlint();
//                this.renderArmorParts(matrices, vertexConsumers, light, armorItem, bl2, model, bl, 1.0F, 1.0F, 1.0F, null);
////                ci.cancel();
//            }
//        }
//    }
}