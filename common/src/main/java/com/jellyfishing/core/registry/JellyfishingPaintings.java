package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;

@SuppressWarnings({ "unused"})
public class JellyfishingPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.PAINTING_VARIANT);

    public static final RegistrySupplier<PaintingVariant> CAPTAIN = create("captain", 64, 48);
    public static final RegistrySupplier<PaintingVariant> BOLD_AND_BRASH = create("bold_and_brash", 16, 32);
    public static final RegistrySupplier<PaintingVariant> JELLYFISH = create("jellyfish", 16, 16);
    public static final RegistrySupplier<PaintingVariant> JELLYFISH_FIELDS = create("jellyfish_fields", 32, 16);
    public static final RegistrySupplier<PaintingVariant> PATTY_WAGON = create("patty_wagon", 32, 32);
    public static final RegistrySupplier<PaintingVariant> MILLIONTH_DOLLAR = create("millionth_dollar", 32, 16);

    private static RegistrySupplier<PaintingVariant> create(String name, int x, int y) {
        return PAINTINGS.register(name, () -> new PaintingVariant(x, y));
    }

    public static void init() {
        PAINTINGS.register();
    }
}