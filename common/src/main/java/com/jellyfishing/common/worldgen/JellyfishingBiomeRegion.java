package com.jellyfishing.common.worldgen;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class JellyfishingBiomeRegion extends Region {
    public JellyfishingBiomeRegion() {
        super(Jellyfishing.id("biome_region"), RegionType.OVERWORLD, 6);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.WARM_OCEAN, JellyfishingBiomes.JELLYFISH_FIELDS);
        });
//        this.addBiome(
//                mapper,
//                ParameterUtils.Temperature.WARM,
//                ParameterUtils.Humidity.WET,
//                ParameterUtils.Continentalness.DEEP_OCEAN,
//                ParameterUtils.Erosion.FULL_RANGE,
//                ParameterUtils.Weirdness.FULL_RANGE,
//                ParameterUtils.Depth.FULL_RANGE,
//                0L,
//                JellyfishingBiomes.JELLYFISH_FIELDS
//        );
//        this.addBiome(
//                mapper,
//                ParameterUtils.Temperature.WARM,
//                ParameterUtils.Humidity.WET,
//                ParameterUtils.Continentalness.OCEAN,
//                ParameterUtils.Erosion.FULL_RANGE,
//                ParameterUtils.Weirdness.FULL_RANGE,
//                ParameterUtils.Depth.FULL_RANGE,
//                0L,
//                JellyfishingBiomes.JELLYFISH_FIELDS
//        );
    }
}