package com.jellyfishing.common.features;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class PineapplePlantFeature extends Feature<ProbabilityFeatureConfiguration> {
    public PineapplePlantFeature(Codec<ProbabilityFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
        var probabilityGen = 0;

        for(var probabilityZero = 0; probabilityZero < context.config().probability; ++probabilityZero) {
            var random1 = context.random().nextInt(8) - context.random().nextInt(8);
            var random2 = context.random().nextInt(8) - context.random().nextInt(8);
            var topY = context.level().getHeight(Heightmap.Types.WORLD_SURFACE_WG, context.origin().getX() + random1, context.origin().getZ() + random2);
            BlockPos blockpos = new BlockPos(context.origin().getX() + random1, topY, context.origin().getZ() + random2);
            BlockState blockstate = JellyfishingBlocks.PINEAPPLE_PLANT.get().defaultBlockState().setValue(BlockStateProperties.AGE_3, context.random().nextInt(2) + 2);
            if (blockstate.canSurvive(context.level(), blockpos)) {
                context.level().setBlock(blockpos, blockstate, 2);
                ++probabilityGen;
            }
        }

        return probabilityGen > 0;
    }
}