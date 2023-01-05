package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class JellyfishingSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Jellyfishing.MOD_ID, Registry.SOUND_EVENT_REGISTRY);

    public static final RegistrySupplier<SoundEvent> STING = SOUNDS.register("sting", () -> new SoundEvent(new ResourceLocation("jellyfishing", "entity.jellyfish.sting")));

    public static final RegistrySupplier<SoundEvent> JELLYFISH_FIELDS = SOUNDS.register("jellyfish_fields", () -> new SoundEvent(new ResourceLocation("jellyfishing", "music.jellyfishfields")));
    public static final RegistrySupplier<SoundEvent> BACKGROUND_MUSIC = SOUNDS.register("background_music", () -> new SoundEvent(new ResourceLocation("jellyfishing", "music.general")));

    public static final SoundType CARPETED_WOOD = new SoundType(1.0F, 1.0F, SoundEvents.WOOD_BREAK, SoundEvents.WOOL_STEP, SoundEvents.WOOD_PLACE, SoundEvents.WOOD_HIT, SoundEvents.WOOL_FALL);
}