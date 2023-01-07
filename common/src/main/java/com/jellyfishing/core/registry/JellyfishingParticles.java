package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

public class JellyfishingParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.PARTICLE_TYPE);

    public static final RegistrySupplier<SimpleParticleType> CLOUD_PARTICLE = PARTICLES.register("cloud_particle", () -> new SimpleParticleType(false) {});
}