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
    private double x, y, z;

    public CloudParticle(Level level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super((ClientLevel) level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.xd = 0.1f * (this.random.nextFloat()) - 0.05f + 2;
        this.yd = 0.1f * (this.random.nextFloat()) - 0.05f;
        this.zd = 0.1f * (this.random.nextFloat()) - 0.05f;
        this.x = x;
        this.y = y;
        this.z = z;
        this.quadSize = 10f * (this.random.nextFloat() * 0.5f + 1.7f);
        this.rCol = .5F;
        this.gCol = .5F;
        this.bCol = .5F;
        this.lifetime = (int) (Math.random() * 100.0d) + 2000;
    }

    @Override
    public void tick() {
        this.xo = x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else if (false) {
            this.x += this.xd;
            this.y += this.yd;
            this.z += this.zd;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteSet spriteSet) implements ParticleProvider<SimpleParticleType> {
        @Override
        public @NotNull Particle createParticle(SimpleParticleType parameters, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            if (level.isDay()) {
                y = 148;
            } else {
                y = 0;
            }
            CloudParticle particle = new CloudParticle(level, x + (256 * level.random.nextDouble()) - 128, y, z + (256 * level.random.nextDouble()) - 128, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(spriteSet);
            return particle;
        }
    }
}