package com.jellyfishing.core.mixin.client;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HoneyBlock.class)
public abstract class HoneyBlockMixin extends Block {
    public HoneyBlockMixin(Properties settings) {
        super(settings);
    }

//    @Inject(method = "addRegularParticles", at = @At("HEAD"), cancellable = true)
//    private static void addRegularParticles(Entity entity, CallbackInfo ci) {
//        if (entity.getBlockStateAtPos().getBlock() == JellyfishingBlocks.JELLY_BLOCK) {
//            addJellyParticles(entity, 5, ci);
//        }
//    }
//
//    @Inject(method = "addRichParticles", at = @At("HEAD"), cancellable = true)
//    private static void addRichParticles(Entity entity, CallbackInfo ci) {
//        if (entity.getBlockStateAtPos().getBlock() == JellyfishingBlocks.JELLY_BLOCK) {
//            addJellyParticles(entity, 5, ci);
//        }
//    }

    @Inject(method = "showParticles", at = @At("HEAD"), cancellable = true)
    private static void addJellyParticles(Entity entity, int count, CallbackInfo ci) {
        if (entity.getFeetBlockState().getBlock() == JellyfishingBlocks.JELLY_BLOCK.get()) {
            if (entity.level.isClientSide) {
                BlockState blockstate = JellyfishingBlocks.JELLY_BLOCK.get().defaultBlockState();

                for(int i = 0; i < count; ++i) {
                    entity.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), entity.getX(), entity.getY(), entity.getZ(), 0.0D, 0.0D, 0.0D);
                }
                ci.cancel();
            }
        }
    }
}