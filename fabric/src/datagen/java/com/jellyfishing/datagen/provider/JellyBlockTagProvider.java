package com.jellyfishing.datagen.provider;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
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
                        CHROME_METAL_SLAB.get(), CHROME_BRICKS.get(), CHROME_METAL_STAIRS.get(), CHROME_BRICK_SLAB.get(), VAULT_DOOR.get(),
                        VAULT_TRAPDOOR.get(), SCRAP_METAL_WINDOW.get(), CHROME_DOOR.get(), CHROME_VENT.get(), SEANUT_BRITTLE_BLOCK.get(),
                        CORALSTONE.get(), CORALSTONE_WALL.get(), CORALSTONE_STAIRS.get(), CORALSTONE_SLAB.get()
                );
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(PINEAPPLE_BLOCK.get(), PINEAPPLE_PILLAR.get(), CHISELED_PINEAPPLE_BLOCK.get()
                );
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(SCRAP_METAL.get(), SCRAP_METAL_STAIRS.get(), SCRAP_METAL_SLAB.get(), CHROME_METAL.get(), CHROME_METAL_STAIRS.get(), CHROME_METAL_SLAB.get(), CHROME_BRICKS.get(), CHROME_METAL_STAIRS.get(), CHROME_BRICK_SLAB.get(), VAULT_DOOR.get(), VAULT_TRAPDOOR.get(), SCRAP_METAL_WINDOW.get(), CHROME_DOOR.get(), CHROME_VENT.get());
    }
}