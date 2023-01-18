package com.jellyfishing.core.registry;

import com.jellyfishing.common.item.BubbleKitItem;
import com.jellyfishing.common.item.GloveItem;
import com.jellyfishing.common.item.JellyfishItem;
import com.jellyfishing.common.item.JellyfishNetItem;
import com.jellyfishing.common.item.KelpMaterial;
import com.jellyfishing.common.item.KelpShakeItem;
import com.jellyfishing.common.item.SpatulaItem;
import com.jellyfishing.common.item.SuitMaterial;
import com.jellyfishing.core.Jellyfishing;
import dev.architectury.core.item.ArchitecturyRecordItem;
import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;

import java.util.function.Supplier;

@SuppressWarnings({ "unused"})
public class JellyfishingItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> JELLYFISH_NET = register("jellyfish_net", () -> new JellyfishNetItem(new Item.Properties().arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES).durability(148)));

    public static final RegistrySupplier<Item> BUBBLE_WAND = register("bubble_wand", () -> new BubbleKitItem(new Item.Properties().arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES).durability(128)));

    public static final RegistrySupplier<Item> JELLYFISH_JELLY = register("jellyfish_jelly", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build())));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH_JELLY = register("blue_jellyfish_jelly", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).build())));

    public static final RegistrySupplier<Item> JELLYFISH = register("jellyfish", () -> new JellyfishItem(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS), JellyfishingEntities.JELLYFISH));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH = register("blue_jellyfish", () -> new JellyfishItem(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS), JellyfishingEntities.BLUE_JELLYFISH));

    public static final RegistrySupplier<Item> JELLYFISH_SPAWN_EGG = register("jellyfish_spawn_egg", () -> new ArchitecturySpawnEggItem(JellyfishingEntities.JELLYFISH,15615448, 15615377, new Item.Properties().arch$tab(CreativeModeTabs.SPAWN_EGGS)));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH_SPAWN_EGG = register("blue_jellyfish_spawn_egg", () -> new ArchitecturySpawnEggItem(JellyfishingEntities.BLUE_JELLYFISH,5733356, 5719532, new Item.Properties().arch$tab(CreativeModeTabs.SPAWN_EGGS)));

    public static final RegistrySupplier<Item> SEANUT = register("seanut", () -> new ItemNameBlockItem(JellyfishingBlocks.SEANUT_BUSH.get(), (new Item.Properties()).arch$tab(CreativeModeTabs.NATURAL_BLOCKS).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build())));
    public static final RegistrySupplier<Item> ROASTED_SEANUT = register("roasted_seanut", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build())));
    public static final RegistrySupplier<Item> SEANUT_BUTTER = register("seanut_butter", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.5F).build())));
    public static final RegistrySupplier<Item> SEANUT_BRITTLE = register("seanut_brittle", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(12).saturationMod(0.8F).build())));

    public static final RegistrySupplier<Item> PINEAPPLE_SEEDS = register("pineapple_seeds", () -> new ItemNameBlockItem(JellyfishingBlocks.PINEAPPLE_PLANT.get(), (new Item.Properties()).arch$tab(CreativeModeTabs.NATURAL_BLOCKS)));

    public static final RegistrySupplier<Item> PINEAPPLE = register("pineapple", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));


    public static final RegistrySupplier<Item> JELLYFISH_JELLY_SANDWICH = register("jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.45F).build())));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH_JELLY_SANDWICH = register("blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(12).saturationMod(0.55F).build())));

    public static final RegistrySupplier<Item> SEANUT_JELLYFISH_JELLY_SANDWICH = register("seanut_butter_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(11).saturationMod(0.55F).build())));
    public static final RegistrySupplier<Item> SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH = register("seanut_butter_blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(15).saturationMod(0.7F).build())));

    public static final RegistrySupplier<Item> KELP_SHAKE = register("kelp_shake", () -> new KelpShakeItem(new Item.Properties().stacksTo(16).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.2F).build())));

    public static final RegistrySupplier<Item> KELP_MUSTACHE = register("kelp_mustache", () -> new ArmorItem(new KelpMaterial(), EquipmentSlot.HEAD, new Item.Properties().arch$tab(CreativeModeTabs.COMBAT)));

    public static final RegistrySupplier<Item> TRIPLE_GOOBERBERRY_SUNRISE = register("triple_gooberberry_sunrise", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).fast().build())));

    public static final RegistrySupplier<Item> GREASE_BALL = register("grease_ball", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> KRABBY_PATTY = register("krabby_patty", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.FOOD_AND_DRINKS).food(new FoodProperties.Builder().nutrition(10).saturationMod(1.5F).meat().build()).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> SPATULA = register("spatula", () -> new SpatulaItem(Tiers.IRON, 4 - 3, -2, new Item.Properties().durability(250).arch$tab(CreativeModeTabs.COMBAT)));

    public static final RegistrySupplier<Item> GOLDEN_SPATULA = register("golden_spatula", () -> new SpatulaItem(Tiers.NETHERITE, 6 - 3, -2F, new Item.Properties().durability(2031).arch$tab(CreativeModeTabs.COMBAT).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> KARATE_GLOVE = register("karate_glove", () -> new GloveItem(new Item.Properties().durability(1000).arch$tab(CreativeModeTabs.COMBAT), 3 - 1, -1.5F));
    public static final RegistrySupplier<Item> MASTER_KARATE_GLOVE = register("master_karate_glove", () -> new GloveItem(new Item.Properties().durability(2031).arch$tab(CreativeModeTabs.COMBAT), 5 - 1, -1.5F));
    public static final RegistrySupplier<Item> POWER_KARATE_GLOVE = register("power_karate_glove", () -> new GloveItem(new Item.Properties().durability(2031).arch$tab(CreativeModeTabs.COMBAT), 18 - 1, -3.625F));


    public static final SuitMaterial SUIT_MATERIAL = new SuitMaterial();

    public static final RegistrySupplier<Item> AIR_SUIT_HELMET = register("air_suit_helmet", () -> new ArmorItem(SUIT_MATERIAL, EquipmentSlot.HEAD, new Item.Properties().arch$tab(CreativeModeTabs.COMBAT)));
    public static final RegistrySupplier<Item> AIR_SUIT_CHESTPLATE = register("air_suit_chestplate", () -> new ArmorItem(SUIT_MATERIAL, EquipmentSlot.CHEST, new Item.Properties().arch$tab(CreativeModeTabs.COMBAT)));
    public static final RegistrySupplier<Item> AIR_SUIT_LEGGINGS = register("air_suit_leggings", () -> new ArmorItem(SUIT_MATERIAL, EquipmentSlot.LEGS, new Item.Properties().arch$tab(CreativeModeTabs.COMBAT)));
    public static final RegistrySupplier<Item> AIR_SUIT_BOOTS = register("air_suit_boots", () -> new ArmorItem(SUIT_MATERIAL, EquipmentSlot.FEET, new Item.Properties().arch$tab(CreativeModeTabs.COMBAT)));

//TODO: InvisibilitySpray IDEA!!!!

    public static final RegistrySupplier<Item> DIVER_SUIT_HELMET = null;//register("diver_suit_helmet", () -> new ArmorItem(SUIT_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().arch$tab(CreativeModeTabs.COMBAT)));

    public static final RegistrySupplier<Item> MUSIC_DISC_JELLYFISH_FIELDS = register("music_disc_jellyfish_fields", () -> new ArchitecturyRecordItem(15, JellyfishingSounds.JELLYFISH_FIELDS, new Item.Properties().arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES).rarity(Rarity.RARE), 173));

    private static RegistrySupplier<Item> register(String id, Supplier<Item> itemSupplier) {
        return ITEMS.register(id, itemSupplier);
    }
}