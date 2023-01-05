package com.jellyfishing.common.block;

import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class AlgaeGrassBlock extends Block {

    public AlgaeGrassBlock(Properties properties) {
        super(properties);
    }

    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        if (worldIn.getBlockState(pos.above()).isSolid()) {
            worldIn.setBlockState(pos, state);
        }
    }

    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        this.tick(state, worldIn, pos, random);
    }

    public void onBlockAdded(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (worldIn.getBlockState(pos.above()).isSolid()) {
            worldIn.setBlockState(pos, state);
        }
    }
}
