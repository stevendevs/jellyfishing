package com.jellyfishing.core.util;

import com.jellyfishing.core.registry.JellyfishingItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class ItemsUtil {
    public static boolean hasAllSuitPieces(Player player) {
        var headItem = player.getItemBySlot(EquipmentSlot.HEAD).getItem();
        var chestItem = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        var legItem = player.getItemBySlot(EquipmentSlot.LEGS).getItem();
        var feetItem = player.getItemBySlot(EquipmentSlot.FEET).getItem();
        return headItem == JellyfishingItems.AIR_SUIT_HELMET.get() &&
                chestItem == JellyfishingItems.AIR_SUIT_CHESTPLATE.get() &&
                legItem == JellyfishingItems.AIR_SUIT_LEGGINGS.get() &&
                feetItem == JellyfishingItems.AIR_SUIT_BOOTS.get();
    }
}