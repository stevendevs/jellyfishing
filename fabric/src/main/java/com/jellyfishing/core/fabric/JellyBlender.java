package com.jellyfishing.core.fabric;

import com.jellyfishing.common.worldgen.JellyfishFieldsSurfaceRuleData;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import java.util.function.Consumer;

public class JellyBlender implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new JellyfishingBiomeRegion());

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Jellyfishing.MOD_ID, JellyfishFieldsSurfaceRuleData.makeRules());
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