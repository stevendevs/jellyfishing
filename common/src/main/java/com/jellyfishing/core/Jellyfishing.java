package com.jellyfishing.core;

import com.google.common.collect.Sets;
import com.jellyfishing.common.entities.AbstractJellyfishEntity;
import com.jellyfishing.common.misc.JellyfishingSellItemFactory;
import com.jellyfishing.common.worldgen.JellyfishingBiome;
import com.jellyfishing.core.registry.JellyfishingBiomes;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.jellyfishing.core.registry.JellyfishingConfiguredFeatures;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;

import java.util.Set;

public class Jellyfishing {
    public static final String MOD_ID = "jellyfishing";

    public static void init() {
//        JellyfishingBiomes.BIOMES.register();
        JellyfishingItems.ITEMS.register();
        JellyfishingBlocks.BLOCKS.register();
        JellyfishingFeatures.FEATURES.register();
        JellyfishingEntities.ENTITIES.register();
        JellyfishingSounds.SOUNDS.register();
        JellyfishingPaintings.PAINTINGS.register();
        JellyfishingVillagers.POIS.register();
        JellyfishingVillagers.VILLAGER_PROFESSIONS.register();
        JellyfishingEnchantments.ENCHANTMENTS.register();
        JellyfishingParticles.PARTICLES.register();

//        JellyfishingExtras.registerVillagerProducts();
        JellyfishingExtras.registerFlammables();
        JellyfishingExtras.registerCompostables();
        JellyfishingExtras.registerSpawning();

        EntityAttributeRegistry.register(JellyfishingEntities.JELLYFISH, AbstractJellyfishEntity::createAttributes);
        EntityAttributeRegistry.register(JellyfishingEntities.BLUE_JELLYFISH, AbstractJellyfishEntity::createAttributes);

        LootEvents.registerLootTables();
        LootEvents.villagerTrades();
        LootEvents.traderTrades();

        initialize();

//        EnvExecutor.runInEnv(Env.CLIENT, ()-> Jellyfishing::onClientInit);
    }

