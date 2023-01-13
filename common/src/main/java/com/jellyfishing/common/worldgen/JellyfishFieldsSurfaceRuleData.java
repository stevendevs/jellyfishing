package com.jellyfishing.common.worldgen;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class JellyfishFieldsSurfaceRuleData {
    public static final SurfaceRules.ConditionSource ABOVE_9 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(9), 0);

    public static SurfaceRules.RuleSource makeRules() {
//        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(BuiltInRegistries.BLOCK.get(Jellyfishing.id("algae_grass")).defaultBlockState())), SurfaceRules.state(BuiltInRegistries.BLOCK.get(Jellyfishing.id("coralstone")).defaultBlockState()));
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(BuiltInRegistries.BLOCK.get(Jellyfishing.id("algae_grass")).defaultBlockState())), SurfaceRules.state(BuiltInRegistries.BLOCK.get(Jellyfishing.id("coralstone")).defaultBlockState()));

        SurfaceRules.RuleSource terrain = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(JellyfishingBiomes.JELLYFISH_FIELDS), grassSurface));
        SurfaceRules.RuleSource generation = SurfaceRules.sequence(SurfaceRules.ifTrue(ABOVE_9, terrain));
        return SurfaceRules.sequence(generation);
    }
}