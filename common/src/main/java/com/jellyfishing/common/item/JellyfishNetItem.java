package com.jellyfishing.common.item;

import net.minecraft.world.item.Item;

public class JellyfishNetItem extends Item {
    public JellyfishNetItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getEnchantmentValue() {
        return 30;
    }
}