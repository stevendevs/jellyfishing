package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.concurrent.atomic.AtomicReference;

public class JellyfishingSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> STING = create("entity.jellyfish.sting");

    public static final RegistrySupplier<SoundEvent> JELLYFISH_FIELDS = create("music.jellyfishfields");
    public static final RegistrySupplier<SoundEvent> BACKGROUND_MUSIC = create("music.general");

    public static RegistrySupplier<SoundEvent> create(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Jellyfishing.MOD_ID, id)));
    }
}