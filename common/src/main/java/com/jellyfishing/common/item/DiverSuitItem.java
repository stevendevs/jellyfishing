package com.jellyfishing.common.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DiverSuitItem extends ArmorItem {

    public DiverSuitItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (this.equals(JellyfishingItems.DIVER_SUIT_HELMET.get())) {
            if (player.isInWater()) {
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 10, 0, true, true));
            }
            player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 10, 0, true, true));
        }
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (this.equals(JellyfishingItems.DIVER_SUIT_HELMET.get())) {
            if (player.isInWater()) {
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 10, 0, true, true));
            }
            player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 10, 0, true, true));
        }

    }
}
