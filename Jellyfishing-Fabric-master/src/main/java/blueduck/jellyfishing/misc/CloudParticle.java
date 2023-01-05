package blueduck.jellyfishing.misc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CloudParticle extends SpriteBillboardParticle {
    private double posX, posY, posZ;

    public CloudParticle(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super((ClientWorld) world, x, y, z, velocityX, velocityY, velocityZ);

        this.velocityX = 0.1f * (this.random.nextFloat()) - 0.05f + 2;
        this.velocityY = 0.1f * (this.random.nextFloat()) - 0.05f;
        this.velocityZ = 0.1f * (this.random.nextFloat()) - 0.05f;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.scale = 10f * (this.random.nextFloat() * 0.5f + 1.7f);
        this.colorRed = .5F;
        this.colorGreen = .5F;
        this.colorBlue = .5F;
        this.maxAge = (int) (Math.random() * 100.0d) + 2000;
    }

    @Override
    public void tick() {
        this.prevPosX = posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else if (false) {
            this.posX += this.velocityX;
            this.posY += this.velocityY;
            this.posZ += this.velocityZ;
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public @NotNull Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            if (world.isDay()) {
                y = 148;
            } else {
                y = 0;
            }
            var particle = new CloudParticle(world, x + (256 * world.random.nextDouble()) - 128, y, z + (256 * world.random.nextDouble()) - 128, velocityX, velocityY, velocityZ);
            particle.setSprite(spriteProvider);
            return particle;
        }
    }
}