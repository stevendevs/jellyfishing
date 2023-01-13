package com.jellyfishing.common.features;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class TubePlantFeature extends Feature<NoneFeatureConfiguration> {
    public TubePlantFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context){
        int i = 0;

        WorldGenLevel worldGenLevel = context.level();
        BlockPos blockPos = context.origin();
        RandomSource randomSource = context.random();

        int randomX = randomSource.nextInt(8) - randomSource.nextInt(8);
        int randomZ = randomSource.nextInt(8) - randomSource.nextInt(8);

        int j = worldGenLevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockPos.getX() + randomX, blockPos.getZ() + randomZ);
        BlockPos blockPos2 = new BlockPos(blockPos.getX() + randomX, j, blockPos.getZ() + randomZ);

        if (worldGenLevel.getBlockState(blockPos2).is(Blocks.WATER)) {
            BlockState blockState = JellyfishingBlocks.TUBE_PLANT.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);

            for(int l = 0; l <= 1; ++l) {
                if (worldGenLevel.getBlockState(blockPos2).is(Blocks.WATER) && worldGenLevel.getBlockState(blockPos2.above()).is(Blocks.WATER) && blockState.canSurvive(worldGenLevel, blockPos2)) {
                    worldGenLevel.setBlock(blockPos2, blockState, 2);
                    ++i;
                }
            }
        }

        return i > 0;
    }
}