package com.jellyfishing.datagen;

import com.jellyfishing.common.worldgen.JellyfishingBiome;
import com.jellyfishing.datagen.provider.JellyWorldGenProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class JellyfishingData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(JellyWorldGenProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, JellyfishingBiome::bootstrapBiomes);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, JellyfishingBiome::bootstrapConfiguredFeatures);
    }
}
