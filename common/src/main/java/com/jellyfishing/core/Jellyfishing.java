package com.jellyfishing.core;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.jellyfishing.core.registry.JellyfishingExtras;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.registry.JellyfishingSounds;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.minecraft.resources.ResourceLocation;

public class Jellyfishing {
    public static final String MOD_ID = "jellyfishing";

    public static void onClientInit() {

    }

    public static void init() {
        JellyfishingBlocks.BLOCKS.register();
        JellyfishingItems.ITEMS.register();
        JellyfishingSounds.SOUNDS.register();

        JellyfishingExtras.registerFlammables();
        JellyfishingExtras.registerCompostables();

        EnvExecutor.runInEnv(Env.CLIENT, ()-> Jellyfishing::onClientInit);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}