package com.jellyfishing.common.entities;

import com.jellyfishing.core.registry.JellyfishingItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlueJellyfishEntity extends AbstractJellyfishEntity {
    public BlueJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, Level level) {
        super(type, level, new ItemStack(JellyfishingItems.BLUE_JELLYFISH.get(), 1), JellyfishingItems.BLUE_JELLYFISH_JELLY.get(), 1, true, 200, 6, 0.8, 0.75, 0.3);
    }
}