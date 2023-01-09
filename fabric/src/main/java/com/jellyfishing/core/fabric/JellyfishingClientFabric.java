package com.jellyfishing.core.fabric;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.JellyfishingClient;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.util.fabric.ClientUtil;
import net.fabricmc.api.ClientModInitializer;

public class JellyfishingClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        JellyfishingClient.onClientInit();
        ClientUtil.registerMultiplePerspective(JellyfishingItems.SPATULA.get(), JellyfishingItems.GOLDEN_SPATULA.get());
    }
}