package com.jellyfishing.common.item;

import com.jellyfishing.core.registry.JellyfishingItems;
import dev.architectury.extensions.ItemExtension;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class AirSuitItem extends ArmorItem implements ItemExtension {
    public AirSuitItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void tickArmor(ItemStack stack, Player player) {
        if (hasAllPieces(player) && player.isInWater()) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 0, true, true));
        }
    }

    private static boolean hasAllPieces(LivingEntity player) {
        return (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == JellyfishingItems.AIR_SUIT_HELMET.get() &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == JellyfishingItems.AIR_SUIT_CHESTPLATE.get() &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == JellyfishingItems.AIR_SUIT_LEGGINGS.get() &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == JellyfishingItems.AIR_SUIT_BOOTS.get());
    }
}