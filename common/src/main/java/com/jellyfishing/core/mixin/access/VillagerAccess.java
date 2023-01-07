package com.jellyfishing.core.mixin.access;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unused"})
@Mixin(Villager.class)
public interface VillagerAccess {
    @Mutable
    @Accessor("WANTED_ITEMS")
    static void setGatherableItems(Set<Item> items) {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("FOOD_POINTS")
    static void setItemFoodValues(Map<Item, Integer> food) {
        throw new AssertionError();
    }
}