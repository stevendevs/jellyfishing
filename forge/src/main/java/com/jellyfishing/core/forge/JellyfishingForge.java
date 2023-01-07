package com.jellyfishing.core.forge;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.config.JellyfishingConfig;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Jellyfishing.MOD_ID)
public class JellyfishingForge {
    public JellyfishingForge() {
        EventBuses.registerModEventBus(Jellyfishing.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Jellyfishing.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, JellyfishingConfig.COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, JellyfishingConfig.CLIENT_CONFIG);
    }
}
