package com.jellyfishing.core.mixin;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.jellyfishing.core.registry.JellyfishingTags;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonStructureResolver.class)
public class PistonHandlerMixin {
    @Inject(method = "isSticky", at = @At("HEAD"), cancellable = true)
    private static void isSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        var block = state.getBlock();
        if (block == JellyfishingBlocks.JELLY_BLOCK.get() || block == JellyfishingBlocks.BLUE_JELLY_BLOCK.get()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canStickToEachOther", at = @At("HEAD"), cancellable = true)
    private static void canStickToEachOther(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() != adjacentState.getBlock() && state.is(JellyfishingTags.JELLY_BLOCKS) && adjacentState.is(JellyfishingTags.JELLY_BLOCKS)) {
            cir.setReturnValue(false);
        }
    }
}