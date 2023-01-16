package com.jellyfishing.core.fabric;

import com.jellyfishing.client.JellyfishingClient;
import net.fabricmc.api.ClientModInitializer;

public class JellyfishingClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        JellyfishingClient.onClientInit();

//        BuiltinItemRendererRegistry.INSTANCE.register(JellyfishingItems.SPATULA.get(), SpatulaItemRender.INSTANCE);

//        ClientUtil.registerMultiplePerspective(JellyfishingItems.SPATULA.get(), JellyfishingItems.GOLDEN_SPATULA.get());
    }
}