package com.jellyfishing.common.features;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class SeanutBushFeature extends Feature<ProbabilityFeatureConfiguration> {
    public SeanutBushFeature(Codec<ProbabilityFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
        var probabilityGen = 0;

        for(var probabilityZero = 0; probabilityZero < context.config().probability; ++probabilityZero) {
            var random1 = context.random().nextInt(8) - context.random().nextInt(8);
            var random2 = context.random().nextInt(8) - context.random().nextInt(8);
            var topY = context.level().getHeight(Heightmap.Types.OCEAN_FLOOR, context.origin().getX() + random1, context.origin().getZ() + random2);
            BlockPos blockpos = new BlockPos(context.origin().getX() + random1, topY, context.origin().getZ() + random2);
            BlockState blockstate = JellyfishingBlocks.SEANUT_BUSH.get().defaultBlockState().setValue(BlockStateProperties.AGE_3, 3);
            if (context.level().getBlockState(blockpos).getBlock() == Blocks.WATER && blockstate.canSurvive(context.level(), blockpos)) {
                context.level().setBlock(blockpos, blockstate, 2);
                ++probabilityGen;
            }
        }

        return probabilityGen > 0;
    }
}
