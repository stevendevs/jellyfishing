package com.jellyfishing.core.registry.forge;

import com.jellyfishing.core.mixin.forge.FireBlockAccessor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;

import java.util.function.Supplier;

public class JellyfishingExtrasImpl {
    public static <T extends Block> void setFlammable(Supplier<T> block, int encouragement, int flammability) {
        ((FireBlockAccessor)Blocks.FIRE).invokeSetFlammable(block.get(), encouragement, flammability);
    }

    public static <T extends ItemLike> void setCompostable(Supplier<T> like, float chance) {
        ComposterBlock.COMPOSTABLES.put(like.get(), chance);
    }
}