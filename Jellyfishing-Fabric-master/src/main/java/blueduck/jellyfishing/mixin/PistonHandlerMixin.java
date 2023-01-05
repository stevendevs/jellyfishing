package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import blueduck.jellyfishing.registry.JellyfishingTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonHandler.class)
public class PistonHandlerMixin {
    @Inject(method = "isBlockSticky", at = @At("HEAD"), cancellable = true)
    private static void isBlockSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        var block = state.getBlock();
        if (block == JellyfishingBlocks.JELLY_BLOCK || block == JellyfishingBlocks.BLUE_JELLY_BLOCK) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isAdjacentBlockStuck", at = @At("HEAD"), cancellable = true)
    private static void isAdjacentBlockStuck(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        var block = state.getBlock();
        var adjacentBlock = adjacentState.getBlock();
        if (block != adjacentBlock && JellyfishingTags.JELLY_BLOCKS.contains(block) && JellyfishingTags.JELLY_BLOCKS.contains(adjacentState.getBlock())) {
            cir.setReturnValue(false);
        }
    }
}