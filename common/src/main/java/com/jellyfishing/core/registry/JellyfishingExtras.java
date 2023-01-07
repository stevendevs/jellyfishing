package com.jellyfishing.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class JellyfishingExtras {
    public static void registerFlammables() {
        setFlammable(JellyfishingBlocks.BAMBOO_WALL, 10, 20);
        setFlammable(JellyfishingBlocks.PINK_BAMBOO_WALL, 10, 20);
        setFlammable(JellyfishingBlocks.BLUE_BAMBOO_WALL, 10, 20);
        setFlammable(JellyfishingBlocks.YELLOW_BAMBOO_WALL, 10, 20);

        setFlammable(JellyfishingBlocks.WHITE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.ORANGE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.MAGENTA_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.LIGHT_BLUE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.YELLOW_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.LIME_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.PINK_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.GRAY_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.LIGHT_GRAY_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.CYAN_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.PURPLE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.BLUE_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.BROWN_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.GREEN_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.RED_CARPETED_TILES, 8, 20);
        setFlammable(JellyfishingBlocks.BLACK_CARPETED_TILES, 8, 20);
    }

    public static void registerCompostables() {
        setCompostable(JellyfishingItems.SEANUT, 0.3F);
        setCompostable(JellyfishingItems.PINEAPPLE_SEEDS, 0.3F);
        setCompostable(JellyfishingItems.PINEAPPLE, 0.7F);
    }

    public static void setEnchantmentCategory() {
//        EnchantmentCategory[] TYPES = new EnchantmentCategory[CreativeModeTabs.TOOLS_AND_UTILITIES.getEnchantmentCategories().length + 2];
//        for (int i = 0; i < CreativeModeTabs.TOOLS_AND_UTILITIES.getEnchantmentCategories().length; i++) {
//            TYPES[i] = CreativeModeTabs.TOOLS_AND_UTILITIES.getEnchantmentCategories()[i];
//        }
//        TYPES[TYPES.length - 1] = JellyfishingEnchantments.NET_ENCHANTMENT_CATEGORY;
//        CreativeModeTabs.TOOLS_AND_UTILITIES.setEnchantmentCategories(TYPES);
    }

    public static boolean doSpecialSpawn(Mob entity, LevelAccessor level) {
        if (!level.isClientSide()) {
            if (entity instanceof Drowned) {
                if (entity.getLevel().getRandom().nextDouble() < 0.025) {
                    if (entity.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                        entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(JellyfishingItems.KELP_MUSTACHE.get()));
                        entity.setDropChance(EquipmentSlot.HEAD, 0.085F);

                    }
                }
                if (entity.getLevel().getRandom().nextDouble() < 0.01) {
                    if (entity.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                        entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.JELLYFISH_NET.get()));
                        entity.setDropChance(EquipmentSlot.MAINHAND, 0.085F);

                    }
                }
                if (entity.getLevel().getRandom().nextDouble() < 0.01) {
                    if (entity.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                        entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.SPATULA.get()));
                        entity.setDropChance(EquipmentSlot.MAINHAND, 0.085F);

                    }
                }
                if (entity.getLevel().getRandom().nextDouble() < 0.02) {
                    if (entity.getLevel().getRandom().nextDouble() < 0.9) {
                        if (entity.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                            entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.KARATE_GLOVE.get()));
                            entity.setDropChance(EquipmentSlot.MAINHAND, 0.085F);
                        }
                        if (entity.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
                            entity.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.KARATE_GLOVE.get()));
                            entity.setDropChance(EquipmentSlot.OFFHAND, 0.085F);

                        }
                    }
                    else if (entity.getLevel().getRandom().nextDouble() < 0.5) {
                        if (entity.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                            entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                            entity.setDropChance(EquipmentSlot.MAINHAND, 0.085F);
                        }
                        if (entity.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
                            entity.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                            entity.setDropChance(EquipmentSlot.OFFHAND, 0.085F);

                        }
                    }
                    else {
                        if (entity.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                            entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.POWER_KARATE_GLOVE.get()));
                            entity.setDropChance(EquipmentSlot.MAINHAND, 0.085F);
                        }
                        if (entity.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
                            entity.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.POWER_KARATE_GLOVE.get()));
                            entity.setDropChance(EquipmentSlot.OFFHAND, 0.085F);
                        }
                    }
                }
                if (entity.getLevel().getRandom().nextDouble() < 0.01) {
                    entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(JellyfishingItems.AIR_SUIT_HELMET.get()));
                    entity.setDropChance(EquipmentSlot.HEAD, 0.01F);

                    if (entity.getLevel().getRandom().nextDouble() < 0.5) {
                        entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                        entity.setDropChance(EquipmentSlot.MAINHAND, 0.01F);
                        entity.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE.get()));
                        entity.setDropChance(EquipmentSlot.OFFHAND, 0.01F);
                    }
                }
            }
        }
        return true;
    }
    
    @ExpectPlatform
    public static <T extends Block> void setFlammable(Supplier<T> block, int encouragement, int flammability) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends ItemLike> void setCompostable(Supplier<T> like, float chance) {
        throw new AssertionError();
    }
}