package com.jellyfishing.core.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

/**
 * Credit to Fabric API, Copied from lol
 */
public class ArmorRendererRegistryImpl {
    private static final HashMap<Item, ArmorRenderer> RENDERERS = new HashMap<>();

    public static void register(ArmorRenderer renderer, ItemLike... items) {
        Objects.requireNonNull(renderer, "renderer is null");

        if (items.length == 0) {
            throw new IllegalArgumentException("Armor renderer registered for no item");
        }

        for (ItemLike item : items) {
            Objects.requireNonNull(item.asItem(), "armor item is null");

            if (RENDERERS.putIfAbsent(item.asItem(), renderer) != null) {
                throw new IllegalArgumentException("Custom armor renderer already exists for " + BuiltInRegistries.ITEM.getKey(item.asItem()));
            }
        }
    }

    @Nullable
    public static ArmorRenderer get(Item item) {
        return RENDERERS.get(item);
    }
}