package com.jellyfishing.common.worldgen;

import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.jellyfishing.core.registry.JellyfishingEntities;
import com.jellyfishing.core.registry.JellyfishingFeatures;
import com.jellyfishing.core.registry.JellyfishingParticles;
import com.jellyfishing.core.registry.JellyfishingSounds;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.sounds.Music;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import javax.annotation.Nullable;

public class JellyfishingBiome {
    public static Music MUSIC = new Music(JellyfishingSounds.BACKGROUND_MUSIC, 200, 4000, false);
    public static AmbientParticleSettings PARTICLES = new AmbientParticleSettings(JellyfishingParticles.CLOUD_PARTICLE.get(), 0.000005F);

    public static void bootstrapBiomes(BootstapContext<Biome> biomeRegisterable) {
        biomeRegisterable.register(
                JellyfishingBiomes.JELLYFISH_FIELDS,
                jellyfishFields(
                        biomeRegisterable.lookup(Registries.PLACED_FEATURE),
                        biomeRegisterable.lookup(Registries.CONFIGURED_CARVER)
                )
        );
    }


    public static void bootstrapConfiguredFeatures(BootstapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest ruleTest = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        FeatureUtils.register(
                featureRegisterable,
                JellyfishingFeatures.CONFIGURED_CORALSTONE_REPLACEMENT,
                Feature.ORE,
                new OreConfiguration(
                        ruleTest,
                        JellyfishingBlocks.CORALSTONE.get().defaultBlockState(),
                        100
                )
        );
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor,
                               int waterFogColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder,
                               @Nullable Music music, AmbientParticleSettings particleSettings) {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor)
                        .fogColor(12638463).skyColor(skyColor).ambientParticle(particleSettings)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome jellyfishFields(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(JellyfishingEntities.JELLYFISH.get(), 1000, 1, 1));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(JellyfishingEntities.BLUE_JELLYFISH.get(), 100, 1, 1));


        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
//        biomeBuilder.a(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, BuiltinStructures.RUINED_PORTAL_OCEAN.);
//        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());
//        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());
//        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());
//        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());

        return biome(Biome.Precipitation.RAIN, 0.8F, 0.4F, 4566523, 2587774, 7842047, spawnBuilder, biomeBuilder, MUSIC, PARTICLES);
    }
}