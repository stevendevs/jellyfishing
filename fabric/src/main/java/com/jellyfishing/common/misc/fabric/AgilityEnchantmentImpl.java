package com.jellyfishing.common.misc.fabric;

import com.jellyfishing.common.misc.AgilityEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AgilityEnchantmentImpl {
    public static AgilityEnchantment newInstance() {
        return new AgilityEnchantment(EnchantmentCategory.TRIDENT);
    }
}