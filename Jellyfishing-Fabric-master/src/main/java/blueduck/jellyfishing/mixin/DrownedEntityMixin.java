package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DrownedEntity.class)
public abstract class DrownedEntityMixin extends ZombieEntity {
    public DrownedEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    //TODO: MAKE THIS WORK
    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    public void initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        if (this.random.nextDouble() < 0.025) {
            if (this.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(JellyfishingItems.KELP_MUSTACHE));
                this.handDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.085F;
            }
        }

        if (this.random.nextDouble() < 0.01) {
            if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.JELLYFISH_NET));
                this.handDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.085F;
            }
        }

        if (this.random.nextDouble() < 0.01) {
            if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.SPATULA));
                this.handDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.085F;
            }
        }

        if (this.random.nextDouble() < 0.02) {
            if (this.random.nextDouble() < 0.9) {
                if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                    this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.KARATE_GLOVE));
                    this.handDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.085F;
                }

                if (this.getEquippedStack(EquipmentSlot.OFFHAND).isEmpty()) {
                    this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.KARATE_GLOVE));
                    this.handDropChances[EquipmentSlot.OFFHAND.getEntitySlotId()] = 0.085F;
                }
            } else if (this.random.nextDouble() < 0.5) {
                if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                    this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE));
                    this.handDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.085F;
                }

                if (this.getEquippedStack(EquipmentSlot.OFFHAND).isEmpty()) {
                    this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE));
                    this.handDropChances[EquipmentSlot.OFFHAND.getEntitySlotId()] = 0.085F;
                }
            } else {
                if (this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                    this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.POWER_KARATE_GLOVE));
                    this.handDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.085F;
                }

                if (this.getEquippedStack(EquipmentSlot.OFFHAND).isEmpty()) {
                    this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.POWER_KARATE_GLOVE));
                    this.handDropChances[EquipmentSlot.OFFHAND.getEntitySlotId()] = 0.085F;
                }
            }
        }

        if (this.random.nextDouble() < 0.01) {
            this.equipStack(EquipmentSlot.HEAD, new ItemStack(JellyfishingItems.AIR_SUIT_HELMET));
            this.handDropChances[EquipmentSlot.HEAD.getEntitySlotId()] = 0.01F;

            if (this.random.nextDouble() < 0.5) {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE));
                this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 0.01F;
                this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(JellyfishingItems.MASTER_KARATE_GLOVE));
                this.handDropChances[EquipmentSlot.OFFHAND.getEntitySlotId()] = 0.01F;
            }
        }
    }
}