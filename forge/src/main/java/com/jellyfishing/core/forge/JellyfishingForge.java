package com.jellyfishing.core.forge;

import com.jellyfishing.client.JellyfishingClient;
import com.jellyfishing.common.worldgen.JellyfishFieldsSurfaceRuleData;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.mojang.datafixers.util.Pair;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.util.function.Consumer;

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

    public static class JellyfishingBiomeRegion extends Region {
        public JellyfishingBiomeRegion() {
            super(Jellyfishing.id("biome_region"), RegionType.OVERWORLD, 6);
        }

        @Override
        public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
            this.addModifiedVanillaOverworldBiomes(mapper, builder -> builder.replaceBiome(Biomes.WARM_OCEAN, JellyfishingBiomes.JELLYFISH_FIELDS));
        }
    }
}