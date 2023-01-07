package com.jellyfishing.common.misc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CloudParticle extends TextureSheetParticle {
    private double posX, posY, posZ;

    public CloudParticle(Level world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super((ClientLevel) world, x, y, z, velocityX, velocityY, velocityZ);

        this.xd = 0.1f * (this.random.nextFloat()) - 0.05f + 2;
        this.yd = 0.1f * (this.random.nextFloat()) - 0.05f;
        this.zd = 0.1f * (this.random.nextFloat()) - 0.05f;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.scale(10f * (this.random.nextFloat() * 0.5f + 1.7f));
        this.rCol = .5F;
        this.gCol = .5F;
        this.bCol = .5F;
        this.lifetime = (int) (Math.random() * 100.0d) + 2000;
    }

    @Override
    public void tick() {
        this.xd = posX;
        this.yd = this.posY;
        this.zd = this.posZ;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else if (false) {
            this.posX += this.xd;
            this.posY += this.yd;
            this.posZ += this.zd;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteSet spriteProvider) implements ParticleProvider<SimpleParticleType> {
        @Override
        public @NotNull Particle createParticle(SimpleParticleType parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            if (world.isDay()) {
                y = 148;
            } else {
                y = 0;
            }
            var particle = new CloudParticle(world, x + (256 * world.random.nextDouble()) - 128, y, z + (256 * world.random.nextDouble()) - 128, velocityX, velocityY, velocityZ);
            particle.setSprite(spriteProvider.get(world.random));
            return particle;
        }
    }
}