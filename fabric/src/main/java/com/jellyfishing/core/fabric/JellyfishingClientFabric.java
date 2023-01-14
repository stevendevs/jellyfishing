package com.jellyfishing.core.fabric;

import com.jellyfishing.client.renderer.SpatulaItemRender;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.JellyfishingClient;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.util.fabric.ClientUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

public class JellyfishingClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        JellyfishingClient.onClientInit();

//        BuiltinItemRendererRegistry.INSTANCE.register(JellyfishingItems.SPATULA.get(), SpatulaItemRender.INSTANCE);

//        ClientUtil.registerMultiplePerspective(JellyfishingItems.SPATULA.get(), JellyfishingItems.GOLDEN_SPATULA.get());
    }
}