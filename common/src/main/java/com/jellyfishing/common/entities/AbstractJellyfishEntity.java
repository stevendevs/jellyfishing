package com.jellyfishing.common.entities;

import com.jellyfishing.core.mixin.access.DamageSourceAccess;
import com.jellyfishing.core.registry.JellyfishingEnchantments;
import com.jellyfishing.core.registry.JellyfishingItems;
import com.jellyfishing.core.registry.JellyfishingSounds;
import com.jellyfishing.core.util.ItemsUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;
import java.util.Random;

public class AbstractJellyfishEntity extends AbstractFish implements Bucketable {
    public int stingTime;
    public int stingCounter;
    public int stingDmg;
    public double stingChance;
    public double dailyDrops;
    public int dropCounter;
    public double dodgeChance;
    public double dodgeSpeed;
    public double dirX, dirY, dirZ;
    public int moveCounter;
    public int moveTime;

    public final DamageSource JELLYFISH_STING = ((DamageSourceAccess) new EntityDamageSource("sting", this)).invokeBypassArmor();
    public ItemStack JELLYFISH_ITEM;
    public Item JELLY_ITEM;

    public boolean canThisEntitySting;

    public AbstractJellyfishEntity(EntityType<? extends AbstractFish> type, Level worldIn, ItemStack JItem, Item JellyItem, double dropsPerDay, boolean canSting, int stingTicks, int stingDamage, double stingChance, double dodgeChance, double dodgeSpeed) {
        super(type, worldIn);
        JELLYFISH_ITEM = JItem;
        JELLY_ITEM = JellyItem;
        stingTime = stingTicks;
        stingCounter = 0;
        dailyDrops = dropsPerDay;
        canThisEntitySting = canSting;
        stingDmg = stingDamage;
        this.stingChance = stingChance;
        this.dodgeChance = dodgeChance;
        this.dodgeSpeed = dodgeSpeed;
        if (dropCounter <= 0) {
            dropCounter = (int) (worldIn.getRandom().nextDouble() * 24000 / dropsPerDay);
        }
        dirX = (worldIn.getRandom().nextDouble()) - .5;
        dirY = (worldIn.getRandom().nextDouble()) - .5;
        dirZ = (worldIn.getRandom().nextDouble()) - .5;
        moveCounter = 80;
        moveTime = 80;
        this.setYRot(0);
    }

    public AbstractJellyfishEntity(EntityType<? extends AbstractFish> type, Level worldIn, ItemStack JItem, Item JellyItem, double dropsPerDay, boolean canSting, int stingTicks, int stingDamage, double stingChance, double dodgeChance, double dodgeSpeed, int moveTicks) {
        super(type, worldIn);
        JELLYFISH_ITEM = JItem;
        JELLY_ITEM = JellyItem;
        stingTime = stingTicks;
        stingCounter = 0;
        dailyDrops = dropsPerDay;
        canThisEntitySting = canSting;
        stingDmg = stingDamage;
        this.stingChance = stingChance;
        this.dodgeChance = dodgeChance;
        this.dodgeSpeed = dodgeSpeed;
        if (dropCounter <= 0) {
            dropCounter = (int) (24000 / dropsPerDay);
        }
        dirX = (worldIn.getRandom().nextDouble()) - .5;
        dirY = (worldIn.getRandom().nextDouble()) - .5;
        dirZ = (worldIn.getRandom().nextDouble()) - .5;
        moveCounter = 80;
        moveTime = moveTicks;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(Items.WATER_BUCKET, 1);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.COD_HURT;
    }

    public ItemStack getJellyfishItem() {
        return JELLYFISH_ITEM;
    }

    public Item getJellyItem() {
        return JELLY_ITEM;
    }

