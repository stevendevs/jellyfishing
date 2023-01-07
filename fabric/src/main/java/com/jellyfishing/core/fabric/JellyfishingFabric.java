package com.jellyfishing.core.fabric;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.config.JellyfishingConfig;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.util.fabric.ClientUtil;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.fml.config.ModConfig;
import terrablender.api.TerraBlenderApi;

public class JellyfishingFabric implements ModInitializer, ClientModInitializer, TerraBlenderApi {

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(Jellyfishing.MOD_ID, ModConfig.Type.COMMON, JellyfishingConfig.COMMON_CONFIG);
        ForgeConfigRegistry.INSTANCE.register(Jellyfishing.MOD_ID, ModConfig.Type.CLIENT, JellyfishingConfig.CLIENT_CONFIG);

        Jellyfishing.init();
    }

    @Override
    public void onInitializeClient() {
        ClientUtil.registerMultiplePerspective(JellyfishingItems.SPATULA.get(), JellyfishingItems.GOLDEN_SPATULA.get());
    }

    @Override
    public void onTerraBlenderInitialized() {

    }


}