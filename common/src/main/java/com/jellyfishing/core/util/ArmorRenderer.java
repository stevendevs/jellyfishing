package com.jellyfishing.core.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

/**
 * Credit to Fabric API, Copied from lol
 */
@Environment(EnvType.CLIENT)
public interface ArmorRenderer {
    /**
     * Registers the armor renderer for the specified items.
     * @param renderer	the renderer
     * @param items		the items
     * @throws IllegalArgumentException if an item already has a registered armor renderer
     * @throws NullPointerException if either an item or the renderer is null
     */
    static void register(ArmorRenderer renderer, ItemLike... items) {
        ArmorRendererRegistryImpl.register(renderer, items);
    }

    /**
     * Helper method for rendering a specific armor model, comes after setting visibility.
     *
     * <p>This primarily handles applying glint and the correct {@link RenderType}
     * @param matrices			the matrix stack
     * @param vertexConsumers	the vertex consumer provider
     * @param light				packed lightmap coordinates
     * @param stack				the item stack of the armor item
     * @param model				the model to be rendered
     * @param texture			the texture to be applied
     */
    static void renderPart(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ItemStack stack, Model model, ResourceLocation texture) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(vertexConsumers, RenderType.armorCutoutNoCull(texture), false, stack.hasFoil());
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    /**
     * Renders an armor part.
     *
     * @param matrices			the matrix stack
     * @param vertexConsumers	the vertex consumer provider
     * @param stack				the item stack of the armor item
     * @param entity			the entity wearing the armor item
     * @param slot				the equipment slot in which the armor stack is worn
     * @param light				packed lightmap coordinates
     * @param contextModel		the model provided by {@link RenderLayer#getParentModel()}
     */
    void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel);
}