    @Override
    public void tick() {
        if (this.stingCounter > 0) {
            --this.stingCounter;
        }
        if (dropCounter == 0 && this.isInWater()) {
            this.spawnAtLocation(new ItemStack(JELLY_ITEM, 1), -0.5F);
            dropCounter = (int) (24000 / dailyDrops);
        }
        if (dropCounter > 0) {
            dropCounter--;
        }
        if (moveCounter == 0 && this.isInWater()) {
            moveCounter = moveTime;
            this.setDeltaMovement(dirX, dirY, dirZ);
            dirX += (this.getCommandSenderWorld().getRandom().nextDouble() * 0.2) - 0.1;
            dirY += (this.getCommandSenderWorld().getRandom().nextDouble() * 0.2) - 0.1;
            dirZ += (this.getCommandSenderWorld().getRandom().nextDouble() * 0.2) - 0.1;
            if (Math.abs(dirX) > 0.5) {
                dirX = Math.abs(dirX) / dirX * 0.5;
            }
            if (Math.abs(dirY) > 0.5) {
                dirY = Math.abs(dirY) / dirY * 0.5;
            }
            if (Math.abs(dirZ) > 0.5) {
                dirZ = Math.abs(dirZ) / dirZ * 0.5;
            }
            //this.rotationYaw = (float) Math.atan2(dirZ, dirX);
        } else if (moveCounter > 0) {
            moveCounter--;
        }

        super.tick();
        if (this.onGround && !this.isInWater()) {
            this.setDeltaMovement(0.0, -0.3, 0.0);
            if (dirY > 0) {
                dirY *= -1;
            }
        }
    }

