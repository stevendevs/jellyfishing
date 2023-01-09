package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class JellyfishingBiomes {
    public static final ResourceKey<Biome> JELLYFISH_FIELDS = create("jellyfish_fields");

    private static ResourceKey<Biome> create(String name) {
        return ResourceKey.create(Registries.BIOME, Jellyfishing.id(name));
    }
}