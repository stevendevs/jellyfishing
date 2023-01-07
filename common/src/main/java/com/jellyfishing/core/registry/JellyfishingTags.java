package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class JellyfishingTags {
    public static final TagKey<Block> JELLY_BLOCKS = create("jelly_blocks", Registries.BLOCK);

    private static <T> TagKey<T> create(String name, ResourceKey<? extends Registry<T>> resourceKey) {
        return TagKey.create(resourceKey, Jellyfishing.id(name));
    }
}