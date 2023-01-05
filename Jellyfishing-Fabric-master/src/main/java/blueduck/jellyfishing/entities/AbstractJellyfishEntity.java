package blueduck.jellyfishing.entities;

import blueduck.jellyfishing.misc.config.JellyfishingConfig;
import blueduck.jellyfishing.registry.JellyfishingEnchantments;
import blueduck.jellyfishing.registry.JellyfishingItems;
import blueduck.jellyfishing.registry.JellyfishingSounds;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class AbstractJellyfishEntity extends FishEntity implements Bucketable {
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

    public static final DamageSource JELLYFISH_STING = (new DamageSource("sting")).setBypassesArmor();
    public ItemStack JELLYFISH_ITEM;
    public Item JELLY_ITEM;

    public boolean canThisEntitySting;

    public AbstractJellyfishEntity(EntityType<? extends FishEntity> type, World worldIn, ItemStack JItem, Item JellyItem, double dropsPerDay, boolean canSting, int stingTicks, int stingDamage, double stingChance, double dodgeChance, double dodgeSpeed) {
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
        this.yaw = 0;
    }

    public AbstractJellyfishEntity(EntityType<? extends FishEntity> type, World worldIn, ItemStack JItem, Item JellyItem, double dropsPerDay, boolean canSting, int stingTicks, int stingDamage, double stingChance, double dodgeChance, double dodgeSpeed, int moveTicks) {
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
    public ItemStack getBucketItem() {
        return new ItemStack(Items.WATER_BUCKET, 1);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return JellyfishingSounds.BLANK;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    public ItemStack getJellyfishItem() {
        return JELLYFISH_ITEM;
    }

    public Item getJellyItem() {
        return JELLY_ITEM;
    }

    public void tickMovement() {
        if (this.stingCounter > 0) {
            --this.stingCounter;
        }
        if (dropCounter == 0 && this.isTouchingWater()) {
            this.dropStack(new ItemStack(JELLY_ITEM, 1), -0.5F);
            dropCounter = (int) (24000 / dailyDrops);
        }
        if (dropCounter > 0) {
            dropCounter--;
        }
        if (moveCounter == 0 && this.isTouchingWater()) {
            moveCounter = moveTime;
            this.setVelocity(dirX, dirY, dirZ);
            dirX += (this.getEntityWorld().getRandom().nextDouble() * 0.2) - 0.1;
            dirY += (this.getEntityWorld().getRandom().nextDouble() * 0.2) - 0.1;
            dirZ += (this.getEntityWorld().getRandom().nextDouble() * 0.2) - 0.1;
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

        super.tickMovement();
        if (this.onGround && !this.isTouchingWater()) {
            this.setVelocity(0.0, -0.3, 0.0);
            if (dirY > 0) {
                dirY *= -1;
            }
        }
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if ((!this.canImmediatelyDespawn(1) && !(JellyfishingConfig.CAUGHT_JELLYFISH_STING.get())) || JellyfishingItems.hasAllSuitPieces(entityIn)) {
            return;
        }
        if (canThisEntitySting && stingCounter == 0 && this.isTouchingWater() && JellyfishingConfig.JELLYFISH_STING.get() && !entityIn.getEntityWorld().getDifficulty().equals(Difficulty.PEACEFUL)) {
            stingCounter = stingTime;
            if (this.getEntityWorld().getRandom().nextDouble() < stingChance) {
                entityIn.damage(JELLYFISH_STING, stingDmg);
                this.playSound(JellyfishingSounds.STING, 1, 1);
                this.setNewVelocity(entityIn, dodgeSpeed);
            }
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D);
    }

    public void setNewVelocity(Entity entityIn, double multiplier) {
        this.setVelocity((this.getX() - entityIn.getX()) * multiplier, (this.getY() - entityIn.getY()) * multiplier, (this.getZ() - entityIn.getZ()) * multiplier);
    }

    protected void registerGoals() {
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.getItem() == JellyfishingItems.JELLYFISH_NET && player.getItemCooldownManager().getCooldownProgress(itemstack.getItem(), 0) == 0) {
            player.swingHand(hand);
            if (!player.getEntityWorld().isClient() && this.isAlive()) {
                if (!this.canImmediatelyDespawn(1) || dodgeChance - (EnchantmentHelper.getLevel(JellyfishingEnchantments.AGILITY, itemstack) / 10) < this.getEntityWorld().getRandom().nextDouble()) {

                    player.getItemCooldownManager().set(itemstack.getItem(), 20);
                    this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, 1.0F);
                    if (EnchantmentHelper.getLevel(Enchantments.UNBREAKING, itemstack) == 0 || (EnchantmentHelper.getLevel(Enchantments.UNBREAKING, itemstack) > 0 && player.getEntityWorld().getRandom().nextDouble() < (EnchantmentHelper.getLevel(Enchantments.UNBREAKING, itemstack) / 3))) {
                        itemstack.damage(1, player, (playerEntity) -> {
                            playerEntity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                        });
                    }
                    if (this.canImmediatelyDespawn(1)) {
                        int i = (int) (this.dodgeChance * 10);
                        while (i > 0) {
                            int j = ExperienceOrbEntity.roundToOrbSize(i);
                            i -= j;
                            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.getX(), this.getY(), this.getZ(), j));
                        }
                    }
                    boolean greaseFlag = false;
                    if (0.005 > this.getEntityWorld().getRandom().nextDouble() && this.canImmediatelyDespawn(1)) {
                        this.dropStack(new ItemStack(JellyfishingItems.GREASE_BALL, 1), -0.5F);
                        this.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                        greaseFlag = true;
                    }
                    if (EnchantmentHelper.getLevel(JellyfishingEnchantments.PLUNDERING, itemstack) > 0 && this.canImmediatelyDespawn(1)) {
                        if (this.canImmediatelyDespawn(1) && player.getEntityWorld().getRandom().nextDouble() < (0.1 * EnchantmentHelper.getLevel(JellyfishingEnchantments.PLUNDERING, itemstack))) {
                            try {
                                LootContext.Builder lootcontext$builder = (new LootContext.Builder(this.getServer().getWorld(this.getEntityWorld().getRegistryKey()))).parameter(LootContextParameters.ORIGIN, this.getVelocity()).parameter(LootContextParameters.TOOL, itemstack).random(this.random).luck(player.getLuck() + (EnchantmentHelper.getLevel(JellyfishingEnchantments.PLUNDERING, itemstack) - 1));
                                lootcontext$builder.parameter(LootContextParameters.KILLER_ENTITY, player).parameter(LootContextParameters.THIS_ENTITY, this);
                                LootTable loottable = this.world.getServer().getLootManager().getTable(LootTables.FISHING_GAMEPLAY);
                                List<ItemStack> list = loottable.generateLoot(lootcontext$builder.build(LootContextTypes.FISHING));
                                this.dropStack(list.get(0));
                                this.playSound(SoundEvents.ENTITY_FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                            } catch (Exception e) {
                                this.playSound(SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, 1.0F, 1.0F);
                            }
                        }
                        if (!greaseFlag && 0.005 * EnchantmentHelper.getLevel(JellyfishingEnchantments.PLUNDERING, itemstack) > this.getEntityWorld().getRandom().nextDouble() && this.canImmediatelyDespawn(1)) {
                            this.dropStack(new ItemStack(JellyfishingItems.GREASE_BALL, 1), -0.5F);
                            this.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                        }
                        if (0.005 * EnchantmentHelper.getLevel(JellyfishingEnchantments.PLUNDERING, itemstack) > this.getEntityWorld().getRandom().nextDouble() && this.canImmediatelyDespawn(1)) {
                            this.dropStack(new ItemStack(JellyfishingItems.MUSIC_DISC_JELLYFISH_FIELDS, 1), -0.5F);
                        }
                        if (0.005 * EnchantmentHelper.getLevel(JellyfishingEnchantments.PLUNDERING, itemstack) > this.getEntityWorld().getRandom().nextDouble() && this.canImmediatelyDespawn(1)) {
                            this.dropStack(new ItemStack(JellyfishingItems.BUBBLE_WAND, 1), -0.5F);
                        }
                    }
                    ItemStack itemstack1 = this.getJellyfishItem();
                    this.copyDataToStack(itemstack1);
                    if (!this.world.isClient) {
                        Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity) player, itemstack1);
                    }

                    if (itemstack.isEmpty()) {
                        player.setStackInHand(hand, itemstack1);
                    } else if (!player.inventory.insertStack(itemstack1)) {
                        player.dropItem(itemstack1, false);
                    }

                    this.remove(RemovalReason.UNLOADED_WITH_PLAYER);

                    return ActionResult.SUCCESS;
                } else {
                    this.setNewVelocity(player, dodgeSpeed);
                    player.getItemCooldownManager().set(itemstack.getItem(), 20 - 10 * EnchantmentHelper.getLevel(JellyfishingEnchantments.AGILITY, itemstack));
                    return ActionResult.SUCCESS;
                }
            }
        }
        return super.interactMob(player, hand);
    }

    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
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
    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.stingCounter = compound.getInt("StingTicks");
        this.dropCounter = compound.getInt("DropTicks");
        this.moveCounter = compound.getInt("MoveTicks");
        this.dirX = compound.getDouble("DirX");
        this.dirY = compound.getDouble("DirY");
        this.dirZ = compound.getDouble("DirZ");
    }

    public static boolean canSpawn(EntityType<? extends AbstractJellyfishEntity> type, World world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.isWater(pos);
    }
}