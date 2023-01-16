package com.jellyfishing.datagen;

import com.jellyfishing.common.worldgen.JellyfishingBiome;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingPaintings;
import com.jellyfishing.datagen.provider.JellyBlockTagProvider;
import com.jellyfishing.datagen.provider.JellyPaintingTagProvider;
import com.jellyfishing.datagen.provider.JellyWorldGenProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.structures.StructureUpdater;

public class JellyfishingData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(JellyWorldGenProvider::new);
        pack.addProvider(JellyBlockTagProvider::new);
        pack.addProvider(JellyPaintingTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, JellyfishingBiome::bootstrapBiomes);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, JellyfishingBiome::bootstrapConfiguredFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, JellyfishingBiome::bootstrapPlacedFeatures);
    }
}
