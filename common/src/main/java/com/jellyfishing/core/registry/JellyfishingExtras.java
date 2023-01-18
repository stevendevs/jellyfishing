package com.jellyfishing.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.jellyfishing.common.entities.AbstractJellyfishEntity;
import com.jellyfishing.core.mixin.access.VillagerAccess;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;

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

    public static void registerSpawning() {
        var entities = ImmutableList.of(JellyfishingEntities.JELLYFISH, JellyfishingEntities.BLUE_JELLYFISH);
        entities.forEach(entity -> entity.listen((real)-> SpawnPlacements.register(real, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractJellyfishEntity::canJellySpawn)));
    }

    public static void registerVillagerProducts() {
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
    }

    public static void doSpecialSpawn(Mob entity, LevelAccessor level) {
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
    }
    
    @ExpectPlatform
    public static <T extends Block> void setFlammable(RegistrySupplier<T> block, int encouragement, int flammability) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends ItemLike> void setCompostable(RegistrySupplier<T> like, float chance) {
        throw new AssertionError();
    }
}