    @Override
    public void playerTouch(Player entityIn) {
        if ((!this.removeWhenFarAway(1)) || ItemsUtil.hasAllSuitPieces(entityIn)) {
            return;
        }
        if (canThisEntitySting && stingCounter == 0 && this.isInWater() && !entityIn.getCommandSenderWorld().getDifficulty().equals(Difficulty.PEACEFUL)) {
            stingCounter = stingTime;
            if (this.getCommandSenderWorld().getRandom().nextDouble() < stingChance) {
                entityIn.hurt(JELLYFISH_STING, stingDmg);
                this.playSound(JellyfishingSounds.STING.get(), 1, 1);
                this.setNewVelocity(entityIn, dodgeSpeed);
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D);
    }

    public void setNewVelocity(Entity entityIn, double multiplier) {
        this.setDeltaMovement((this.getX() - entityIn.getX()) * multiplier, (this.getY() - entityIn.getY()) * multiplier, (this.getZ() - entityIn.getZ()) * multiplier);
    }

    @Override
    protected void registerGoals() {
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == JellyfishingItems.JELLYFISH_NET.get() && player.getCooldowns().getCooldownPercent(itemstack.getItem(), 0) == 0) {
            player.swing(hand);
            if (!player.getCommandSenderWorld().isClientSide() && this.isAlive()) {
                if (!this.removeWhenFarAway(1) || dodgeChance - (EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.AGILITY.get(), itemstack) / 10.0F) < this.getCommandSenderWorld().getRandom().nextDouble()) {

                    player.getCooldowns().addCooldown(itemstack.getItem(), 20);
                    this.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 1.0F);
                    if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, itemstack) == 0 || (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, itemstack) > 0 && player.getCommandSenderWorld().getRandom().nextDouble() < (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, itemstack) / 3.0F))) {
                        itemstack.hurtAndBreak(1, player, (playerEntity) -> {
                            playerEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                        });
                    }
                    if (this.removeWhenFarAway(1)) {
                        int i = (int) (this.dodgeChance * 10);
                        while (i > 0) {
                            int j = ExperienceOrb.getExperienceValue(i);
                            i -= j;
                            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY(), this.getZ(), j));
                        }
                    }
                    boolean greaseFlag = false;
                    if (0.005 > this.getCommandSenderWorld().getRandom().nextDouble() && this.removeWhenFarAway(1)) {
                        this.spawnAtLocation(new ItemStack(JellyfishingItems.GREASE_BALL.get(), 1), -0.5F);
                        this.playSound(SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                        greaseFlag = true;
                    }
                    if (EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > 0 && this.removeWhenFarAway(1)) {
                        if (this.removeWhenFarAway(1) && player.getCommandSenderWorld().getRandom().nextDouble() < (0.1 * EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack))) {
                            try {
                                LootContext.Builder lootcontext$builder = (new LootContext.Builder(this.getServer().getLevel(this.getCommandSenderWorld().dimension()))).withParameter(LootContextParams.ORIGIN, this.getDeltaMovement()).withParameter(LootContextParams.TOOL, itemstack).withRandom(this.random).withLuck(player.getLuck() + (EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) - 1));
                                lootcontext$builder.withParameter(LootContextParams.KILLER_ENTITY, player).withParameter(LootContextParams.THIS_ENTITY, this);
                                LootTable loottable = this.level.getServer().getLootTables().get(BuiltInLootTables.FISHING);
                                List<ItemStack> list = loottable.getRandomItems(lootcontext$builder.create(LootContextParamSets.FISHING));
                                this.spawnAtLocation(list.get(0));
                                this.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                            } catch (Exception e) {
                                this.playSound(SoundEvents.FISHING_BOBBER_RETRIEVE, 1.0F, 1.0F);
                            }
                        }
                        if (!greaseFlag && 0.005 * EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > this.getCommandSenderWorld().getRandom().nextDouble() && this.removeWhenFarAway(1)) {
                            this.spawnAtLocation(new ItemStack(JellyfishingItems.GREASE_BALL.get(), 1), -0.5F);
                            this.playSound(SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                        }
                        if (0.005 * EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > this.getCommandSenderWorld().getRandom().nextDouble() && this.removeWhenFarAway(1)) {
                            this.spawnAtLocation(new ItemStack(JellyfishingItems.MUSIC_DISC_JELLYFISH_FIELDS.get(), 1), -0.5F);
                        }
                        if (0.005 * EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.PLUNDERING.get(), itemstack) > this.getCommandSenderWorld().getRandom().nextDouble() && this.removeWhenFarAway(1)) {
                            this.spawnAtLocation(new ItemStack(JellyfishingItems.BUBBLE_WAND.get(), 1), -0.5F);
                        }
                    }
                    ItemStack itemstack1 = this.getJellyfishItem();
                    this.saveToBucketTag(itemstack1);
                    if (!this.level.isClientSide) {
                        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack1);
                    }

                    if (itemstack.isEmpty()) {
                        player.setItemInHand(hand, itemstack1);
                    } else if (!player.getInventory().add(itemstack1)) {
                        player.drop(itemstack1, false);
                    }

                    this.remove(RemovalReason.UNLOADED_WITH_PLAYER);

                    return InteractionResult.SUCCESS;
                } else {
                    this.setNewVelocity(player, dodgeSpeed);
                    player.getCooldowns().addCooldown(itemstack.getItem(), 20 - 10 * EnchantmentHelper.getItemEnchantmentLevel(JellyfishingEnchantments.AGILITY.get(), itemstack));
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("StingTicks", stingCounter);
        compound.putInt("DropTicks", dropCounter);
        compound.putInt("MoveTicks", moveCounter);
        compound.putDouble("DirX", dirX);
        compound.putDouble("DirY", dirY);
        compound.putDouble("DirZ", dirZ);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.stingCounter = compound.getInt("StingTicks");
        this.dropCounter = compound.getInt("DropTicks");
        this.moveCounter = compound.getInt("MoveTicks");
        this.dirX = compound.getDouble("DirX");
        this.dirY = compound.getDouble("DirY");
        this.dirZ = compound.getDouble("DirZ");
    }

    public static boolean canJellySpawn(EntityType<? extends LivingEntity> axolotl, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.isWaterAt(pos);
    }
}