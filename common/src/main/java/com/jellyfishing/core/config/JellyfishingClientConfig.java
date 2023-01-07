package com.jellyfishing.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JellyfishingClientConfig {
    public static ForgeConfigSpec CLIENT_CONFIG;

    // CLIENT //
    public static final String CATEGORY_SETTINGS = "General";

    public static ForgeConfigSpec.ConfigValue<Boolean> MUSIC;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General");
        MUSIC = CLIENT_BUILDER.comment("Enable custom music in the Jellyfish Fields?")
                .define("music", true, o -> o instanceof Boolean);

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}