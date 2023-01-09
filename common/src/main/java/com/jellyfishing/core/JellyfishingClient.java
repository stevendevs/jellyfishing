package com.jellyfishing.core;

import com.jellyfishing.client.model.AbstractJellyfishModel;
import com.jellyfishing.client.model.SuitModel;
import com.jellyfishing.client.renderer.BlueJellyfishRenderer;
import com.jellyfishing.client.renderer.JellyfishRenderer;
import com.jellyfishing.common.misc.CloudParticle;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.jellyfishing.core.registry.JellyfishingEntities;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.registry.JellyfishingParticles;
import com.jellyfishing.core.util.ArmorRenderer;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;

public class JellyfishingClient {
    public static void onClientInit() {
        EntityModelLayerRegistry.register(SuitModel.LAYER_LOCATION, SuitModel::createBodyLayer);
        EntityModelLayerRegistry.register(AbstractJellyfishModel.LAYER_LOCATION, AbstractJellyfishModel::createBodyLayer);

        EntityRendererRegistry.register(JellyfishingEntities.JELLYFISH, JellyfishRenderer::new);
        EntityRendererRegistry.register(JellyfishingEntities.BLUE_JELLYFISH, BlueJellyfishRenderer::new);

        RenderTypeRegistry.register(RenderType.translucent(),
                JellyfishingBlocks.JELLY_BLOCK.get(),
                JellyfishingBlocks.BLUE_JELLY_BLOCK.get(),
                JellyfishingBlocks.BUBBLE_BLOCK.get()
        );
        RenderTypeRegistry.register(RenderType.cutoutMipped(),
                JellyfishingBlocks.CORAL_PLANT.get(),
                JellyfishingBlocks.TUBE_PLANT.get(),
                JellyfishingBlocks.SEANUT_BUSH.get(),
                JellyfishingBlocks.PINEAPPLE_PLANT.get(),
                JellyfishingBlocks.POTTED_PINEAPPLE.get(),
                JellyfishingBlocks.SCRAP_METAL_WINDOW.get(),
                JellyfishingBlocks.CHROME_DOOR.get(),
                JellyfishingBlocks.CHROME_VENT.get()
        );

        ParticleProviderRegistry.register(JellyfishingParticles.CLOUD_PARTICLE, CloudParticle.Factory::new);

        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            SuitModel suitModel = new SuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SuitModel.LAYER_LOCATION));
            contextModel.copyPropertiesTo(suitModel);
            suitModel.setAllVisible(true);
            if (stack.getItem().equals(JellyfishingItems.AIR_SUIT_HELMET.get()) || stack.getItem().equals(JellyfishingItems.AIR_SUIT_BOOTS.get()) || stack.getItem().equals(JellyfishingItems.AIR_SUIT_CHESTPLATE.get()))
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, suitModel, Jellyfishing.id("textures/models/armor/sandy_suit_legs.png"));
            else if (stack.getItem().equals(JellyfishingItems.AIR_SUIT_LEGGINGS.get()))
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, suitModel, Jellyfishing.id("textures/models/armor/sandy_suit.png"));
        }, JellyfishingItems.AIR_SUIT_HELMET.get(), JellyfishingItems.AIR_SUIT_CHESTPLATE.get(), JellyfishingItems.AIR_SUIT_LEGGINGS.get(), JellyfishingItems.AIR_SUIT_BOOTS.get());
    }
}
