package com.jellyfishing.datagen.provider;

import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingFeatures;
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
        entries.add(JellyfishingFeatures.CONFIGURED_CORALSTONE_REPLACEMENT, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(JellyfishingFeatures.CONFIGURED_CORALSTONE_REPLACEMENT).value());
    }
}
