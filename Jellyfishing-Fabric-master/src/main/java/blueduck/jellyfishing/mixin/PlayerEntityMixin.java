package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
//        updateDiverSuit();
//        updateKelpMustache();
//        updateSandySuit();
    }

    private void updateDiverSuit() {
        var itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
        if (itemStack.getItem() == JellyfishingItems.DIVER_SUIT_HELMET) {
            if (this.isTouchingWater()) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 10, 0, true, true));
            }
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 10, 0, true, true));
        }
    }

    private void updateKelpMustache() {
        var itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
        if (itemStack.getItem() == JellyfishingItems.KELP_MUSTACHE) {
            if (world.getRandom().nextDouble() < 0.025) {
                itemStack.damage(1, this, (delete) -> delete.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
            }
            this.addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(11), 10, 0));
        }
    }

    private void updateSandySuit() {
        if (JellyfishingItems.hasAllSuitPieces(this) && this.isTouchingWater()) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 10, 0, true, true));
        }
    }
}