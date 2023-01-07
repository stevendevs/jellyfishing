package com.jellyfishing.common.biomes;

import com.jellyfishing.core.config.JellyfishingClientConfig;
import com.jellyfishing.core.config.JellyfishingConfig;
import com.jellyfishing.core.registry.JellyfishingEntities;
import com.jellyfishing.core.registry.JellyfishingParticles;
import com.jellyfishing.core.registry.JellyfishingSounds;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

public class JellyfishingBiome {
//    private final Biome biome;
//
//    public JellyfishingBiome(Biome.Climate climate, Biome.Category category, float depth, float scale, BiomeAmbience effects, BiomeGenerationSettings biomeGenerationSettings, MobSpawnInfo mobSpawnInfo) {
//        biome = new Biome(climate, category, depth, scale, effects, biomeGenerationSettings, mobSpawnInfo);
//    }
//    public JellyfishingBiome(Biome.Builder builder) {
//        this.biome = builder.build();
//    }
//
//    public JellyfishingBiome(Biome biome) {
//        this.biome = biome;
//    }
//
//    public Biome getBiome() {
//        return this.biome;
//    }
//
//
//    public BiomeDictionary.Type[] getBiomeDictionary() {
//        return new BiomeDictionary.Type[]{BiomeDictionary.Type.OVERWORLD};
//    }
//
//    public BiomeManager.BiomeType getBiomeType() {
//        return BiomeManager.BiomeType.WARM;
//    }
//
//    public RegistryKey<Biome> getKey() {
//        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, Objects.requireNonNull(WorldGenRegistries.BIOME.getKey(this.biome)));
//    }

    public static Music MUSIC = JellyfishingClientConfig.MUSIC.get() ? new Music(JellyfishingSounds.BACKGROUND_MUSIC, 200, 4000, false) : Musics.GAME;
    public static AmbientParticleSettings PARTICLES = new AmbientParticleSettings(JellyfishingParticles.CLOUD_PARTICLE.get(), JellyfishingConfig.FLOWER_CLOUDS.get() ? 0.000005F : 0f);

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor,
                               int waterFogColor, int skyColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder,
                               @Nullable Music music, AmbientParticleSettings particleSettings) {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).temperature(temperature).downfall(downfall)
                .specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor)
                        .fogColor(12638463).skyColor(skyColor).ambientParticle(particleSettings)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome jellyfishFields() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(JellyfishingEntities.JELLYFISH.get(), 1000, 1, 1));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(JellyfishingEntities.BLUE_JELLYFISH.get(), 100, 1, 1));


        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(PlacedFeature.LIST_OF_LISTS_CODEC, ConfiguredWorldCarver.CODEC);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);



        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatureInit.GUM_PLACED.getHolder().get());

        return biome(Biome.Precipitation.RAIN, 0.8F, 0.4F, 4566523, 2587774, 5212728, spawnBuilder, biomeBuilder, MUSIC, PARTICLES);
    }
}
