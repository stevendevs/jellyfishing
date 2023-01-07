package com.jellyfishing.core.mixin;

import com.jellyfishing.core.registry.JellyfishingItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot slot);

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
//        updateDiverSuit();
        updateKelpMustache();
        updateSandySuit();
    }

    private void updateDiverSuit() {
        var itemStack = this.getItemBySlot(EquipmentSlot.HEAD);
        if (itemStack.getItem() == JellyfishingItems.DIVER_SUIT_HELMET.get()) {
            if (this.isInWater()) {
                this.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 0, true, true));
            }
            this.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 10, 0, true, true));
        }
    }

    private void updateKelpMustache() {
        var itemStack = this.getItemBySlot(EquipmentSlot.HEAD);
        if (itemStack.getItem() == JellyfishingItems.KELP_MUSTACHE.get()) {
            if (level.getRandom().nextDouble() < 0.025) {
                itemStack.hurtAndBreak(1, this, (delete) -> delete.broadcastBreakEvent(EquipmentSlot.HEAD));
            }
            this.addEffect(new MobEffectInstance(Objects.requireNonNull(MobEffect.byId(11)), 10, 0));
        }
    }

    private void updateSandySuit() {
        if (hasAllPieces(this) && this.isInWater()) {
            this.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 0, true, true));
        }
    }

    private static boolean hasAllPieces(LivingEntity player) {
        return (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == JellyfishingItems.AIR_SUIT_HELMET.get() &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == JellyfishingItems.AIR_SUIT_CHESTPLATE.get() &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == JellyfishingItems.AIR_SUIT_LEGGINGS.get() &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == JellyfishingItems.AIR_SUIT_BOOTS.get());
    }
}