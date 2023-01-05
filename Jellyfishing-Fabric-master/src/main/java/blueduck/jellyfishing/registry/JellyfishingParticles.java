package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.Registry;

public class JellyfishingParticles {
    public static final DefaultParticleType CLOUD_PARTICLE = register("cloud_particle", false);

    private static DefaultParticleType register(String id, boolean alwaysShow) {
        return Registry.register(Registry.PARTICLE_TYPE, Jellyfishing.id(id), new DefaultParticleType(alwaysShow));
    }
}