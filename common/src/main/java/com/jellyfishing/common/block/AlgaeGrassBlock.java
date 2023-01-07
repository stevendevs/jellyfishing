package com.jellyfishing.common.block;

import java.util.Random;

@SuppressWarnings("deprecation")
public class AlgaeGrassBlock extends Block {
    public AlgaeGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBlockState(pos.above()).canOcclude()) {
            level.setBlockAndUpdate(pos, state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.tick(state, level, pos, random);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (level.getBlockState(pos.above()).canOcclude()) {
            level.setBlockAndUpdate(pos, state);
        }
    }
}