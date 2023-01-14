package com.jellyfishing.client.renderer;

import com.jellyfishing.client.model.SpatulaModel;
import com.jellyfishing.common.item.SpatulaItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class SpatulaItemRender implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    public static final SpatulaItemRender INSTANCE = new SpatulaItemRender();

    private SpatulaModel model;

    public SpatulaModel getModel()
    {
        if (this.model == null)
            this.model = new SpatulaModel(Minecraft.getInstance().getEntityModels().bakeLayer(SpatulaModel.LAYER_LOCATION));
        return this.model;
    }

    @Override
    public void render(ItemStack stack, ItemTransforms.TransformType mode, PoseStack poseStack, MultiBufferSource vertexConsumers, int light, int overlay) {
        if (stack.getItem() instanceof SpatulaItem) {
            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexConsumer2 = ItemRenderer.getFoilBufferDirect(vertexConsumers, getModel().renderType(SpatulaModel.TEXTURE), false, stack.hasFoil());
            getModel().renderToBuffer(poseStack, vertexConsumer2, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
        }
    }
}