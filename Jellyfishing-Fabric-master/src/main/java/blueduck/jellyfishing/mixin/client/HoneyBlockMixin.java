package blueduck.jellyfishing.mixin.client;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HoneyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HoneyBlock.class)
public abstract class HoneyBlockMixin extends Block {
    public HoneyBlockMixin(Settings settings) {
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

    @Inject(method = "addParticles", at = @At("HEAD"), cancellable = true)
    private static void addJellyParticles(Entity entity, int count, CallbackInfo ci) {
        if (entity.getBlockStateAtPos().getBlock() == JellyfishingBlocks.JELLY_BLOCK) {
            if (entity.world.isClient) {
                BlockState blockstate = JellyfishingBlocks.JELLY_BLOCK.getDefaultState();

                for(int i = 0; i < count; ++i) {
                    entity.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockstate), entity.getX(), entity.getY(), entity.getZ(), 0.0D, 0.0D, 0.0D);
                }
                ci.cancel();
            }
        }
    }
}