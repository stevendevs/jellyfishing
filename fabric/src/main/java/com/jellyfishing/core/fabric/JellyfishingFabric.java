package com.jellyfishing.core.fabric;

import com.jellyfishing.core.Jellyfishing;
import net.fabricmc.api.ModInitializer;

public class JellyfishingFabric implements ModInitializer {

    @Override
    public void onInitialize() {
//        ForgeConfigRegistry.INSTANCE.register(Jellyfishing.MOD_ID, ModConfig.Type.COMMON, JellyfishingConfig.COMMON_CONFIG);
//        ForgeConfigRegistry.INSTANCE.register(Jellyfishing.MOD_ID, ModConfig.Type.CLIENT, JellyfishingConfig.CLIENT_CONFIG);

        Jellyfishing.init();
    }
}