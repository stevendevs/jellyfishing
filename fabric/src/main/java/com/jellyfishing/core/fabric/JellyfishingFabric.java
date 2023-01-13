package com.jellyfishing.core.fabric;

import com.jellyfishing.common.worldgen.JellyfishingBiome;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;

public class JellyfishingFabric implements ModInitializer {

    @Override
    public void onInitialize() {
//        ForgeConfigRegistry.INSTANCE.register(Jellyfishing.MOD_ID, ModConfig.Type.COMMON, JellyfishingConfig.COMMON_CONFIG);
//        ForgeConfigRegistry.INSTANCE.register(Jellyfishing.MOD_ID, ModConfig.Type.CLIENT, JellyfishingConfig.CLIENT_CONFIG);

        Jellyfishing.init();
    }
}