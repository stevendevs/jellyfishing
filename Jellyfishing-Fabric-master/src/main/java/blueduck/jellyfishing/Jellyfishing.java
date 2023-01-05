package blueduck.jellyfishing;

import blueduck.jellyfishing.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.misc.JellyfishingSellItemFactory;
import blueduck.jellyfishing.misc.config.JellyfishingConfig;
import blueduck.jellyfishing.mixin.access.VillagerAccess;
import blueduck.jellyfishing.registry.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Jellyfishing implements ModInitializer {
    //TODO: ask about other hidden features in the forge version, do some blockEntities/ Pattywagon, clean up this class
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "jellyfishing";
    public static final String MOD_NAME = "Jellyfishing";

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public void onInitialize() {
        ModLoadingContext.registerConfig(MOD_ID, ModConfig.Type.COMMON, JellyfishingConfig.COMMON_CONFIG);
        ModLoadingContext.registerConfig(MOD_ID, ModConfig.Type.CLIENT, JellyfishingConfig.CLIENT_CONFIG);

        Reflection.initialize(
//                JellyfishingBiomes.class,
                JellyfishingBlocks.class,
                JellyfishingConfiguredFeatures.class,
                JellyfishingEntities.class,
                JellyfishingFeatures.class,
                JellyfishingItems.class,
                JellyfishingPaintings.class,
                JellyfishingParticles.class,
                JellyfishingSounds.class,
                JellyfishingTags.class,
                JellyfishingVillagers.class
        );
        JellyfishingEntities.init();
//        JellyfishingBiomes.init();
        JellyfishingPaintings.init();
        JellyfishingVillagers.init();

        FabricDefaultAttributeRegistry.register(JellyfishingEntities.JELLYFISH, AbstractJellyfishEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(JellyfishingEntities.BLUE_JELLYFISH, AbstractJellyfishEntity.createAttributes());

        SpawnRestriction.register(JellyfishingEntities.JELLYFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfishEntity::canSpawn);
        SpawnRestriction.register(JellyfishingEntities.BLUE_JELLYFISH, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfishEntity::canSpawn);

        VillagerAccess.setItemFoodValues(ImmutableMap.<Item, Integer>builder()
                .putAll(VillagerEntity.ITEM_FOOD_VALUES)
                .put(JellyfishingItems.ROASTED_SEANUT, 2)
                .put(JellyfishingItems.SEANUT_BRITTLE, 5)
                .put(JellyfishingItems.JELLYFISH_JELLY_SANDWICH, 4)
                .put(JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH, 5)
                .put(JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH, 5)
                .put(JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH, 6)
                .put(JellyfishingItems.KRABBY_PATTY, 20)
                .put(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE, 8)
                .put(JellyfishingItems.PINEAPPLE, 1)
                .build());

        VillagerAccess.setGatherableItems(ImmutableSet.<Item>builder()
                .addAll(VillagerEntity.GATHERABLE_ITEMS)
                .add(JellyfishingItems.ROASTED_SEANUT)
                .add(JellyfishingItems.SEANUT_BRITTLE)
                .add(JellyfishingItems.JELLYFISH_JELLY_SANDWICH)
                .add(JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH)
                .add(JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH)
                .add(JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH)
                .add(JellyfishingItems.KRABBY_PATTY)
                .add(JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE)
                .add(JellyfishingItems.PINEAPPLE)
                .build());

        JellyfishingBlocks.registerFlammables();
        JellyfishingItems.registerCompostables();
//        JellyfishingConfiguredFeatures.registerConfiguredFeatures();
        LootEvents.onBiomeLoad();
        LootEvents.registerLootTables();
        LootEvents.villagerTrades();
        LootEvents.traderTrades();

        log(Level.INFO, "Jellylizing");
    }

    public static RegistryKey<PlacedFeature> rk(PlacedFeature placedFeature) {
        return BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature).get();
    }

    public static class LootEvents {
        public static void onBiomeLoad() {
//            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, rk(JellyfishingConfiguredFeatures.CONFIGURED_PINEAPPLE_PLANT_PATCH));
        }

        public static void registerLootTables() {
            Set<Identifier> shipSupplyChestsId = Sets.newHashSet(
                    LootTables.SHIPWRECK_SUPPLY_CHEST,
                    LootTables.UNDERWATER_RUIN_BIG_CHEST,
                    LootTables.UNDERWATER_RUIN_SMALL_CHEST
            );

            LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
                if (LootTables.SHIPWRECK_TREASURE_CHEST == id) {
                    supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("chests/shipwreck_treasure"))).build());
                }

                if (shipSupplyChestsId.contains(id)) {
                    supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("chests/shipwreck_supply"))).build());
                }

                if (LootTables.SPAWN_BONUS_CHEST == id) {
                    supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("chests/kelp_mustache"))).build());
                }

                if (LootTables.END_CITY_TREASURE_CHEST == id) {
                    supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("chests/end_city_treasure")).weight(10).quality(1)).build());
                }

                if (LootTables.FISHING_GAMEPLAY == id) {
                    if (JellyfishingConfig.JELLYFISH_FISHABLE.get()) {
                        supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("gameplay/fishing/fish")).weight(10).quality(1)).build());
                    }
                    if (JellyfishingConfig.NETS_FISHABLE.get()) {
                        supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("gameplay/fishing/treasure_net")).weight(3).quality(1)).build());
                    }
                    supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("gameplay/fishing/junk_plants")).weight(1).quality(-2)).build());
                }

                if (LootTables.HERO_OF_THE_VILLAGE_FISHERMAN_GIFT_GAMEPLAY == id) {
                    supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("gameplay/fishing/fish")).weight(1).quality(1)).build());
                }

                if (LootTables.HERO_OF_THE_VILLAGE_TOOLSMITH_GIFT_GAMEPLAY == id) {
                    supplier.withPool(LootPool.builder().with(LootTableEntry.builder(id("gameplay/toolsmith_net")).weight(1).quality(1)).build());
                }
            });
        }

        public static void villagerTrades() {
            // FISHERMAN //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.JELLYFISH_JELLY, 3, 5, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.BLUE_JELLYFISH_JELLY, 3, 5, 10, 0.05F)));

            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, JellyfishingItems.JELLYFISH_NET, 1, 5, 10, 0.05F)));

            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 7, JellyfishingItems.JELLYFISH, 1, 3, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 9, JellyfishingItems.BLUE_JELLYFISH, 1, 3, 10, 0.05F)));

            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingBlocks.CORAL_PLANT.asItem(), 3, 5, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingBlocks.TUBE_PLANT.asItem(), 2, 5, 10, 0.05F)));

            // MASON //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingBlocks.POLISHED_CORALSTONE.asItem(), 4, 5, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingBlocks.CORALSTONE.asItem(), 8, 5, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingBlocks.CORALSTONE.asItem(), 16, Items.EMERALD, 1, 5, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingBlocks.CORALSTONE.asItem(), 8, Items.EMERALD, 1, 5, 10, 0.05F)));

            // CLERIC //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.JELLYFISH_JELLY, 4, Items.EMERALD, 1, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.BLUE_JELLYFISH_JELLY, 3, Items.EMERALD, 1, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT_BUTTER, 3, Items.EMERALD, 1, 6, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.KELP_SHAKE, 1, 6, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE, 1, 5, 10, 0.05F)));

            // FARMER //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT, 24, Items.EMERALD, 1, 8, 10, 0.05F)));

            // TOOLSMITH //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, JellyfishingItems.JELLYFISH_NET, 1, 5, 10, 0.05F)));

            // WEAPONSMITH //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 16, JellyfishingItems.SPATULA, 1, 3, 10, 0.05F)));

            // ARMORER //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.KELP_MUSTACHE, 1, 5, 10, 0.05F)));

            // BUTCHER //
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 5, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 5, JellyfishingItems.KRABBY_PATTY, 1, 5, 10, 0.05F)));

            // FRYCOOK //
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.JELLYFISH_JELLY, 20, Items.EMERALD, 1, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.BLUE_JELLYFISH_JELLY, 16, Items.EMERALD, 1, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT_BUTTER, 8, Items.EMERALD, 1, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.SEANUT, 20, Items.EMERALD, 1, 8, 10, 0.05F)));

            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.JELLYFISH, 5, Items.EMERALD, 1, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.BLUE_JELLYFISH, 2, Items.EMERALD, 1, 8, 10, 0.05F)));

            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.ROASTED_SEANUT, 36, 16, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.JELLYFISH_JELLY_SANDWICH, 2, 16, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 3, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.BLUE_JELLYFISH_JELLY_SANDWICH, 1, 16, 10, 0.05F)));

            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE, 3, 16, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.SEANUT_JELLYFISH_JELLY_SANDWICH, 1, 16, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH, 1, 16, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.SEANUT_BRITTLE, 8, 16, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 4, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.KELP_SHAKE, 1, 16, 10, 0.05F)));

            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 5, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(JellyfishingItems.GREASE_BALL, 1, Items.EMERALD, 32, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 5, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.KRABBY_PATTY, 1, 8, 10, 0.05F)));
            TradeOfferHelper.registerVillagerOffers(JellyfishingVillagers.FRYCOOK, 5, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 8, JellyfishingItems.KARATE_GLOVE, 1, 3, 10, 0.05F)));
        }

        public static void traderTrades() {
            TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.JELLYFISH_JELLY, 6, 8, 10, 0.05F)));
            TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 4, JellyfishingItems.BLUE_JELLYFISH_JELLY, 4, 8, 10, 0.05F)));
            TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 1, JellyfishingItems.KELP_MUSTACHE, 1, 5, 10, 0.05F)));

            TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.TRIPLE_GOOBERBERRY_SUNRISE, 1, 3, 10, 0.05F)));
            TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.JELLYFISH, 1, 3, 10, 0.05F)));
            TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.BLUE_JELLYFISH, 1, 2, 10, 0.05F)));
            TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 2, JellyfishingItems.SEANUT, 1, 4, 10, 0.05F)));
            TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 4, JellyfishingItems.KRABBY_PATTY, 1, 3, 10, 0.05F)));
            TradeOfferHelper.registerWanderingTraderOffers(2, factories -> factories.add(new JellyfishingSellItemFactory.SellItemFactory(Items.EMERALD, 3, JellyfishingItems.BUBBLE_WAND, 1, 2, 10, 0.05F)));
        }
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    public static Identifier id(String id) {
        return new Identifier(MOD_ID, id);
    }
}