package com.jellyfishing.core.registry;

import com.jellyfishing.common.features.CoralPlantFeature;
import com.jellyfishing.common.features.PineapplePlantFeature;
import com.jellyfishing.common.features.SeanutBushFeature;
import com.jellyfishing.common.features.TubePlantFeature;
import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class JellyfishingFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<Feature<ProbabilityFeatureConfiguration>> CORAL_PLANT_FEATURE = FEATURES.register("coral_plant_feature", () -> new CoralPlantFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistrySupplier<Feature<ProbabilityFeatureConfiguration>> TUBE_PLANT_FEATURE = FEATURES.register("tube_plant_feature", () ->new TubePlantFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistrySupplier<Feature<ProbabilityFeatureConfiguration>> SEANUT_BUSH_FEATURE = FEATURES.register("seanut_bush_feature", () -> new SeanutBushFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistrySupplier<Feature<ProbabilityFeatureConfiguration>> PINEAPPLE_PLANT_FEATURE = FEATURES.register("pineapple_plant_feature", () -> new PineapplePlantFeature(ProbabilityFeatureConfiguration.CODEC));


    public static void registerConfiguredFeatures() {
        register("coral_plant_feature", CORAL_PLANT_FEATURE.get().register(new ProbabilityFeatureConfiguration(.5F)).range(32).square().count(100));
        register("tube_plant_feature", TUBE_PLANT_FEATURE.get().register(new ProbabilityFeatureConfiguration(.1F)).range(32).square().count(100));
        register("seanut_bush_feature", SEANUT_BUSH_FEATURE.get().register(new ProbabilityFeatureConfiguration(.2F)).range(32).square().count(100));
        register("coralstone_replacement", Feature.ORE.register(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, JellyfishingBlocks.CORALSTONE.get().getDefaultState(), 100)).range(300).square().count(50));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.registerForHolder(BuiltInRegistries.FEATURE, new ResourceLocation(Jellyfishing.MOD_ID, name), feature);
    }
}
