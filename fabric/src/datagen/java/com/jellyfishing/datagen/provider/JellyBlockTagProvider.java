package com.jellyfishing.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

import static com.jellyfishing.core.registry.JellyfishingBlocks.*;

public class JellyBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public JellyBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SCRAP_METAL.get(), SCRAP_METAL_STAIRS.get(), SCRAP_METAL_SLAB.get(), CHROME_METAL.get(), CHROME_METAL_STAIRS.get(),
                        CHROME_METAL_SLAB.get(), CHROME_BRICKS.get(), CHROME_BRICK_STAIRS.get(), CHROME_BRICK_SLAB.get(), VAULT_DOOR.get(),
                        VAULT_TRAPDOOR.get(), SCRAP_METAL_WINDOW.get(), CHROME_DOOR.get(), CHROME_VENT.get(), SEANUT_BRITTLE_BLOCK.get(),
                        POLISHED_CORALSTONE.get(), POLISHED_CORALSTONE_SLAB.get(), POLISHED_CORALSTONE_STAIRS.get(), GRILL.get(), POTTED_PINEAPPLE.get()
                );
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(PINEAPPLE_BLOCK.get(), PINEAPPLE_PILLAR.get(), CHISELED_PINEAPPLE_BLOCK.get(), BAMBOO_WALL.get(), PINK_BAMBOO_WALL.get(),
                        BLUE_BAMBOO_WALL.get(), YELLOW_BAMBOO_WALL.get()
                );
        getOrCreateTagBuilder(FabricMineableTags.SHEARS_MINEABLE)
                .add(WHITE_CARPETED_TILES.get(), ORANGE_CARPETED_TILES.get(), MAGENTA_CARPETED_TILES.get(), LIGHT_BLUE_CARPETED_TILES.get(), YELLOW_CARPETED_TILES.get(),
                        LIME_CARPETED_TILES.get(), PINK_CARPETED_TILES.get(), GRAY_CARPETED_TILES.get(), LIGHT_GRAY_CARPETED_TILES.get(), CYAN_CARPETED_TILES.get(),
                        PURPLE_CARPETED_TILES.get(), BLUE_CARPETED_TILES.get(), BROWN_CARPETED_TILES.get(), GREEN_CARPETED_TILES.get(), RED_CARPETED_TILES.get(),
                        BLACK_CARPETED_TILES.get()
                );
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ALGAE_GRASS.get(), ALGAE_BLOCK.get(), PATTY_TILES.get(), CORALSTONE.get(), CORALSTONE_WALL.get(),
                        CORALSTONE_STAIRS.get(), CORALSTONE_SLAB.get()
                );
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(SEANUT_BUSH.get(), PINEAPPLE_PLANT.get(), CORAL_PLANT.get(), TUBE_PLANT.get()
                );
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(SCRAP_METAL.get(), SCRAP_METAL_STAIRS.get(), SCRAP_METAL_SLAB.get(), CHROME_METAL.get(), CHROME_METAL_STAIRS.get(), CHROME_METAL_SLAB.get(), CHROME_BRICKS.get(), CHROME_METAL_STAIRS.get(), CHROME_BRICK_SLAB.get(), VAULT_DOOR.get(), VAULT_TRAPDOOR.get(), SCRAP_METAL_WINDOW.get(), CHROME_DOOR.get(), CHROME_VENT.get());
    }
}