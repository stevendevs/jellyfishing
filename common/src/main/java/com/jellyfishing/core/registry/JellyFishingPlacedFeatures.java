package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class JellyFishingPlacedFeatures {
    public static final ResourceKey<PlacedFeature> CORAL_PLANT = create("coral_plant");
    public static final ResourceKey<PlacedFeature> TUBE_PLANT = create("tube_plant");
    public static final ResourceKey<PlacedFeature> SEANUT_BUSH = create("seanut_bush");

    public static ResourceKey<PlacedFeature> create(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Jellyfishing.id(string));
    }
}
