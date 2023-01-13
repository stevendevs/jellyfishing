package com.jellyfishing.core;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.jellyfishing.common.entities.AbstractJellyfishEntity;
import com.jellyfishing.common.misc.JellyfishingSellItemFactory;
import com.jellyfishing.common.worldgen.JellyfishingBiome;
import com.jellyfishing.core.mixin.access.VillagerAccess;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.jellyfishing.core.registry.JellyfishingEnchantments;
import com.jellyfishing.core.registry.JellyfishingEntities;
import com.jellyfishing.core.registry.JellyfishingExtras;
import com.jellyfishing.core.registry.JellyfishingFeatures;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.registry.JellyfishingPaintings;
import com.jellyfishing.core.registry.JellyfishingParticles;
import com.jellyfishing.core.registry.JellyfishingSounds;
import com.jellyfishing.core.registry.JellyfishingVillagers;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.LootEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.level.entity.trade.TradeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;

import java.util.List;
import java.util.Set;

public class Jellyfishing {
    public static final String MOD_ID = "jellyfishing";



    public static void init() {
//        JellyfishingBiomes.BIOMES.register();
        JellyfishingBlocks.BLOCKS.register();
        JellyfishingFeatures.FEATURES.register();
        JellyfishingEntities.ENTITIES.register();
        JellyfishingItems.ITEMS.register();
        JellyfishingSounds.SOUNDS.register();
        JellyfishingPaintings.PAINTINGS.register();
        JellyfishingVillagers.POIS.register();
        JellyfishingVillagers.VILLAGER_PROFESSIONS.register();
        JellyfishingEnchantments.ENCHANTMENTS.register();
        JellyfishingParticles.PARTICLES.register();

        JellyfishingExtras.setEnchantmentCategory();
        JellyfishingExtras.registerFlammables();
        JellyfishingExtras.registerCompostables();

        EntityAttributeRegistry.register(JellyfishingEntities.JELLYFISH, AbstractJellyfishEntity::createAttributes);
        EntityAttributeRegistry.register(JellyfishingEntities.BLUE_JELLYFISH, AbstractJellyfishEntity::createAttributes);

        VillagerAccess.setItemFoodValues(ImmutableMap.<Item, Integer>builder()
                .putAll(Villager.FOOD_POINTS)
                .put(JellyfishingItems.ROASTED_SEANUT.get(), 2)
                .put(JellyfishingItems.SEANUT_BRITTLE.get(), 5)
                .put(JellyfishingItems.JELLYFISH_JELLY_SANDWICH.get(), 4)
                .put(JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH.get(), 5)
                .put(JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH.get(), 5)
                .put(JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH.get(), 6)
                .put(JellyfishingItems.KRABBY_PATTY.get(), 20)
                .put(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 8)
                .put(JellyfishingItems.PINEAPPLE.get(), 1)
                .build());

        VillagerAccess.setGatherableItems(ImmutableSet.<Item>builder()
                .addAll(Villager.WANTED_ITEMS)
                .add(JellyfishingItems.ROASTED_SEANUT.get())
                .add(JellyfishingItems.SEANUT_BRITTLE.get())
                .add(JellyfishingItems.JELLYFISH_JELLY_SANDWICH.get())
                .add(JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH.get())
                .add(JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH.get())
                .add(JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH.get())
                .add(JellyfishingItems.KRABBY_PATTY.get())
                .add(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get())
                .add(JellyfishingItems.PINEAPPLE.get())
                .build());

        SpawnPlacements.register(JellyfishingEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfishEntity::canJellySpawn);
        SpawnPlacements.register(JellyfishingEntities.BLUE_JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfishEntity::canJellySpawn);

        LootEvents.onBiomeLoad();
        LootEvents.registerLootTables();
        LootEvents.villagerTrades();
        LootEvents.traderTrades();

        initialize();

//        BiomeModifications.addProperties((ctx, mutable) -> {
//            if (ctx.hasTag(BiomeTags.IS_FOREST)) {
//                mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, placedFeature);
//            }
//        });

//        EnvExecutor.runInEnv(Env.CLIENT, ()-> Jellyfishing::onClientInit);
    }

    public static void initialize() {
        LifecycleEvent.SETUP.register(() -> {
            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.getKey().get().equals(JellyfishingBiomes.JELLYFISH_FIELDS.registry())) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, JellyfishingBiome.PLACED_COMMON_CORAL_PLANT);
                }
            });
        });
    }

