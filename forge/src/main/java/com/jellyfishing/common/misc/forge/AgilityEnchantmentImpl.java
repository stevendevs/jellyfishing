package com.jellyfishing.common.misc.forge;

import com.jellyfishing.common.item.JellyfishNetItem;
import com.jellyfishing.common.misc.AgilityEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AgilityEnchantmentImpl {
    public static AgilityEnchantment newInstance() {
        return new AgilityEnchantment(EnchantmentCategory.create("NET", item -> item instanceof JellyfishNetItem));
    }
}