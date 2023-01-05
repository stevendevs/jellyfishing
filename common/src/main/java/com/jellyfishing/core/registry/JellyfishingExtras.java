package com.jellyfishing.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class JellyfishingExtras {
    public static void registerFlammables() {
        setFlammable(JellyfishingBlocks.BAMBOO_WALL, 10, 20);
        setFlammable(JellyfishingBlocks.PINK_BAMBOO_WALL, 10, 20);
        setFlammable(JellyfishingBlocks.BLUE_BAMBOO_WALL, 10, 20);
        setFlammable(JellyfishingBlocks.YELLOW_BAMBOO_WALL, 10, 20);

        setFlammable(JellyfishingBlocks.WHITE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.ORANGE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.MAGENTA_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.LIGHT_BLUE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.YELLOW_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.LIME_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.PINK_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.GRAY_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.LIGHT_GRAY_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.CYAN_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.PURPLE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.BLUE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.BROWN_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.GREEN_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.RED_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.BLACK_CARPETED_TILES, 8, 20);
    }

    public static void registerCompostables() {
        setCompostable(JellyfishingItems.SEANUT, 0.3F);
        setCompostable(JellyfishingItems.PINEAPPLE_SEEDS, 0.3F);
        setCompostable(JellyfishingItems.PINEAPPLE, 0.7F);
    }
    
    @ExpectPlatform
    public static <T extends Block> void setFlammable(Supplier<T> block, int encouragement, int flammability) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends ItemLike> void setCompostable(Supplier<T> like, float chance) {
        throw new AssertionError();
    }
}