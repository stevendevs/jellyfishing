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
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

import java.util.function.Supplier;

public class JellyfishingFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.FEATURE);
    
    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> CORAL_PLANT_FEATURE = create("coral_plant_feature", () -> new CoralPlantFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> TUBE_PLANT_FEATURE = create("tube_plant_feature", () -> new TubePlantFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> SEANUT_BUSH_FEATURE = create("seanut_bush_feature", () -> new SeanutBushFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistrySupplier<Feature<ProbabilityFeatureConfiguration>> PINEAPPLE_PLANT_FEATURE = create("pineapple_plant_feature", () -> new PineapplePlantFeature(ProbabilityFeatureConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> RegistrySupplier<F> create(String key, Supplier<F> value) {
        return FEATURES.register(key, value);
    }
}