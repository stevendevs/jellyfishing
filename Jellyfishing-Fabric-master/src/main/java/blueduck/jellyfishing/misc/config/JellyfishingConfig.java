package blueduck.jellyfishing.misc.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JellyfishingConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;
    public static final String CATEGORY_SETTINGS = "general";

    public static ForgeConfigSpec.ConfigValue<Integer> BIOME_WEIGHT;
    public static ForgeConfigSpec.ConfigValue<Boolean> JELLYFISH_STING;
    public static ForgeConfigSpec.ConfigValue<Boolean> CAUGHT_JELLYFISH_STING;
    public static ForgeConfigSpec.ConfigValue<Boolean> JELLYFISH_FISHABLE;
    public static ForgeConfigSpec.ConfigValue<Boolean> NETS_FISHABLE;

    public static ForgeConfigSpec.ConfigValue<Integer> SPATULA_DAMAGE;
    public static ForgeConfigSpec.ConfigValue<Integer> GOLDEN_SPATULA_DAMAGE;
    public static ForgeConfigSpec.ConfigValue<Integer> KARATE_DAMAGE;
    public static ForgeConfigSpec.ConfigValue<Integer> MASTER_KARATE_DAMAGE;
    public static ForgeConfigSpec.ConfigValue<Integer> POWER_KARATE_DAMAGE;

    public static ForgeConfigSpec.ConfigValue<Boolean> FLOWER_CLOUDS;

    public static ForgeConfigSpec.ConfigValue<Boolean> NOPLACE_JELLYFISH;

    public static ForgeConfigSpec.ConfigValue<Boolean> MUSIC;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_SETTINGS);
        BIOME_WEIGHT = CLIENT_BUILDER.comment("Biome Weight of Jellyfish Fields")
                .defineInRange("jellyfish_fields_weight", 6, 1, 100);
        JELLYFISH_STING = CLIENT_BUILDER.comment("Should Jellyfish sting?")
                .define("jellyfish_sting", true, o -> o instanceof Boolean);
        CAUGHT_JELLYFISH_STING = CLIENT_BUILDER.comment("Should Caught and Released Jellyfish sting?")
                .define("caught_jellyfish_sting", false, o -> o instanceof Boolean);
        JELLYFISH_FISHABLE = CLIENT_BUILDER.comment("Should Jellyfish be fishable with a fishing rod?")
                .define("jellyfish_fishable", true, o -> o instanceof Boolean);
        NETS_FISHABLE  = CLIENT_BUILDER.comment("Should Jellyfish Nets be fishable with a fishing rod?")
                .define("nets_fishable", true, (o) -> o instanceof Boolean);
        COMMON_BUILDER.push("Attack Damages");
        SPATULA_DAMAGE = CLIENT_BUILDER.comment("Damage of the Spatula")
                .defineInRange("spatula_damage", 4, 1, 1000);
        GOLDEN_SPATULA_DAMAGE = CLIENT_BUILDER.comment("Damage of the Golden Spatula")
                .defineInRange("golden_spatula_damage", 6, 1, 1000);
        KARATE_DAMAGE = CLIENT_BUILDER.comment("Damage of the Karate Glove")
                .defineInRange("karate_damage", 3, 1, 1000);
        MASTER_KARATE_DAMAGE = CLIENT_BUILDER.comment("Damage of the Master Karate Glove")
                .defineInRange("master_karate_damage", 5, 1, 1000);
        POWER_KARATE_DAMAGE = CLIENT_BUILDER.comment("Damage of the Power Karate Glove")
                .defineInRange("power_karate_damage", 18, 1, 1000);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Biome");
        FLOWER_CLOUDS  = CLIENT_BUILDER.comment("Enable Flower Clouds?")
                .define("flower_clouds", true, o -> o instanceof Boolean);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Optimization");
        NOPLACE_JELLYFISH  = CLIENT_BUILDER.comment("Should Jellyfish be non-placeable? (Useful for servers as it will prevent entity lag from placed jellyfish)")
                .comment("With this enabled, right clicking will produce a few of the jellyfish's jelly item and destroy the jellyfish item")
                .define("no_place_jellyfish", false, o -> o instanceof Boolean);

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();


        CLIENT_BUILDER.push(CATEGORY_SETTINGS);
        MUSIC = CLIENT_BUILDER.comment("Enable custom music in the Jellyfish Fields?")
                .define("music", true, o -> o instanceof Boolean);
        CLIENT_BUILDER.pop();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}