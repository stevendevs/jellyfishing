package com.jellyfishing.common.misc.forge;

import com.jellyfishing.common.item.JellyfishNetItem;
import com.jellyfishing.common.misc.AgilityEnchantment;
import com.jellyfishing.common.misc.PlunderingEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PlunderingEnchantmentImpl {
    public static PlunderingEnchantment newInstance() {
        return new PlunderingEnchantment(EnchantmentCategory.create("NET", item -> item instanceof JellyfishNetItem));
    }
}