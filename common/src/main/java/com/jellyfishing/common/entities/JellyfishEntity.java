package com.jellyfishing.common.entities;

import com.jellyfishing.core.registry.JellyfishingItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class JellyfishEntity extends AbstractJellyfishEntity {
    public JellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, Level level) {
        super(type, level, new ItemStack(JellyfishingItems.JELLYFISH.get(), 1), JellyfishingItems.JELLYFISH_JELLY.get(), 1, true, 500, 3, 0.1, 0.2, 0.1);
    }
}