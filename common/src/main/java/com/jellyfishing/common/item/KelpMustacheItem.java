package com.jellyfishing.common.item;

import dev.architectury.extensions.ItemExtension;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class KelpMustacheItem extends ArmorItem implements ItemExtension {
    public KelpMustacheItem(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    @Override
    public void tickArmor(ItemStack stack, Player player) {
        if (player.getLevel().getRandom().nextDouble() < 0.025) {
            stack.hurtAndBreak(1, player, (delete) -> delete.broadcastBreakEvent(EquipmentSlot.HEAD));
        }
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 0));
    }
}
