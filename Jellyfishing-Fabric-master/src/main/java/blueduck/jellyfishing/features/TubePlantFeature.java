package blueduck.jellyfishing.features;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class TubePlantFeature extends Feature<ProbabilityConfig> {
    public TubePlantFeature(Codec<ProbabilityConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<ProbabilityConfig> context){
        var probabilityGen = 0;

        for(var probabilityZero = 0; probabilityZero < context.getConfig().probability; ++probabilityZero) {
            var random1 = context.getRandom().nextInt(8) - context.getRandom().nextInt(8);
            var random2 = context.getRandom().nextInt(8) - context.getRandom().nextInt(8);
            var topY = context.getWorld().getTopY(Heightmap.Type.OCEAN_FLOOR, context.getOrigin().getX() + random1, context.getOrigin().getZ() + random2);
            var blockpos = new BlockPos(context.getOrigin().getX() + random1, topY, context.getOrigin().getZ() + random2);
            var blockstate = JellyfishingBlocks.TUBE_PLANT.getDefaultState();
            if (context.getWorld().getBlockState(blockpos).getBlock() == Blocks.WATER && blockstate.canPlaceAt(context.getWorld(), blockpos)) {
                context.getWorld().setBlockState(blockpos, blockstate, 2);
                ++probabilityGen;
            }
        }

        return probabilityGen > 0;
    }
}