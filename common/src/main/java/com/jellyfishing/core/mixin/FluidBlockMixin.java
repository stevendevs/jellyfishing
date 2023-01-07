package com.jellyfishing.core.mixin;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public abstract class FluidBlockMixin {
    @Shadow @Final protected FlowingFluid fluid;

    @Shadow protected abstract void fizz(LevelAccessor world, BlockPos pos);

    @Inject(method = "shouldSpreadLiquid", at = @At("RETURN"), cancellable = true)
    private void receiveNeighborFluids(Level world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (this.fluid.is(FluidTags.LAVA)) {
            var jelly = world.getBlockState(pos.below()).is(JellyfishingBlocks.JELLY_BLOCK.get());
            var directions = Direction.values();
            for (Direction direction : directions) {
                if (direction != Direction.DOWN) {
                    var pos2 = pos.offset(direction.getNormal());
                    if (jelly && world.getFluidState(pos2).is(FluidTags.WATER)) {
                        world.setBlockAndUpdate(pos, JellyfishingBlocks.CORALSTONE.get().defaultBlockState());
                        this.fizz
                                (world, pos);
                        cir.setReturnValue(false);
                    }
                }
            }
        }

        cir.setReturnValue(true);
    }
}