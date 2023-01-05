package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.misc.config.JellyfishingConfig;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class JellyfishingBiomes {
    //TODO
//    public static RegistryKey<Biome> JELLYFISH_FIELDS;
//
//    static float CLOUDS;
//    static MusicSound MUSIC;
//
//    public static void init() {
//        CLOUDS = JellyfishingConfig.FLOWER_CLOUDS.get() ? 0.000005F : 0f;
//        MUSIC = JellyfishingConfig.MUSIC.get() ? new MusicSound(JellyfishingSounds.BACKGROUND_MUSIC, 200, 4000, false) : MusicType.GAME;
//
//        JELLYFISH_FIELDS = createBiome("jellyfish_fields", makeJellyfishingBiome());
//
//        OverworldBiomes.addContinentalBiome(JELLYFISH_FIELDS, OverworldClimate.TEMPERATE, JellyfishingConfig.BIOME_WEIGHT.get());
//    }
//
//    private static RegistryKey<Biome> createBiome(String id, Biome biome) {
//        Identifier identifier = Jellyfishing.id(id);
//        BuiltinRegistries.add(BuiltinRegistries.BIOME, identifier, biome);
//
//        return getKey(identifier);
//    }
//
//    private static RegistryKey<Biome> getKey(Identifier identifier) {
//        return RegistryKey.of(Registry.BIOME_KEY, identifier);
//    }
//
//    static final ConfiguredSurfaceBuilder<?> JELLY_SURFACE_BUILDER = Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, Jellyfishing.id("jellyfish_fields"), new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, new TernarySurfaceConfig(JellyfishingBlocks.ALGAE_GRASS.getDefaultState(), JellyfishingBlocks.CORALSTONE.getDefaultState(), JellyfishingBlocks.ALGAE_GRASS.getDefaultState())));
//
//    private static Biome makeJellyfishingBiome() {
//        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
//        GenerationSettings.Builder builder = new GenerationSettings.Builder().surfaceBuilder(JELLY_SURFACE_BUILDER);
//
//        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_OCEAN);
//        DefaultBiomeFeatures.addDefaultOres(builder);
//
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.CONFIGURED_CORAL_PLANT);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.CONFIGURED_TUBE_PLANT);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.CONFIGURED_SEANUT_BUSH);
//        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, JellyfishingConfiguredFeatures.CONFIGURED_CORALSTONE_REPLACEMENT);
//
////        builder.feature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_CORAL_PLANT);
////        builder.feature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_TUBE_PLANT);
////        builder.feature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_SEANUT_BUSH);
////        builder.feature(6, () -> JellyfishingConfiguredFeatures.CONFIGURED_CORALSTONE_REPLACEMENT);
//
//        spawns.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(JellyfishingEntities.JELLYFISH, 1000, 1, 1));
//        spawns.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(JellyfishingEntities.BLUE_JELLYFISH, 100, 1, 1));
//
//        return (new Biome.Builder())
//                .precipitation(Biome.Precipitation.RAIN)
//                .temperature(0.8F)
//                .temperatureModifier(Biome.TemperatureModifier.NONE)
//                .downfall(0.4F)
//                .category(Biome.Category.OCEAN)
//                .depth(-1.2F)
//                .scale(.15F)
//                .effects((new BiomeEffects.Builder())
//                        .waterColor(4566523)
//                        .waterFogColor(2587774)
//                        .fogColor(12638463)
//                        .skyColor(7842047)
//                        .moodSound(BiomeMoodSound.CAVE)
//                        .particleConfig(new BiomeParticleConfig(JellyfishingParticles.CLOUD_PARTICLE, CLOUDS))
//                        .music(MUSIC)
//                        .build())
//                .spawnSettings(spawns
//                        .playerSpawnFriendly()
//                        .build())
//                .generationSettings((builder)
//                        .surfaceBuilder(JELLY_SURFACE_BUILDER)
//                        .build()).build();
//    }
}