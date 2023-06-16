package com.jellyfishing.datagen.provider;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingVillagers;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;

public class JellyPoiTypeProvider extends FabricTagProvider<PoiType> {
    public JellyPoiTypeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(BuiltInRegistries.POINT_OF_INTEREST_TYPE.get(Jellyfishing.id("frycook")));
    }
}