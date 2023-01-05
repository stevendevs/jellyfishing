package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.blocks.PineapplePlant;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.TrapezoidHeightProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class JellyfishingConfiguredFeatures {
    //TODO
//    public static final ConfiguredFeature<?, ?> CONFIGURED_CORAL_PLANT = JellyfishingFeatures.CORAL_PLANT_FEATURE.configure(new ProbabilityConfig(.5F)).range((new RangeDecoratorConfig(TrapezoidHeightProvider.create(YOffset.fixed(0), YOffset.fixed(0), 32)))).spreadHorizontally().repeat(5);
//    public static final ConfiguredFeature<?, ?> CONFIGURED_TUBE_PLANT = JellyfishingFeatures.TUBE_PLANT_FEATURE.configure(new ProbabilityConfig(.2F)).range((new RangeDecoratorConfig(TrapezoidHeightProvider.create(YOffset.fixed(0), YOffset.fixed(0), 32)))).spreadHorizontally().repeat(2);
//    public static final ConfiguredFeature<?, ?> CONFIGURED_SEANUT_BUSH = JellyfishingFeatures.SEANUT_BUSH_FEATURE.configure(new ProbabilityConfig(.03F)).range((new RangeDecoratorConfig(TrapezoidHeightProvider.create(YOffset.fixed(0), YOffset.fixed(0), 32)))).spreadHorizontally().repeat(2);
//    public static final ConfiguredFeature<?, ?> CONFIGURED_PINEAPPLE_PLANT = JellyfishingFeatures.PINEAPPLE_PLANT_FEATURE.configure(new ProbabilityConfig(.05F)).range((new RangeDecoratorConfig(TrapezoidHeightProvider.create(YOffset.fixed(0), YOffset.fixed(0), 32)))).spreadHorizontally().repeat(1);
//    public static final ConfiguredFeature<?, ?> CONFIGURED_PINEAPPLE_PLANT_PATCH = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(JellyfishingBlocks.PINEAPPLE_PLANT.getDefaultState().with(PineapplePlant.AGE, 3)), SimpleBlockPlacer.INSTANCE).tries(4).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).canReplace().build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE);
//    public static final ConfiguredFeature<?, ?> CONFIGURED_CORALSTONE_REPLACEMENT = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, JellyfishingBlocks.CORALSTONE.getDefaultState(), 100)).range((new RangeDecoratorConfig(TrapezoidHeightProvider.create(YOffset.fixed(0), YOffset.fixed(0), 300)))).spreadHorizontally().repeat(250);
//
//    public static void registerConfiguredFeatures() {
//        var registry = BuiltinRegistries.CONFIGURED_FEATURE;
//        Registry.register(registry, Jellyfishing.id("coral_plant"), CONFIGURED_CORAL_PLANT);
//        Registry.register(registry, Jellyfishing.id("tube_plant"), CONFIGURED_TUBE_PLANT);
//        Registry.register(registry, Jellyfishing.id("seanut_bush"), CONFIGURED_SEANUT_BUSH);
//        Registry.register(registry, Jellyfishing.id("pineapple_plant"), CONFIGURED_PINEAPPLE_PLANT);
//        Registry.register(registry, Jellyfishing.id("pineapple_patch"), CONFIGURED_PINEAPPLE_PLANT_PATCH);
//        Registry.register(registry, Jellyfishing.id("coralstone_replacement"), CONFIGURED_CORALSTONE_REPLACEMENT);
//    }
}