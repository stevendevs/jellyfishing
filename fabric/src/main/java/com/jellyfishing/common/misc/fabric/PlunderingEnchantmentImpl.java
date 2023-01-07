package com.jellyfishing.common.misc.fabric;

import com.jellyfishing.common.misc.AgilityEnchantment;
import com.jellyfishing.common.misc.PlunderingEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PlunderingEnchantmentImpl {
    public static PlunderingEnchantment newInstance() {
        return new PlunderingEnchantment(EnchantmentCategory.TRIDENT);
    }
}