//    public static RegistryKey<PlacedFeature> rk(PlacedFeature placedFeature) {
//        return BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature).get();
//    }

    public static class LootEvents {
        public static void onBiomeLoad() {
//            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, rk(JellyfishingConfiguredFeatures.CONFIGURED_PINEAPPLE_PLANT_PATCH));
        }

        public static void registerLootTables() {
            Set<ResourceLocation> shipSupplyChestsId = Sets.newHashSet(
                    BuiltInLootTables.SHIPWRECK_SUPPLY,
                    BuiltInLootTables.UNDERWATER_RUIN_BIG,
                    BuiltInLootTables.UNDERWATER_RUIN_SMALL
            );

            LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> {
                if (BuiltInLootTables.SHIPWRECK_TREASURE == id) {
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("chests/shipwreck_treasure"))).build());
                }

                if (shipSupplyChestsId.contains(id)) {
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("chests/shipwreck_supply"))).build());
                }

                if (BuiltInLootTables.SPAWN_BONUS_CHEST == id) {
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("chests/kelp_mustache"))).build());
                }

                if (BuiltInLootTables.END_CITY_TREASURE == id) {
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("chests/end_city_treasure")).setWeight(10).setQuality(1)).build());
                }

                if (BuiltInLootTables.FISHING == id) {
                    if (true) {
                        context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("gameplay/fishing/fish")).setWeight(10).setQuality(1)).build());
                    }
                    if (true) {
                        context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("gameplay/fishing/treasure_net")).setWeight(3).setQuality(1)).build());
                    }
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("gameplay/fishing/junk_plants")).setWeight(1).setQuality(-2)).build());
                }

                if (BuiltInLootTables.FISHERMAN_GIFT == id) {
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("gameplay/fishing/fish")).setWeight(1).setQuality(1)).build());
                }

                if (BuiltInLootTables.TOOLSMITH_GIFT == id) {
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("gameplay/toolsmith_net")).setWeight(1).setQuality(1)).build());
                }
            });
        }

        public static void villagerTrades() {
            // FISHERMAN //
            TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 1, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.JELLYFISH_JELLY.get(), 3, 5, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 1, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 3, 5, 10, 0.05F));

            TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 2, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, JellyfishingItems.JELLYFISH_NET.get(), 1, 5, 10, 0.05F));

            TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 7, JellyfishingItems.JELLYFISH.get(), 1, 3, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 9, JellyfishingItems.BLUE_JELLYFISH.get(), 1, 3, 10, 0.05F));

            TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingBlocks.CORAL_PLANT.get().asItem(), 3, 5, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingBlocks.TUBE_PLANT.get().asItem(), 2, 5, 10, 0.05F));

            // MASON //
            TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingBlocks.POLISHED_CORALSTONE.get().asItem(), 4, 5, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingBlocks.CORALSTONE.get().asItem(), 8, 5, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 3, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingBlocks.CORALSTONE.get().asItem(), 16, Items.EMERALD, 1, 5, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 4, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingBlocks.CORALSTONE.get().asItem(), 8, Items.EMERALD, 1, 5, 10, 0.05F));

            // CLERIC //
            TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 2, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.JELLYFISH_JELLY.get(), 4, Items.EMERALD, 1, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 2, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 3, Items.EMERALD, 1, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 3, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT_BUTTER.get(), 3, Items.EMERALD, 1, 6, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.KELP_SHAKE.get(), 1, 6, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 1, 5, 10, 0.05F));

            // FARMER //
            TradeRegistry.registerVillagerTrade(VillagerProfession.FARMER, 2, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT.get(), 24, Items.EMERALD, 1, 8, 10, 0.05F));

            // TOOLSMITH //
            TradeRegistry.registerVillagerTrade(VillagerProfession.TOOLSMITH, 2, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, JellyfishingItems.JELLYFISH_NET.get(), 1, 5, 10, 0.05F));

            // WEAPONSMITH //
            TradeRegistry.registerVillagerTrade(VillagerProfession.WEAPONSMITH, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 16, JellyfishingItems.SPATULA.get(), 1, 3, 10, 0.05F));

            // ARMORER //
            TradeRegistry.registerVillagerTrade(VillagerProfession.ARMORER, 1, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.KELP_MUSTACHE.get(), 1, 5, 10, 0.05F));

            // BUTCHER //
            TradeRegistry.registerVillagerTrade(VillagerProfession.BUTCHER, 5, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, JellyfishingItems.KRABBY_PATTY.get(), 1, 5, 10, 0.05F));

            // FRYCOOK //
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.JELLYFISH_JELLY.get(), 20, Items.EMERALD, 1, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 16, Items.EMERALD, 1, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT_BUTTER.get(), 8, Items.EMERALD, 1, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT.get(), 20, Items.EMERALD, 1, 8, 10, 0.05F));

            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 2, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.JELLYFISH.get(), 5, Items.EMERALD, 1, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 2, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.BLUE_JELLYFISH.get(), 2, Items.EMERALD, 1, 8, 10, 0.05F));

            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.ROASTED_SEANUT.get(), 36, 16, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.JELLYFISH_JELLY_SANDWICH.get(), 2, 16, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH.get(), 1, 16, 10, 0.05F));

            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 3, 16, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH.get(), 1, 16, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH.get(), 1, 16, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.SEANUT_BRITTLE.get(), 8, 16, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.KELP_SHAKE.get(), 1, 16, 10, 0.05F));

            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 5, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.GREASE_BALL.get(), 1, Items.EMERALD, 32, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 5, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.KRABBY_PATTY.get(), 1, 8, 10, 0.05F));
            TradeRegistry.registerVillagerTrade(JellyfishingVillagers.FRYCOOK.get(), 5, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 8, JellyfishingItems.KARATE_GLOVE.get(), 1, 3, 10, 0.05F));
        }

        public static void traderTrades() {
            TradeRegistry.registerTradeForWanderingTrader(false, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.JELLYFISH_JELLY.get(), 6, 8, 10, 0.05F));
            TradeRegistry.registerTradeForWanderingTrader(false, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 4, JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 4, 8, 10, 0.05F));
            TradeRegistry.registerTradeForWanderingTrader(false, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.KELP_MUSTACHE.get(), 1, 5, 10, 0.05F));

            TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.get(), 1, 3, 10, 0.05F));
            TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.JELLYFISH.get(), 1, 3, 10, 0.05F));
            TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.BLUE_JELLYFISH.get(), 1, 2, 10, 0.05F));
            TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.SEANUT.get(), 1, 4, 10, 0.05F));
            TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 4, JellyfishingItems.KRABBY_PATTY.get(), 1, 3, 10, 0.05F));
            TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.BUBBLE_WAND.get(), 1, 2, 10, 0.05F));
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}