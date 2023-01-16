package com.jellyfishing.core.fabric;

import com.jellyfishing.client.renderer.SpatulaItemRender;
import com.jellyfishing.common.worldgen.JellyfishingBiome;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.mixin.access.StructurePoolAccess;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingFeatures;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.client.renderer.texture.atlas.SpriteResourceLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class JellyfishingFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        registerVillagerHouses();

        Jellyfishing.init();
    }

    public static void registerVillagerHouses() {
        final String[] types = new String[] {"desert", "plains", "savanna", "snowy", "taiga"};
        for (String type : types) {
            DynamicRegistrySetupCallback.EVENT.register(registryManager ->
                    registryManager.registerEntryAdded(Registries.TEMPLATE_POOL, ((rawId, id, pool) -> {
                        if (id.equals(new ResourceLocation("minecraft", "village/" + type + "/houses"))) {
                            for (int i = 0; i < 10; i++) {
                                pool.templates.add(StructurePoolElement.legacy(Jellyfishing.MOD_ID + ":village/krusty_krab_" + type).apply(StructureTemplatePool.Projection.RIGID));
                            }
                        }
                    }))
            );
        }
    }
}