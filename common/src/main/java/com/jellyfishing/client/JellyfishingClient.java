package com.jellyfishing.client;

import com.google.common.collect.ImmutableList;
import com.jellyfishing.client.model.AbstractJellyfishModel;
import com.jellyfishing.client.model.ModelPerspectiveRegistry;
import com.jellyfishing.client.model.SpatulaModel;
import com.jellyfishing.client.model.SuitModel;
import com.jellyfishing.client.renderer.BlueJellyfishRenderer;
import com.jellyfishing.client.renderer.JellyfishRenderer;
import com.jellyfishing.common.misc.CloudParticle;
import com.jellyfishing.core.Jellyfishing;
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
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class JellyfishingClient {
    public static void onClientInit() {
        EntityModelLayerRegistry.register(SuitModel.LAYER_LOCATION, SuitModel::createBodyLayer);
        EntityModelLayerRegistry.register(AbstractJellyfishModel.LAYER_LOCATION, AbstractJellyfishModel::createBodyLayer);
        EntityModelLayerRegistry.register(SpatulaModel.LAYER_LOCATION, SpatulaModel::createBodyLayer);

        EntityRendererRegistry.register(JellyfishingEntities.JELLYFISH, JellyfishRenderer::new);
        EntityRendererRegistry.register(JellyfishingEntities.BLUE_JELLYFISH, BlueJellyfishRenderer::new);

        var blockRendersTranslucent = ImmutableList.of(JellyfishingBlocks.JELLY_BLOCK, JellyfishingBlocks.BLUE_JELLY_BLOCK);
        var blockRendersMipped = ImmutableList.of(JellyfishingBlocks.CORAL_PLANT, JellyfishingBlocks.TUBE_PLANT, JellyfishingBlocks.SEANUT_BUSH, JellyfishingBlocks.PINEAPPLE_PLANT, JellyfishingBlocks.POTTED_PINEAPPLE, JellyfishingBlocks.SCRAP_METAL_WINDOW, JellyfishingBlocks.CHROME_DOOR, JellyfishingBlocks.CHROME_VENT);

        blockRendersTranslucent.forEach(block -> block.listen((real)-> RenderTypeRegistry.register(RenderType.translucent(), real)));
        blockRendersMipped.forEach(block -> block.listen((real)-> RenderTypeRegistry.register(RenderType.cutoutMipped(), real)));

        ParticleProviderRegistry.register(JellyfishingParticles.CLOUD_PARTICLE, CloudParticle.Factory::new);

        JellyfishingItems.AIR_SUIT_HELMET.listen((item)-> JellyfishingItems.AIR_SUIT_CHESTPLATE.listen((item1)-> JellyfishingItems.AIR_SUIT_LEGGINGS.listen((item2)-> JellyfishingItems.AIR_SUIT_BOOTS.listen((item3)->
                ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
                    SuitModel suitModel = new SuitModel(Minecraft.getInstance().getEntityModels().bakeLayer(SuitModel.LAYER_LOCATION));
                    suitModel.setCurrentEntity(entity);
                    contextModel.copyPropertiesTo(suitModel);
                    suitModel.setAllVisible(true);
                    if (stack.getItem().equals(JellyfishingItems.AIR_SUIT_HELMET))
                        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, suitModel, Jellyfishing.id("textures/models/armor/air_suit_helmet.png"));
                    else if (stack.getItem().equals(JellyfishingItems.AIR_SUIT_CHESTPLATE))
                        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, suitModel, Jellyfishing.id("textures/models/armor/air_suit_chest.png"));
                    else if (stack.getItem().equals(JellyfishingItems.AIR_SUIT_LEGGINGS))
                        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, suitModel, Jellyfishing.id("textures/models/armor/air_suit_legs.png"));
                    else if (stack.getItem().equals(JellyfishingItems.AIR_SUIT_BOOTS))
                        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, suitModel, Jellyfishing.id("textures/models/armor/air_suit_boots.png"));
        }, item, item1, item2, item3)))));


        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("spatula_in_hand"), "inventory"));
        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("golden_spatula_in_hand"), "inventory"));
        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("karate_glove_left"), "inventory"));
        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("karate_glove_right"), "inventory"));
        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("master_karate_glove_left"), "inventory"));
        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("master_karate_glove_right"), "inventory"));
        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("power_karate_glove_right"), "inventory"));
        ModelPerspectiveRegistry.registerModelProvider(new ModelResourceLocation(Jellyfishing.id("power_karate_glove_left"), "inventory"));
        JellyfishingItems.SPATULA.listen((item)-> ModelPerspectiveRegistry.registerMultiplePerspective(item, new ModelResourceLocation(Jellyfishing.id("spatula_in_hand"), "inventory"), new ModelResourceLocation(Jellyfishing.id("spatula"), "inventory")));
        JellyfishingItems.GOLDEN_SPATULA.listen((item)-> ModelPerspectiveRegistry.registerMultiplePerspective(item, new ModelResourceLocation(Jellyfishing.id("golden_spatula_in_hand"), "inventory"), new ModelResourceLocation(Jellyfishing.id("golden_spatula"), "inventory")));
        JellyfishingItems.KARATE_GLOVE.listen((item)-> ModelPerspectiveRegistry.registerLeftRightPerspective(item, new ModelResourceLocation(Jellyfishing.id("karate_glove_left"), "inventory"), new ModelResourceLocation(Jellyfishing.id("karate_glove_right"), "inventory"), new ModelResourceLocation(Jellyfishing.id("karate_glove"), "inventory")));
        JellyfishingItems.MASTER_KARATE_GLOVE.listen((item)-> ModelPerspectiveRegistry.registerLeftRightPerspective(item, new ModelResourceLocation(Jellyfishing.id("master_karate_glove_left"), "inventory"), new ModelResourceLocation(Jellyfishing.id("master_karate_glove_right"), "inventory"), new ModelResourceLocation(Jellyfishing.id("master_karate_glove"), "inventory")));
        JellyfishingItems.POWER_KARATE_GLOVE.listen((item)-> ModelPerspectiveRegistry.registerLeftRightPerspective(item, new ModelResourceLocation(Jellyfishing.id("power_karate_glove_left"), "inventory"), new ModelResourceLocation(Jellyfishing.id("power_karate_glove_right"), "inventory"), new ModelResourceLocation(Jellyfishing.id("power_karate_glove"), "inventory")));
    }
}