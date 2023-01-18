package com.jellyfishing.core.registry.forge;

import com.jellyfishing.core.mixin.forge.FireBlockAccessor;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;

import java.util.concurrent.atomic.AtomicReference;

public class JellyfishingExtrasImpl {
    public static <T extends Block> void setFlammable(RegistrySupplier<T> blockSP, int encouragement, int flammability) {
        AtomicReference<T> block2 = new AtomicReference<>();
        blockSP.listen(block2::set);
        ((FireBlockAccessor)Blocks.FIRE).invokeSetFlammable(block2.get(), encouragement, flammability);
    }

    public static <T extends ItemLike> void setCompostable(RegistrySupplier<T> like, float chance) {
        AtomicReference<T> like2 = new AtomicReference<>();
        like.listen(like2::set);
        ComposterBlock.COMPOSTABLES.put(like2.get(), chance);
    }
}