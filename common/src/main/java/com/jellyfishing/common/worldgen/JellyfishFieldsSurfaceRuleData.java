package com.jellyfishing.common.worldgen;

import com.google.common.collect.ImmutableList;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.util.concurrent.atomic.AtomicReference;

public class JellyfishFieldsSurfaceRuleData {
    public static final SurfaceRules.ConditionSource ABOVE_MAX = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0);

    public static SurfaceRules.RuleSource makeRules() {
        AtomicReference<SurfaceRules.RuleSource> grassSurface = new AtomicReference<>();
                JellyfishingBlocks.ALGAE_GRASS.listen((algee)-> JellyfishingBlocks.CORALSTONE.listen((coral)-> grassSurface.set(SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRuleData.makeStateRule(JellyfishingBlocks.ALGAE_GRASS.get())), SurfaceRuleData.makeStateRule(JellyfishingBlocks.CORALSTONE.get())))));
        SurfaceRules.RuleSource terrain = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(JellyfishingBiomes.JELLYFISH_FIELDS), grassSurface.get()));

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.hole()), SurfaceRules.ifTrue(ABOVE_MAX, terrain)));

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}