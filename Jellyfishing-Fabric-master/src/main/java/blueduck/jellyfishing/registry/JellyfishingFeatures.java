package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.features.CoralPlantFeature;
import blueduck.jellyfishing.features.PineapplePlantFeature;
import blueduck.jellyfishing.features.SeanutBushFeature;
import blueduck.jellyfishing.features.TubePlantFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.*;

public class JellyfishingFeatures {
    public static final Feature<ProbabilityConfig> CORAL_PLANT_FEATURE = register("coral_plant_feature", new CoralPlantFeature(ProbabilityConfig.CODEC));
    public static final Feature<ProbabilityConfig> TUBE_PLANT_FEATURE = register("tube_plant_feature", new TubePlantFeature(ProbabilityConfig.CODEC));
    public static final Feature<ProbabilityConfig> SEANUT_BUSH_FEATURE = register("seanut_bush_feature", new SeanutBushFeature(ProbabilityConfig.CODEC));
    public static final Feature<ProbabilityConfig> PINEAPPLE_PLANT_FEATURE = register("pineapple_plant_feature", new PineapplePlantFeature(ProbabilityConfig.CODEC));

    //TODO: fix this just for porting purposes
//   public static void registerConfiguredFeatures() {
//       register("coral_plant_feature", CORAL_PLANT_FEATURE.withConfiguration(new ProbabilityConfig(.5F)).range(32).square().func_242731_b(100));
//       register("tube_plant_feature", TUBE_PLANT_FEATURE.withConfiguration(new ProbabilityConfig(.1F)).range(32).square().func_242731_b(100));
//       register("seanut_bush_feature", SEANUT_BUSH_FEATURE.withConfiguration(new ProbabilityConfig(.2F)).range(32).square().func_242731_b(100));
//       register("coralstone_replacement", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, JellyfishingBlocks.CORALSTONE.getDefaultState(), 100)).range(300).square().func_242731_b(50));
//   }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Jellyfishing.id(name), feature);
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, Jellyfishing.id(name), feature);
    }
}