package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin {
    @Shadow @Final protected FlowableFluid fluid;

    @Shadow protected abstract void playExtinguishSound(WorldAccess world, BlockPos pos);

    @Inject(method = "receiveNeighborFluids", at = @At("RETURN"), cancellable = true)
    private void receiveNeighborFluids(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (this.fluid.isIn(FluidTags.LAVA)) {
            var jelly = world.getBlockState(pos.down()).isOf(JellyfishingBlocks.JELLY_BLOCK);
            var directions = Direction.values();
            for (Direction direction : directions) {
                if (direction != Direction.DOWN) {
                    var pos2 = pos.offset(direction);
                    if (jelly && world.getFluidState(pos2).isIn(FluidTags.WATER)) {
                        world.setBlockState(pos, JellyfishingBlocks.CORALSTONE.getDefaultState());
                        this.playExtinguishSound(world, pos);
                        cir.setReturnValue(false);
                    }
                }
            }
        }

        cir.setReturnValue(true);
    }
}