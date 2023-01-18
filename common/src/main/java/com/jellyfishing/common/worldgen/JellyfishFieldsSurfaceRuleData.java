package com.jellyfishing.common.worldgen;

import com.google.common.collect.ImmutableList;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class JellyfishFieldsSurfaceRuleData {
    public static final SurfaceRules.ConditionSource CORAL_CONDITION = SurfaceRules.verticalGradient("coralstone", VerticalAnchor.absolute(8), VerticalAnchor.absolute(0));
    public static final SurfaceRules.ConditionSource ABOVE_9 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(9), 0);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRuleData.makeStateRule(JellyfishingBlocks.ALGAE_GRASS.get())), SurfaceRuleData.makeStateRule(JellyfishingBlocks.CORALSTONE.get()));
        SurfaceRules.RuleSource terrain = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(JellyfishingBiomes.JELLYFISH_FIELDS), grassSurface));

        SurfaceRules.RuleSource deepGeneration = SurfaceRules.ifTrue(CORAL_CONDITION, terrain);
        SurfaceRules.RuleSource generation = SurfaceRules.ifTrue(ABOVE_9, terrain);

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        builder.add(deepGeneration);
        builder.add(generation);

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}