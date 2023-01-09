package com.jellyfishing.common.worldgen;

import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class JellyfishFieldsSurfaceRuleData {
    private static final SurfaceRules.RuleSource CORALSTONE = makeStateRule(JellyfishingBlocks.CORALSTONE.get());
    private static final SurfaceRules.RuleSource ALGAE_GRASS = makeStateRule(JellyfishingBlocks.ALGAE_GRASS.get());

    public static final SurfaceRules.ConditionSource ABOVE_9 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(9), 0);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ALGAE_GRASS), CORALSTONE);

        SurfaceRules.RuleSource terrain = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(JellyfishingBiomes.JELLYFISH_FIELDS), grassSurface));
        SurfaceRules.RuleSource generation = SurfaceRules.sequence(SurfaceRules.ifTrue(ABOVE_9, terrain));
        return SurfaceRules.sequence(generation);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}