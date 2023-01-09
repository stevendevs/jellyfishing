package com.jellyfishing.core.fabric;

import com.jellyfishing.common.worldgen.JellyfishFieldsSurfaceRuleData;
import com.jellyfishing.common.worldgen.JellyfishingBiomeRegion;
import com.jellyfishing.core.Jellyfishing;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class JellyBlender implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new JellyfishingBiomeRegion());

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Jellyfishing.MOD_ID, JellyfishFieldsSurfaceRuleData.makeRules());
    }
}