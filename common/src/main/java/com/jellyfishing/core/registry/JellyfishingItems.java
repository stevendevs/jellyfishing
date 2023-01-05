package com.jellyfishing.core.registry;

import com.jellyfishing.common.item.BubbleKitItem;
import com.jellyfishing.common.item.GloveItem;
import com.jellyfishing.common.item.JellyfishItem;
import com.jellyfishing.common.item.JellyfishNetItem;
import com.jellyfishing.common.item.KelpMaterial;
import com.jellyfishing.common.item.KelpMustacheItem;
import com.jellyfishing.common.item.KelpShakeItem;
import com.jellyfishing.common.item.SandySuitItem;
import com.jellyfishing.common.item.SpatulaItem;
import com.jellyfishing.common.item.SuitMaterial;
import com.jellyfishing.core.Jellyfishing;
import dev.architectury.core.item.ArchitecturyRecordItem;
import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;

public class JellyfishingItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Jellyfishing.MOD_ID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> JELLYFISH_NET = ITEMS.register("jellyfish_net", () -> new JellyfishNetItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).durability(148)));

    public static final RegistrySupplier<Item> BUBBLE_WAND = ITEMS.register("bubble_wand", () -> new BubbleKitItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).durability(128)));

    public static final RegistrySupplier<Item> JELLYFISH_JELLY = ITEMS.register("jellyfish_jelly", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build())));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH_JELLY = ITEMS.register("blue_jellyfish_jelly", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).build())));

    public static final RegistrySupplier<Item> JELLYFISH = ITEMS.register("jellyfish", () -> new JellyfishItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS),  () -> JellyfishingEntities.JELLYFISH.get()));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH = ITEMS.register("blue_jellyfish", () -> new JellyfishItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS),  () -> JellyfishingEntities.BLUE_JELLYFISH.get()));

    public static final RegistrySupplier<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg", () -> new ArchitecturySpawnEggItem(() -> JellyfishingEntities.JELLYFISH.get(),15615448, 15615377, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH_SPAWN_EGG = ITEMS.register("blue_jellyfish_spawn_egg", () -> new ArchitecturySpawnEggItem(() -> JellyfishingEntities.BLUE_JELLYFISH.get(),5733356, 5719532, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistrySupplier<Item> SEANUT = ITEMS.register("seanut", () -> new ItemNameBlockItem(JellyfishingBlocks.SEANUT_BUSH.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build())));
    public static final RegistrySupplier<Item> ROASTED_SEANUT = ITEMS.register("roasted_seanut", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build())));
    public static final RegistrySupplier<Item> SEANUT_BUTTER = ITEMS.register("seanut_butter", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.5F).build())));
    public static final RegistrySupplier<Item> SEANUT_BRITTLE = ITEMS.register("seanut_brittle", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(12).saturationMod(0.8F).build())));

    public static final RegistrySupplier<Item> PINEAPPLE_SEEDS = ITEMS.register("pineapple_seeds", () -> new ItemNameBlockItem(JellyfishingBlocks.PINEAPPLE_PLANT.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistrySupplier<Item> PINEAPPLE = ITEMS.register("pineapple", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));


    public static final RegistrySupplier<Item> JELLYFISH_JELLY_SANDWICH = ITEMS.register("jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.45F).build())));
    public static final RegistrySupplier<Item> BLUE_JELLYFISH_JELLY_SANDWICH = ITEMS.register("blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(12).saturationMod(0.55F).build())));

    public static final RegistrySupplier<Item> SEANUT_JELLYFISH_JELLY_SANDWICH = ITEMS.register("seanut_butter_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(11).saturationMod(0.55F).build())));
    public static final RegistrySupplier<Item> SEANUT_BLUE_JELLYFISH_JELLY_SANDWICH = ITEMS.register("seanut_butter_blue_jellyfish_jelly_sandwich", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(15).saturationMod(0.7F).build())));

    public static final RegistrySupplier<Item> KELP_SHAKE = ITEMS.register("kelp_shake", () -> new KelpShakeItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.2F).build())));

    public static final RegistrySupplier<Item> KELP_MUSTACHE = ITEMS.register("kelp_mustache", () -> new KelpMustacheItem(new KelpMaterial(), new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistrySupplier<Item> TRIPLE_GOOBERBERRY_SUNRISE = ITEMS.register("triple_gooberberry_sunrise", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).fastToEat().build())));

    public static final RegistrySupplier<Item> GREASE_BALL = ITEMS.register("grease_ball", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> KRABBY_PATTY = ITEMS.register("krabby_patty", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(10).saturationMod(1.5F).meat().build()).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> SPATULA = ITEMS.register("spatula", () -> new SpatulaItem(new Item.Properties().maxDamage(250).tab(CreativeModeTab.TAB_COMBAT), JellyfishingMod.CONFIG.SPATULA_DAMAGE.get() - 3, -2, ItemTier.IRON));

    public static final RegistrySupplier<Item> GOLDEN_SPATULA = ITEMS.register("golden_spatula", () -> new SpatulaItem(new Item.Properties().maxDamage(2031).tab(CreativeModeTab.TAB_COMBAT).rarity(Rarity.RARE), JellyfishingMod.CONFIG.GOLDEN_SPATULA_DAMAGE.get() - 3, -2F, ItemTier.NETHERITE));

    public static final RegistrySupplier<Item> KARATE_GLOVE = ITEMS.register("karate_glove", () -> new GloveItem(new Item.Properties().maxDamage(1000).tab(CreativeModeTab.TAB_COMBAT), JellyfishingMod.CONFIG.KARATE_DAMAGE.get() - 1, -1.5F));
    public static final RegistrySupplier<Item> MASTER_KARATE_GLOVE = ITEMS.register("master_karate_glove", () -> new GloveItem(new Item.Properties().maxDamage(2031).tab(CreativeModeTab.TAB_COMBAT), JellyfishingMod.CONFIG.MASTER_KARATE_DAMAGE.get() - 1, -1.5F));
    public static final RegistrySupplier<Item> POWER_KARATE_GLOVE = ITEMS.register("power_karate_glove", () -> new GloveItem(new Item.Properties().maxDamage(2031).tab(CreativeModeTab.TAB_COMBAT), JellyfishingMod.CONFIG.POWER_KARATE_DAMAGE.get() - 1, -3.625F));


    public static final SuitMaterial SUIT_MATERIAL = new SuitMaterial();

    public static final RegistrySupplier<Item> AIR_SUIT_HELMET = ITEMS.register("air_suit_helmet", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistrySupplier<Item> AIR_SUIT_CHESTPLATE = ITEMS.register("air_suit_chestplate", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistrySupplier<Item> AIR_SUIT_LEGGINGS = ITEMS.register("air_suit_leggings", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistrySupplier<Item> AIR_SUIT_BOOTS = ITEMS.register("air_suit_boots", () -> new SandySuitItem(SUIT_MATERIAL, EquipmentSlot.FEET, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));



    public static final RegistrySupplier<Item> DIVER_SUIT_HELMET = null;//ITEMS.register("diver_suit_helmet", () -> new DiverSuitItem(SUIT_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistrySupplier<Item> MUSIC_DISC_JELLYFISH_FIELDS = ITEMS.register("music_disc_jellyfish_fields", () -> new ArchitecturyRecordItem(15, JellyfishingSounds.JELLYFISH_FIELDS, new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE), 173));
}