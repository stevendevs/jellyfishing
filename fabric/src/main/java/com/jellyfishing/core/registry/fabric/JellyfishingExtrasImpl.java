package com.jellyfishing.core.registry.fabric;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class JellyfishingExtrasImpl {
    public static <T extends Block> void setFlammable(RegistrySupplier<T> block, int encouragement, int flammability) {
        FlammableBlockRegistry.getDefaultInstance().add(block.get(), encouragement, flammability);
    }

    public static <T extends ItemLike> void setCompostable(RegistrySupplier<T> like, float chance) {
        CompostingChanceRegistry.INSTANCE.add(like.get(), chance);
    }
}