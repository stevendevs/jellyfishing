package com.jellyfishing.core.registry;

import com.google.common.collect.ImmutableSet;
import com.jellyfishing.common.block.PineapplePlant;
import com.jellyfishing.core.Jellyfishing;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class JellyfishingConfiguredFeatures {
//    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_PINEAPPLE_PLANT = JellyfishingFeatures.PINEAPPLE_PLANT_FEATURE.get().withConfiguration(new ProbabilityFeatureConfiguration(.05F)).range(32).square().count(1);
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_PINEAPPLE_PLANT_PATCH = createConfigured("pineapple_patch");
    //public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_PINEAPPLE_PLANT_PATCH = Feature.RANDOM_PATCH.(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(JellyfishingBlocks.PINEAPPLE_PLANT.get().getDefaultState().with(PineapplePlant.AGE, Integer.valueOf(3))), SimpleBlockPlacer.PLACER).tries(4).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).replaceable().build()).withPlacement(Features.Placements.PATCH_PLACEMENT);

    public static final ResourceKey<PlacedFeature> PLACED_PINEAPPLE_PLANT_PATCH = ResourceKey.create(
            Registries.PLACED_FEATURE,
            Jellyfishing.id("pineapple_patch")
    );
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_CORALSTONE_REPLACEMENT = createConfigured("coralstone_replacement");
    
//    public static void registerConfiguredFeatures() {
//        Registry.register(registry, new ResourceLocation(Jellyfishing.MOD_ID, "pineapple_plant"), CONFIGURED_PINEAPPLE_PLANT);
//        Registry.register(registry, new ResourceLocation(Jellyfishing.MOD_ID, "pineapple_patch"), CONFIGURED_PINEAPPLE_PLANT_PATCH);
//    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createConfigured(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Jellyfishing.id(name));
    }
}