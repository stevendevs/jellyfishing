package com.jellyfishing.common.misc;

import com.jellyfishing.common.item.JellyfishNetItem;
import com.jellyfishing.core.registry.JellyfishingEnchantments;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PlunderingEnchantment extends Enchantment {
    public PlunderingEnchantment(EnchantmentCategory category) {
        super(Enchantment.Rarity.UNCOMMON, category, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @ExpectPlatform
    public static PlunderingEnchantment newInstance() {
        throw new AssertionError();
    }

    @Override
    public int getMinCost(int level) {
        return 10 + 10 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return 50;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel() {
        return 3;
    }

    public static boolean canEnchant(Item item) {
        return item instanceof JellyfishNetItem;
    }
}