    public static void initialize() {
        LifecycleEvent.SETUP.register(() -> {
            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.getKey().get().equals(JellyfishingBiomes.JELLYFISH_FIELDS.registry())) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, JellyfishingBiome.PLACED_JELLY_FEATURES);
                }
            });
            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.hasTag(BiomeTags.IS_JUNGLE)) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, JellyfishingConfiguredFeatures.PLACED_PINEAPPLE_PLANT_PATCH);
                }
            });
        });
    }

    public static class LootEvents {

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
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("gameplay/fishing/fish")).setWeight(10).setQuality(1)).build());
                    context.addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(id("gameplay/fishing/treasure_net")).setWeight(3).setQuality(1)).build());
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
            JellyfishingItems.JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 1, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 3, 5, 10, 0.05F)));
            JellyfishingItems.BLUE_JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 1, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, item, 3, 5, 10, 0.05F)));

            JellyfishingItems.JELLYFISH_NET.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 2, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, item, 1, 5, 10, 0.05F)));

            JellyfishingItems.JELLYFISH.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 7, item, 1, 3, 10, 0.05F)));
            JellyfishingItems.BLUE_JELLYFISH.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 9, item, 1, 3, 10, 0.05F)));

            JellyfishingBlocks.CORAL_PLANT.listen((block)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, block.asItem(), 3, 5, 10, 0.05F)));
            JellyfishingBlocks.TUBE_PLANT.listen((block)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FISHERMAN, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, block.asItem(), 2, 5, 10, 0.05F)));

            // MASON //
            JellyfishingBlocks.POLISHED_CORALSTONE.listen((block)-> TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, block.asItem(), 4, 5, 10, 0.05F)));
            JellyfishingBlocks.CORALSTONE.listen((block)-> TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, block.asItem(), 8, 5, 10, 0.05F)));
            JellyfishingBlocks.CORALSTONE.listen((block)-> TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 3, new JellyfishingSellItemFactory.SellItemFactory(block.asItem(), 16, Items.EMERALD, 1, 5, 10, 0.05F)));
            JellyfishingBlocks.CORALSTONE.listen((block)-> TradeRegistry.registerVillagerTrade(VillagerProfession.MASON, 4, new JellyfishingSellItemFactory.SellItemFactory(block.asItem(), 8, Items.EMERALD, 1, 5, 10, 0.05F)));

            // CLERIC //
            JellyfishingItems.JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 2, new JellyfishingSellItemFactory.SellItemFactory(item, 4, Items.EMERALD, 1, 8, 10, 0.05F)));
            JellyfishingItems.BLUE_JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 2, new JellyfishingSellItemFactory.SellItemFactory(item, 3, Items.EMERALD, 1, 8, 10, 0.05F)));
            JellyfishingItems.SEANUT_BUTTER.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 3, new JellyfishingSellItemFactory.SellItemFactory(item, 3, Items.EMERALD, 1, 6, 10, 0.05F)));
            JellyfishingItems.KELP_SHAKE.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 1, 6, 10, 0.05F)));
            JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.CLERIC, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, item, 1, 5, 10, 0.05F)));

            // FARMER //
            JellyfishingItems.SEANUT.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.FARMER, 2, new JellyfishingSellItemFactory.SellItemFactory(item, 24, Items.EMERALD, 1, 8, 10, 0.05F)));

            // TOOLSMITH //
            JellyfishingItems.JELLYFISH_NET.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.TOOLSMITH, 2, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, item, 1, 5, 10, 0.05F)));

            // WEAPONSMITH //
            JellyfishingItems.SPATULA.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.WEAPONSMITH, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 16, item, 1, 3, 10, 0.05F)));

            // ARMORER //
            JellyfishingItems.KELP_MUSTACHE.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.ARMORER, 1, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 1, 5, 10, 0.05F)));

            // BUTCHER //
            JellyfishingItems.KRABBY_PATTY.listen((item)-> TradeRegistry.registerVillagerTrade(VillagerProfession.BUTCHER, 5, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, item, 1, 5, 10, 0.05F)));

            // FRYCOOK //
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.JELLYFISH_JELLY.get(), 20, Items.EMERALD, 1, 8, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.BLUE_JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 16, Items.EMERALD, 1, 8, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.SEANUT_BUTTER.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT_BUTTER.get(), 8, Items.EMERALD, 1, 8, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.SEANUT.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 1, new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT.get(), 20, Items.EMERALD, 1, 8, 10, 0.05F))));

            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.JELLYFISH.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 2, new JellyfishingSellItemFactory.SellItemFactory(item, 5, Items.EMERALD, 1, 8, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.BLUE_JELLYFISH.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 2, new JellyfishingSellItemFactory.SellItemFactory(item, 2, Items.EMERALD, 1, 8, 10, 0.05F))));

            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.ROASTED_SEANUT.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 36, 16, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.JELLYFISH_JELLY_SANDWICH.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, item, 2, 16, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 3, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, item, 1, 16, 10, 0.05F))));

            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 3, 16, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, item, 1, 16, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 1, 16, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.SEANUT_BRITTLE.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 8, 16, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.KELP_SHAKE.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 4, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, item, 1, 16, 10, 0.05F))));

            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.GREASE_BALL.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 5, new JellyfishingSellItemFactory.SellItemFactory(item, 1, Items.EMERALD, 32, 8, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.KRABBY_PATTY.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 5, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 1, 8, 10, 0.05F))));
            JellyfishingVillagers.FRYCOOK.listen((cook)-> JellyfishingItems.KARATE_GLOVE.listen((item)-> TradeRegistry.registerVillagerTrade(cook, 5, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 8, item, 1, 3, 10, 0.05F))));
        }

        public static void traderTrades() {
            JellyfishingItems.JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(false, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, item, 6, 8, 10, 0.05F)));
            JellyfishingItems.BLUE_JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(false, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 4, item, 4, 8, 10, 0.05F)));
            JellyfishingItems.KELP_MUSTACHE.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(false, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, item, 1, 5, 10, 0.05F)));

            JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 1, 3, 10, 0.05F)));
            JellyfishingItems.JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 1, 3, 10, 0.05F)));
            JellyfishingItems.JELLYFISH_JELLY.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, item, 1, 2, 10, 0.05F)));
            JellyfishingItems.SEANUT.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, item, 1, 4, 10, 0.05F)));
            JellyfishingItems.KRABBY_PATTY.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 4, item, 1, 3, 10, 0.05F)));
            JellyfishingItems.BUBBLE_WAND.listen((item)-> TradeRegistry.registerTradeForWanderingTrader(true, new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, item, 1, 2, 10, 0.05F)));
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}