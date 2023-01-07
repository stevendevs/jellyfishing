package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;

@SuppressWarnings({ "unused"})
public class  JellyfishingPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.PAINTING_VARIANT);

    public static final RegistrySupplier<PaintingVariant> CAPTAIN = PAINTINGS.register("captain", ()-> new PaintingVariant(64, 48));
    public static final RegistrySupplier<PaintingVariant> BOLD_AND_BRASH = PAINTINGS.register("bold_and_brash", ()-> new PaintingVariant(16, 32));
    public static final RegistrySupplier<PaintingVariant> JELLYFISH = PAINTINGS.register("jellyfish", ()-> new PaintingVariant(16, 16));
    public static final RegistrySupplier<PaintingVariant> JELLYFISH_FIELDS = PAINTINGS.register("jellyfish_fields", ()-> new PaintingVariant(32, 16));
    public static final RegistrySupplier<PaintingVariant> PATTY_WAGON = PAINTINGS.register("patty_wagon", ()-> new PaintingVariant(32, 32));
    public static final RegistrySupplier<PaintingVariant> MILLIONTH_DOLLAR = PAINTINGS.register("millionth_dollar", ()-> new PaintingVariant(32, 16));
}