package com.jellyfishing.common.worldgen;

import com.google.common.collect.ImmutableList;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class JellyfishFieldsSurfaceRuleData {
    public static final SurfaceRules.ConditionSource ABOVE_MAX = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRuleData.makeStateRule(JellyfishingBlocks.ALGAE_GRASS.get())), SurfaceRuleData.makeStateRule(JellyfishingBlocks.CORALSTONE.get()));
        SurfaceRules.RuleSource terrain = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(JellyfishingBiomes.JELLYFISH_FIELDS), grassSurface));

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.hole()), SurfaceRules.ifTrue(ABOVE_MAX, terrain)));

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}