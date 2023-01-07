package com.jellyfishing.common.item;

import com.jellyfishing.core.Jellyfishing;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class KelpMaterial implements ArmorMaterial {

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return 180;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.WET_GRASS_PLACE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Items.KELP);
    }

    @Override
    public String getName() {
        return Jellyfishing.id("mustache").toString();
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 5;
    }
}