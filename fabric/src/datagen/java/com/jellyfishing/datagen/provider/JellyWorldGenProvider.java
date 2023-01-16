package com.jellyfishing.datagen.provider;

import com.jellyfishing.common.worldgen.JellyfishingBiome;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class JellyWorldGenProvider extends FabricDynamicRegistryProvider {
    public JellyWorldGenProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "Jellyfishing World Generation Modifications";
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.add(JellyfishingBiomes.JELLYFISH_FIELDS, registries.lookupOrThrow(Registries.BIOME).getOrThrow(JellyfishingBiomes.JELLYFISH_FIELDS).value());
        entries.add(JellyfishingBiome.PLACED_JELLY_FEATURES, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(JellyfishingBiome.PLACED_JELLY_FEATURES).value());
        entries.add(JellyfishingBiome.JELLY_FEATURES, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(JellyfishingBiome.JELLY_FEATURES).value());
        entries.add(JellyfishingConfiguredFeatures.CONFIGURED_PINEAPPLE_PLANT_PATCH, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(JellyfishingConfiguredFeatures.CONFIGURED_PINEAPPLE_PLANT_PATCH).value());
        entries.add(JellyfishingConfiguredFeatures.PLACED_PINEAPPLE_PLANT_PATCH, registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(JellyfishingConfiguredFeatures.PLACED_PINEAPPLE_PLANT_PATCH).value());
    }
}