package blueduck.jellyfishing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings({"deprecation"})
public class AlgaeGrassBlock extends Block {
    public AlgaeGrassBlock(Settings properties) {
        super(properties);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockState(pos.up()).isOpaque()) {
            world.setBlockState(pos, state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.scheduledTick(state, world, pos, random);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (world.getBlockState(pos.up()).isOpaque()) {
            world.setBlockState(pos, state);
        }
    }
}