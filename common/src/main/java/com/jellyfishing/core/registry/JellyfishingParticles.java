package com.jellyfishing.core.registry;

import blueduck.jellyfishing.JellyfishingMod;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JellyfishingParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, JellyfishingMod.MODID);

    public static final RegistrySupplier<SimpleParticleType> CLOUD_PARTICLE = PARTICLES.register("cloud_particle", () -> new SimpleParticleType(false) {});
}