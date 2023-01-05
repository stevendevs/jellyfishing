package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.client.JellyfishingClient;
import blueduck.jellyfishing.client.entity.model.AirSuitModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AirSuitRenderer implements ArmorRenderer {
    private static final Identifier TEXTURE = Jellyfishing.id("textures/models/armor/air_suit.png");
    private static AirSuitModel airSuitModel;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        if (airSuitModel == null) {
            airSuitModel = new AirSuitModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(JellyfishingClient.AIR_SUIT_LAYER));
        }
        contextModel.setAttributes(airSuitModel);
//        airSuitModel.setVisible(false);
//        airSuitModel.head.visible = slot == EquipmentSlot.HEAD;
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, airSuitModel, TEXTURE);
    }


}