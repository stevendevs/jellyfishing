package com.jellyfishing.core.forge;

import com.jellyfishing.core.Jellyfishing;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Jellyfishing.MOD_ID)
public class JellyfishingForge {
    public JellyfishingForge() {
        EventBuses.registerModEventBus(Jellyfishing.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Jellyfishing.init();
    }
}
