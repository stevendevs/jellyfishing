package com.jellyfishing.core.fabric;

import com.jellyfishing.core.Jellyfishing;
import net.fabricmc.api.ModInitializer;

public class JellyfishingFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Jellyfishing.init();
    }
}