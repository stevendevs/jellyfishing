package com.jellyfishing.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.concurrent.CompletableFuture;

import static com.jellyfishing.core.registry.JellyfishingPaintings.*;

public class JellyPaintingTagProvider extends FabricTagProvider<PaintingVariant> {
    public JellyPaintingTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.PAINTING_VARIANT, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(PaintingVariantTags.PLACEABLE)
                .add(CAPTAIN.get(), BOLD_AND_BRASH.get(), JELLYFISH.get(), JELLYFISH_FIELDS.get(), PATTY_WAGON.get(),
                        MILLIONTH_DOLLAR.get()
                );
    }
}