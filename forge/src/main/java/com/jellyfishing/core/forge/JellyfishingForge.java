package com.jellyfishing.core.forge;

import com.jellyfishing.client.JellyfishingClient;
import com.jellyfishing.common.worldgen.JellyfishFieldsSurfaceRuleData;
import com.jellyfishing.common.worldgen.JellyfishingBiomeRegion;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingExtras;
import dev.architectury.forge.ArchitecturyForge;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.level.entity.forge.EntityAttributeRegistryImpl;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(Jellyfishing.MOD_ID)
public class JellyfishingForge {
    public JellyfishingForge() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Jellyfishing.MOD_ID, bus);
        Jellyfishing.init();
        EnvExecutor.runInEnv(Env.CLIENT, ()-> JellyfishingClient::onClientInit);

        bus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new JellyfishingBiomeRegion());

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Jellyfishing.MOD_ID, JellyfishFieldsSurfaceRuleData.makeRules());
        });
    }
}