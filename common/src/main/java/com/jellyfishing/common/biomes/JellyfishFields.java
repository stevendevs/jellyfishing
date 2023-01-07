package com.jellyfishing.common.biomes;

import com.jellyfishing.core.registry.JellyfishingParticles;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class JellyfishFields extends JellyfishingBiome {

    static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, "jellyfishing:jellyfish_fields", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(JellyfishingBlocks.ALGAE_GRASS.get().getDefaultState(), JellyfishingBlocks.CORALSTONE.get().getDefaultState(), JellyfishingBlocks.ALGAE_GRASS.get().getDefaultState())));

    static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();

    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(SURFACE_BUILDER);


    //3448555
    public JellyfishFields() {
        super(CLIMATE, Biome.Category.OCEAN, -1.2F, .15F, GENERATION_SETTINGS.build(), SPAWN_SETTINGS.copy());
    }
    static {

        GENERATION_SETTINGS.withStructure(StructureFeatures.RUINED_PORTAL_OCEAN);

        DefaultBiomeFeatures.withOverworldOres(GENERATION_SETTINGS);

//        GENERATION_SETTINGS.withFeature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_CORAL_PLANT);
//        GENERATION_SETTINGS.withFeature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_TUBE_PLANT);
//        GENERATION_SETTINGS.withFeature(8, () -> JellyfishingConfiguredFeatures.CONFIGURED_SEANUT_BUSH);
//        GENERATION_SETTINGS.withFeature(6, () -> JellyfishingConfiguredFeatures.CONFIGURED_CORALSTONE_REPLACEMENT);
    }






    public static void bootstrapBiomes(BootstapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
    }
}