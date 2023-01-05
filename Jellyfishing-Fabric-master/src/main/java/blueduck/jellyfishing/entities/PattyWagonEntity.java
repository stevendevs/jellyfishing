package blueduck.jellyfishing.entities;

import blueduck.jellyfishing.registry.JellyfishingEntities;
import blueduck.jellyfishing.registry.JellyfishingItems;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.BoatPaddleStateC2SPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

//public class PattyWagonEntity extends Entity {
//    private static final TrackedData<Integer> TIME_SINCE_HIT = DataTracker.registerData(PattyWagonEntity.class, TrackedDataHandlerRegistry.INTEGER);
//    private static final TrackedData<Integer> FORWARD_DIRECTION = DataTracker.registerData(PattyWagonEntity.class, TrackedDataHandlerRegistry.INTEGER);
//    private static final TrackedData<Float> DAMAGE_TAKEN = DataTracker.registerData(PattyWagonEntity.class, TrackedDataHandlerRegistry.FLOAT);
//    private static final TrackedData<Integer> BOAT_TYPE = DataTracker.registerData(PattyWagonEntity.class, TrackedDataHandlerRegistry.INTEGER);
//    private static final TrackedData<Boolean> LEFT_PADDLE = DataTracker.registerData(PattyWagonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
//    private static final TrackedData<Boolean> RIGHT_PADDLE = DataTracker.registerData(PattyWagonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
//    private static final TrackedData<Integer> ROCKING_TICKS = DataTracker.registerData(PattyWagonEntity.class, TrackedDataHandlerRegistry.INTEGER);
//    private final float[] paddlePositions = new float[2];
//    private float momentum;
//    private float outOfControlTicks;
//    private float deltaRotation;
//    private int lerpSteps;
//    private double lerpX;
//    private double lerpY;
//    private double lerpZ;
//    private double lerpYaw;
//    private double lerpPitch;
//    private boolean leftInputDown;
//    private boolean rightInputDown;
//    private boolean forwardInputDown;
//    private boolean backInputDown;
//    private double waterLevel;
//    private float boatGlide;
//    private Status status;
//    private Status previousStatus;
//    private double lastYd;
//    private boolean rocking;
//    private boolean downwards;
//    private float rockingIntensity;
//    private float rockingAngle;
//    private float prevRockingAngle;
//
//    public PattyWagonEntity(EntityType<? extends PattyWagonEntity> type, World world) {
//        super(type, world);
//        this.inanimate = true;
//    }
//
//    public PattyWagonEntity(World worldIn, double x, double y, double z) {
//        this(JellyfishingEntities.PATTY_WAGON, worldIn);
//        this.setPosition(x, y, z);
//        this.setVelocity(Vec3d.ZERO);
//        this.prevX = x;
//        this.prevY = y;
//        this.prevZ = z;
//    }
//
//    protected float getEyeHeight(EntityPose poseIn, EntityDimensions sizeIn) {
//        return sizeIn.height;
//    }
//
//    protected boolean canClimb() {
//        return false;
//    }
//
//    protected void initDataTracker() {
//        this.dataTracker.startTracking(TIME_SINCE_HIT, 0);
//        this.dataTracker.startTracking(FORWARD_DIRECTION, 1);
//        this.dataTracker.startTracking(DAMAGE_TAKEN, 0.0F);
//        this.dataTracker.startTracking(BOAT_TYPE, Type.OAK.ordinal());
//        this.dataTracker.startTracking(LEFT_PADDLE, false);
//        this.dataTracker.startTracking(RIGHT_PADDLE, false);
//        this.dataTracker.startTracking(ROCKING_TICKS, 0);
//    }
//
//    @Override
//    public Packet<?> createSpawnPacket() {
//        return new EntitySpawnS2CPacket(this);
//    }
//
//    public boolean collidesWith(Entity entity) {
//        return method_30959(this, entity);
//    }
//
//    public static boolean method_30959(Entity p_242378_0_, Entity entity) {
//        return (entity.isCollidable() || entity.isPushable()) && !p_242378_0_.isConnectedThroughVehicle(entity);
//    }
//
//    public boolean isCollidable() {
//        return true;
//    }
//
//    /**
//     * Returns true if this entity should push and be pushed by other entities when colliding.
//     */
//    public boolean isPushable() {
//        return true;
//    }
//
//    protected Vec3d positionInPortal(Direction.Axis axis, BlockLocating.Rectangle result) {
//        return LivingEntity.positionInPortal(super.positionInPortal(axis, result));
//    }
//
//    /**
//     * Returns the Y offset from the entity's position for any entity riding this one.
//     */
//    public double getMountedHeightOffset() {
//        return -0.1D;
//    }
//
//    /**
//     * Called when the entity is attacked.
//     */
//    public boolean damage(DamageSource source, float amount) {
//        if (this.isInvulnerableTo(source)) {
//            return false;
//        } else if (!this.world.isClient && !this.isRemoved()) {
//            this.setForwardDirection(-this.getForwardDirection());
//            this.setTimeSinceHit(10);
//            this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
//            this.scheduleVelocityUpdate();
//            boolean flag = source.getAttacker() instanceof PlayerEntity && ((PlayerEntity)source.getAttacker()).abilities.creativeMode;
//            if (flag || this.getDamageTaken() > 40.0F) {
//                if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
//                    this.dropItem(this.getItemBoat());
//                }
//
//                this.discard();
//            }
//
//            return true;
//        } else {
//            return true;
//        }
//    }
//
//    public void onBubbleColumnSurfaceCollision(boolean downwards) {
//        if (!this.world.isClient) {
//            this.rocking = true;
//            this.downwards = downwards;
//            if (this.getRockingTicks() == 0) {
//                this.setRockingTicks(60);
//            }
//        }
//
//        this.world.addParticle(ParticleTypes.SPLASH, this.getX() + (double)this.random.nextFloat(), this.getY() + 0.7D, this.getZ() + (double)this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
//        if (this.random.nextInt(20) == 0) {
//            this.world.playSound(this.getX(), this.getY(), this.getZ(), this.getSplashSound(), this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.random.nextFloat(), false);
//        }
//
//    }
//
//    /**
//     * Applies a velocity to the entities, to push them away from eachother.
//     */
//    public void pushAwayFrom(Entity entityIn) {
//        if (entityIn instanceof PattyWagonEntity) {
//            if (entityIn.getBoundingBox().minY < this.getBoundingBox().maxY) {
//                super.pushAwayFrom(entityIn);
//            }
//        } else if (entityIn.getBoundingBox().minY <= this.getBoundingBox().minY) {
//            super.pushAwayFrom(entityIn);
//        }
//
//    }
//
//    public Item getItemBoat() {
//        return JellyfishingItems.AIR_SUIT_BOOTS;
//    }
//
//    /**
//     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
//     */
//    @Environment(EnvType.CLIENT)
//    public void animateDamage() {
//        this.setForwardDirection(-this.getForwardDirection());
//        this.setTimeSinceHit(10);
//        this.setDamageTaken(this.getDamageTaken() * 11.0F);
//    }
//
//    /**
//     * Returns true if other Entities should be prevented from moving through this Entity.
//     */
//    public boolean collides() {
//        return !this.isRemoved();
//    }
//
//    /**
//     * Sets a target for the client to interpolate towards over the next few ticks
//     */
//    @Environment(EnvType.CLIENT)
//    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
//        this.lerpX = x;
//        this.lerpY = y;
//        this.lerpZ = z;
//        this.lerpYaw = (double)yaw;
//        this.lerpPitch = (double)pitch;
//        this.lerpSteps = 10;
//    }
//
//    /**
//     * Gets the horizontal facing direction of this Entity, adjusted to take specially-treated entity types into account.
//     */
//    public Direction getMovementDirection() {
//        return this.getHorizontalFacing().rotateYClockwise();
//    }
//
//    /**
//     * Called to update the entity's position/logic.
//     */
//    public void tick() {
//        this.previousStatus = this.status;
//        this.status = this.getBoatStatus();
//        if (this.status != Status.UNDER_WATER && this.status != Status.UNDER_FLOWING_WATER) {
//            this.outOfControlTicks = 0.0F;
//        } else {
//            ++this.outOfControlTicks;
//        }
//
//        if (!this.world.isClient && this.outOfControlTicks >= 60.0F) {
//            this.removeAllPassengers();
//        }
//
//        if (this.getTimeSinceHit() > 0) {
//            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
//        }
//
//        if (this.getDamageTaken() > 0.0F) {
//            this.setDamageTaken(this.getDamageTaken() - 1.0F);
//        }
//
//        super.tick();
//        this.tickLerp();
//        if (this.isLogicalSideForUpdatingMovement()) {
//            if (this.getPassengerList().isEmpty() || !(this.getPassengerList().get(0) instanceof PlayerEntity)) {
//                this.setPaddleState(false, false);
//            }
//
//            this.updateVelocity();
//            if (this.world.isClient) {
//                this.updatePaddles();
//                this.world.sendPacket(new BoatPaddleStateC2SPacket(this.isPaddleMoving(0), this.isPaddleMoving(1)));
//            }
//
//            this.move(MovementType.SELF, this.getVelocity());
//        } else {
//            this.setVelocity(Vec3d.ZERO);
//        }
//
//        this.updateRocking();
//
//        for(int i = 0; i <= 1; ++i) {
//            if (this.isPaddleMoving(i)) {
//                if (!this.isSilent() && (double)(this.paddlePositions[i] % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && ((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F)) % (double)((float)Math.PI * 2F) >= (double)((float)Math.PI / 4F)) {
//                    SoundEvent soundevent = this.getPaddleSound();
//                    if (soundevent != null) {
//                        Vec3d vector3d = this.getRotationVec(1.0F);
//                        double d0 = i == 1 ? -vector3d.z : vector3d.z;
//                        double d1 = i == 1 ? vector3d.x : -vector3d.x;
//                        this.world.playSound((PlayerEntity)null, this.getX() + d0, this.getY(), this.getZ() + d1, soundevent, this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
//                    }
//                }
//
//                this.paddlePositions[i] = (float)((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F));
//            } else {
//                this.paddlePositions[i] = 0.0F;
//            }
//        }
//
//        this.checkBlockCollision();
//        List<Entity> list = this.world.getOtherEntities(this, this.getBoundingBox().expand((double)0.2F, (double)-0.01F, (double)0.2F), EntityPredicates.canBePushedBy(this));
//        if (!list.isEmpty()) {
//            boolean flag = !this.world.isClient && !(this.getControllingPassenger() instanceof PlayerEntity);
//
//            for (Entity entity : list) {
//                if (!entity.hasPassenger(this)) {
//                    if (flag && this.getPassengerList().size() < 2 && !entity.hasVehicle() && entity.getWidth() < this.getWidth() && entity instanceof LivingEntity && !(entity instanceof WaterCreatureEntity) && !(entity instanceof PlayerEntity)) {
//                        entity.startRiding(this);
//                    } else {
//                        this.pushAwayFrom(entity);
//                    }
//                }
//            }
//        }
//
//    }
//
//    private void updateRocking() {
//        int j;
//        if (this.world.isClient) {
//            j = this.getRockingTicks();
//            if (j > 0) {
//                this.rockingIntensity += 0.05F;
//            } else {
//                this.rockingIntensity -= 0.1F;
//            }
//
//            this.rockingIntensity = MathHelper.clamp(this.rockingIntensity, 0.0F, 1.0F);
//            this.prevRockingAngle = this.rockingAngle;
//            this.rockingAngle = 10.0F * (float) Math.sin((double) (0.5F * (float) this.world.getTime())) * this.rockingIntensity;
//        } else {
//            if (!this.rocking) {
//                this.setRockingTicks(0);
//            }
//
//            j = this.getRockingTicks();
//            if (j > 0) {
//                --j;
//                this.setRockingTicks(j);
//                int k = 60 - j - 1;
//                if (k > 0 && j == 0) {
//                    this.setRockingTicks(0);
//                    Vec3d vec3d = this.getVelocity();
//                    if (this.downwards) {
//                        this.setVelocity(vec3d.add(0.0D, -0.7D, 0.0D));
//                        this.removeAllPassengers();
//                    } else {
//                        this.setVelocity(vec3d.x, this.hasPassengerType((entity) -> {
//                            return entity instanceof PlayerEntity;
//                        }) ? 2.7D : 0.6D, vec3d.z);
//                    }
//                }
//
//                this.rocking = false;
//            }
//        }
//
//    }
//
//    @Nullable
//    protected SoundEvent getPaddleSound() {
//        switch(this.getBoatStatus()) {
//            case IN_WATER:
//            case UNDER_WATER:
//            case UNDER_FLOWING_WATER:
//                return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
//            case ON_LAND:
//                return SoundEvents.ENTITY_BOAT_PADDLE_LAND;
//            case IN_AIR:
//            default:
//                return null;
//        }
//    }
//
//    private void tickLerp() {
//        if (this.isLogicalSideForUpdatingMovement()) {
//            this.lerpSteps = 0;
//            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
//        }
//
//        if (this.lerpSteps > 0) {
//            double d = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
//            double e = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
//            double f = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
//            double g = MathHelper.wrapDegrees(this.lerpYaw - (double)this.getYaw());
//            this.setYaw(this.getYaw() + (float)g / (float)this.lerpSteps);
//            this.setPitch(this.getPitch() + (float)(this.lerpPitch - (double)this.getPitch()) / (float)this.lerpSteps);
//            --this.lerpSteps;
//            this.setPosition(d, e, f);
//            this.setRotation(this.getYaw(), this.getPitch());
//        }
//    }
//
//    public void setPaddleState(boolean left, boolean right) {
//        this.dataTracker.set(LEFT_PADDLE, left);
//        this.dataTracker.set(RIGHT_PADDLE, right);
//    }
//
//
//
//    /**
//     * Determines whether the boat is in water, gliding on land, or in air
//     */
//    private Status getBoatStatus() {
//        Status boatentity$status = this.getUnderWaterLocation();
//        if (boatentity$status != null) {
//            this.waterLevel = this.getBoundingBox().maxY;
//            return boatentity$status;
//        } else if (this.checkInWater()) {
//            return Status.IN_WATER;
//        } else {
//            float f = this.getBoatGlide();
//            if (f > 0.0F) {
//                this.boatGlide = f;
//                return Status.ON_LAND;
//            } else {
//                return Status.IN_AIR;
//            }
//        }
//    }
//
//    public float getWaterLevelAbove() {
//        Box axisalignedbb = this.getBoundingBox();
//        int i = MathHelper.floor(axisalignedbb.minX);
//        int j = MathHelper.ceil(axisalignedbb.maxX);
//        int k = MathHelper.floor(axisalignedbb.maxY);
//        int l = MathHelper.ceil(axisalignedbb.maxY - this.lastYd);
//        int i1 = MathHelper.floor(axisalignedbb.minZ);
//        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//        label39:
//        for(int k1 = k; k1 < l; ++k1) {
//            float f = 0.0F;
//
//            for(int l1 = i; l1 < j; ++l1) {
//                for(int i2 = i1; i2 < j1; ++i2) {
//                    blockpos$mutable.set(l1, k1, i2);
//                    FluidState fluidstate = this.world.getFluidState(blockpos$mutable);
//                    if (fluidstate.isIn(FluidTags.WATER)) {
//                        f = Math.max(f, fluidstate.getHeight(this.world, blockpos$mutable));
//                    }
//
//                    if (f >= 1.0F) {
//                        continue label39;
//                    }
//                }
//            }
//
//            if (f < 1.0F) {
//                return (float)blockpos$mutable.getY() + f;
//            }
//        }
//
//        return (float)(l + 1);
//    }
//
//    /**
//     * Decides how much the boat should be gliding on the land (based on any slippery blocks)
//     */
//    public float getBoatGlide() {
//        Box axisalignedbb = this.getBoundingBox();
//        Box axisalignedbb1 = new Box(axisalignedbb.minX, axisalignedbb.minY - 0.001D, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
//        int i = MathHelper.floor(axisalignedbb1.minX) - 1;
//        int j = MathHelper.ceil(axisalignedbb1.maxX) + 1;
//        int k = MathHelper.floor(axisalignedbb1.minY) - 1;
//        int l = MathHelper.ceil(axisalignedbb1.maxY) + 1;
//        int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
//        int j1 = MathHelper.ceil(axisalignedbb1.maxZ) + 1;
//        VoxelShape voxelshape = VoxelShapes.cuboid(axisalignedbb1);
//        float f = 0.0F;
//        int k1 = 0;
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//        for(int l1 = i; l1 < j; ++l1) {
//            for(int i2 = i1; i2 < j1; ++i2) {
//                int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
//                if (j2 != 2) {
//                    for(int k2 = k; k2 < l; ++k2) {
//                        if (j2 <= 0 || k2 != k && k2 != l - 1) {
//                            blockpos$mutable.set(l1, k2, i2);
//                            BlockState blockstate = this.world.getBlockState(blockpos$mutable);
//                            if (!(blockstate.getBlock() instanceof LilyPadBlock) && VoxelShapes.matchesAnywhere(blockstate.getCollisionShape(this.world, blockpos$mutable).offset((double)l1, (double)k2, (double)i2), voxelshape, BooleanBiFunction.AND)) {
//                                f += blockstate.getBlock().getSlipperiness();
//                                ++k1;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return f / (float)k1;
//    }
//
//    private boolean checkInWater() {
//        Box axisalignedbb = this.getBoundingBox();
//        int i = MathHelper.floor(axisalignedbb.minX);
//        int j = MathHelper.ceil(axisalignedbb.maxX);
//        int k = MathHelper.floor(axisalignedbb.minY);
//        int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
//        int i1 = MathHelper.floor(axisalignedbb.minZ);
//        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
//        boolean flag = false;
//        this.waterLevel = Double.MIN_VALUE;
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//        for(int k1 = i; k1 < j; ++k1) {
//            for(int l1 = k; l1 < l; ++l1) {
//                for(int i2 = i1; i2 < j1; ++i2) {
//                    blockpos$mutable.set(k1, l1, i2);
//                    FluidState fluidstate = this.world.getFluidState(blockpos$mutable);
//                    if (fluidstate.isIn(FluidTags.WATER)) {
//                        float f = (float)l1 + fluidstate.getHeight(this.world, blockpos$mutable);
//                        this.waterLevel = Math.max((double)f, this.waterLevel);
//                        flag |= axisalignedbb.minY < (double)f;
//                    }
//                }
//            }
//        }
//
//        return flag;
//    }
//
//    /**
//     * Decides whether the boat is currently underwater.
//     */
//    private Status getUnderWaterLocation() {
//        Box box = this.getBoundingBox();
//        double d = box.maxY + 0.001D;
//        int i = MathHelper.floor(box.minX);
//        int j = MathHelper.ceil(box.maxX);
//        int k = MathHelper.floor(box.maxY);
//        int l = MathHelper.ceil(d);
//        int m = MathHelper.floor(box.minZ);
//        int n = MathHelper.ceil(box.maxZ);
//        boolean bl = false;
//        BlockPos.Mutable mutable = new BlockPos.Mutable();
//
//        for(int o = i; o < j; ++o) {
//            for(int p = k; p < l; ++p) {
//                for(int q = m; q < n; ++q) {
//                    mutable.set(o, p, q);
//                    FluidState fluidState = this.world.getFluidState(mutable);
//                    if (fluidState.isIn(FluidTags.WATER) && d < (double)((float)mutable.getY() + fluidState.getHeight(this.world, mutable))) {
//                        if (!fluidState.isStill()) {
//                            return Status.UNDER_FLOWING_WATER;
//                        }
//
//                        bl = true;
//                    }
//                }
//            }
//        }
//
//        return bl ? Status.UNDER_WATER : null;
//    }
//
//    /**
//     * Update the boat's speed, based on momentum.
//     */
//    private void updateVelocity() {
//        double d0 = (double)-0.04F;
//        double d1 = this.hasNoGravity() ? 0.0D : (double)-0.04F;
//        double d2 = 0.0D;
//        this.momentum = 0.05F;
//        if (this.previousStatus == Status.IN_AIR && this.status != Status.IN_AIR && this.status != Status.ON_LAND) {
//            this.waterLevel = this.getBodyY(1.0D);
//            this.setPosition(this.getX(), (double)(this.getWaterLevelAbove() - this.getHeight()) + 0.101D, this.getZ());
//            this.setVelocity(this.getVelocity().multiply(1.0D, 0.0D, 1.0D));
//            this.lastYd = 0.0D;
//            this.status = Status.IN_WATER;
//        } else {
//            if (this.status == Status.IN_WATER) {
//                d2 = (this.waterLevel - this.getY()) / (double)this.getHeight();
//                this.momentum = 0.9F;
//            } else if (this.status == Status.UNDER_FLOWING_WATER) {
//                d1 = -7.0E-4D;
//                this.momentum = 0.9F;
//            } else if (this.status == Status.UNDER_WATER) {
//                d2 = (double)0.01F;
//                this.momentum = 0.45F;
//            } else if (this.status == Status.IN_AIR) {
//                this.momentum = 0.9F;
//            } else if (this.status == Status.ON_LAND) {
//                this.momentum = this.boatGlide;
//                if (this.getControllingPassenger() instanceof PlayerEntity) {
//                    this.boatGlide /= 2.0F;
//                }
//            }
//
//            Vec3d vector3d = this.getVelocity();
//            this.setVelocity(vector3d.x * (double)this.momentum, vector3d.y + d1, vector3d.z * (double)this.momentum);
//            this.deltaRotation *= this.momentum;
//            if (d2 > 0.0D) {
//                Vec3d vector3d1 = this.getVelocity();
//                this.setVelocity(vector3d1.x, (vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, vector3d1.z);
//            }
//        }
//
//    }
//
//    private void updatePaddles() {
//        if (this.hasPassengers()) {
//            float f = 0.0F;
//            if (this.leftInputDown) {
//                --this.deltaRotation;
//            }
//
//            if (this.rightInputDown) {
//                ++this.deltaRotation;
//            }
//
//            if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
//                f += 0.005F;
//            }
//
//            this.yaw += this.deltaRotation;
//            if (this.forwardInputDown) {
//                f += 0.04F;
//            }
//
//            if (this.backInputDown) {
//                f -= 0.005F;
//            }
//
//            this.setVelocity(this.getVelocity().add((double)(MathHelper.sin(-this.yaw * ((float)Math.PI / 180F)) * f), 0.0D, (double)(MathHelper.cos(this.yaw * ((float)Math.PI / 180F)) * f)));
//            this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown, this.leftInputDown && !this.rightInputDown || this.forwardInputDown);
//        }
//    }
//
//    public void updatePassengerPosition(Entity passenger) {
//        if (this.hasPassenger(passenger)) {
//            float f = 0.0F;
//            float g = (float)((this.isRemoved() ? 0.009999999776482582D : this.getMountedHeightOffset()) + passenger.getHeightOffset());
//            if (this.getPassengerList().size() > 1) {
//                int i = this.getPassengerList().indexOf(passenger);
//                if (i == 0) {
//                    f = 0.2F;
//                } else {
//                    f = -0.6F;
//                }
//
//                if (passenger instanceof AnimalEntity) {
//                    f = (float)((double)f + 0.2D);
//                }
//            }
//
//            Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateY(-this.yaw * 0.017453292F - 1.5707964F);
//            passenger.setPosition(this.getX() + vec3d.x, this.getY() + (double)g, this.getZ() + vec3d.z);
//            passenger.yaw += this.deltaRotation;
//            passenger.setHeadYaw(passenger.getHeadYaw() + this.deltaRotation);
//            this.copyEntityData(passenger);
//            if (passenger instanceof AnimalEntity && this.getPassengerList().size() > 1) {
//                int j = passenger.getId() % 2 == 0 ? 90 : 270;
//                passenger.setBodyYaw(((AnimalEntity)passenger).bodyYaw + (float)j);
//                passenger.setHeadYaw(passenger.getHeadYaw() + (float)j);
//            }
//
//        }
//    }
//
//dont put back
//                                        public Vec3d updatePassengerForDismount(LivingEntity passenger) {
//                                            Vec3d vec3d = getPassengerDismountOffset((double)(this.getWidth() * MathHelper.SQUARE_ROOT_OF_TWO), (double)passenger.getWidth(), passenger.getYaw());
//                                            double d = this.getX() + vec3d.x;
//                                            double e = this.getZ() + vec3d.z;
//                                            BlockPos blockPos = new BlockPos(d, this.getBoundingBox().maxY, e);
//                                            BlockPos blockPos2 = blockPos.down();
//                                            if (!this.world.isWater(blockPos2)) {
//                                                List<Vec3d> list = Lists.newArrayList();
//                                                double f = this.world.getDismountHeight(blockPos);
//                                                if (Dismounting.canDismountInBlock(f)) {
//                                                    list.add(new Vec3d(d, (double)blockPos.getY() + f, e));
//                                                }
//
//                                                double g = this.world.getDismountHeight(blockPos2);
//                                                if (Dismounting.canDismountInBlock(g)) {
//                                                    list.add(new Vec3d(d, (double)blockPos2.getY() + g, e));
//                                                }
//
//                                                UnmodifiableIterator var14 = passenger.getPoses().iterator();
//
//                                                while(var14.hasNext()) {
//                                                    EntityPose entityPose = (EntityPose)var14.next();
//                                                    Iterator var16 = list.iterator();
//
//                                                    while(var16.hasNext()) {
//                                                        Vec3d vec3d2 = (Vec3d)var16.next();
//                                                        if (Dismounting.canPlaceEntityAt(this.world, vec3d2, passenger, entityPose)) {
//                                                            passenger.setPose(entityPose);
//                                                            return vec3d2;
//                                                        }
//                                                    }
//                                                }
//                                            }
//
//                                            return super.updatePassengerForDismount(passenger);
//                                        }
//
//    /**
//     * Applies this boat's yaw to the given entity. Used to update the orientation of its passenger.
//     */
//    protected void copyEntityData(Entity entity) {
//        entity.setBodyYaw(this.yaw);
//        float f = MathHelper.wrapDegrees(entity.yaw - this.yaw);
//        float g = MathHelper.clamp(f, -105.0F, 105.0F);
//        entity.prevYaw += g - f;
//        entity.yaw += g - f;
//        entity.setHeadYaw(entity.yaw);
//    }
//
//    /**
//     * Applies this entity's orientation (pitch/yaw) to another entity. Used to update passenger orientation.
//     */
//    @Environment(EnvType.CLIENT)
//    public void applyOrientationToEntity(Entity entityToUpdate) {
//        this.copyEntityData(entityToUpdate);
//    }
//
//    protected void writeCustomDataToNbt(NbtCompound compound) {
//        compound.putString("Type", this.getBoatType().getName());
//    }
//
//    /**
//     * (abstract) Protected helper method to read subclass entity data from NBT.
//     */
//    protected void readCustomDataFromNbt(NbtCompound compound) {
//        if (compound.contains("Type", 8)) {
//            this.setBoatType(Type.getTypeFromString(compound.getString("Type")));
//        }
//
//    }
//
//    public ActionResult interact(PlayerEntity player, Hand hand) {
//        if (player.shouldCancelInteraction()) {
//            return ActionResult.PASS;
//        } else if (this.outOfControlTicks < 60.0F) {
//            if (!this.world.isClient) {
//                return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
//            } else {
//                return ActionResult.SUCCESS;
//            }
//        } else {
//            return ActionResult.PASS;
//        }
//    }
//
//    protected void fall(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
//        this.lastYd = this.getVelocity().y;
//        if (!this.hasVehicle()) {
//            if (onGroundIn) {
//                if (this.fallDistance > 3.0F) {
//                    if (this.status != Status.ON_LAND) {
//                        this.fallDistance = 0.0F;
//                        return;
//                    }
//
//                    this.handleFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
//                    if (!this.world.isClient && !this.isRemoved()) {
//                        this.kill();
//                        if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
//                            for(int i = 0; i < 3; ++i) {
//                                this.dropItem(this.getBoatType().asPlank());
//                            }
//
//                            for(int j = 0; j < 2; ++j) {
//                                this.dropItem(Items.STICK);
//                            }
//                        }
//                    }
//                }
//
//                this.fallDistance = 0.0F;
//            } else if (!this.world.getFluidState(this.getBlockPos().down()).isIn(FluidTags.WATER) && y < 0.0D) {
//                this.fallDistance = (float)((double)this.fallDistance - y);
//            }
//
//        }
//    }
//
//    public boolean isPaddleMoving(int side) {
//        return this.dataTracker.<Boolean>get(side == 0 ? LEFT_PADDLE : RIGHT_PADDLE) && this.getControllingPassenger() != null;
//    }
//
//    /**
//     * Sets the damage taken from the last hit.
//     */
//    public void setDamageTaken(float damageTaken) {
//        this.dataTracker.set(DAMAGE_TAKEN, damageTaken);
//    }
//
//    /**
//     * Gets the damage taken from the last hit.
//     */
//    public float getDamageTaken() {
//        return this.dataTracker.get(DAMAGE_TAKEN);
//    }
//
//    /**
//     * Sets the time to count down from since the last time entity was hit.
//     */
//    public void setTimeSinceHit(int timeSinceHit) {
//        this.dataTracker.set(TIME_SINCE_HIT, timeSinceHit);
//    }
//
//    /**
//     * Gets the time since the last hit.
//     */
//    public int getTimeSinceHit() {
//        return this.dataTracker.get(TIME_SINCE_HIT);
//    }
//
//    private void setRockingTicks(int ticks) {
//        this.dataTracker.set(ROCKING_TICKS, ticks);
//    }
//
//    private int getRockingTicks() {
//        return this.dataTracker.get(ROCKING_TICKS);
//    }
//
//    @Environment(EnvType.CLIENT)
//    public float getRockingAngle(float partialTicks) {
//        return MathHelper.lerp(partialTicks, this.prevRockingAngle, this.rockingAngle);
//    }
//
//    /**
//     * Sets the forward direction of the entity.
//     */
//    public void setForwardDirection(int forwardDirection) {
//        this.dataTracker.set(FORWARD_DIRECTION, forwardDirection);
//    }
//
//    /**
//     * Gets the forward direction of the entity.
//     */
//    public int getForwardDirection() {
//        return this.dataTracker.get(FORWARD_DIRECTION);
//    }
//
//    public void setBoatType(Type boatType) {
//        this.dataTracker.set(BOAT_TYPE, boatType.ordinal());
//    }
//
//    public Type getBoatType() {
//        return Type.byId(this.dataTracker.get(BOAT_TYPE));
//    }
//
//    protected boolean canAddPassenger(Entity passenger) {
//        return this.getPassengerList().size() < 2 && !this.isSubmergedIn(FluidTags.WATER);
//    }
//
//    /**
//     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
//     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
//     */
//    @Nullable
//    public Entity getControllingPassenger() {
//        List<Entity> list = this.getPassengerList();
//        return list.isEmpty() ? null : list.get(0);
//    }
//
//    @Environment(EnvType.CLIENT)
//    public void updateInputs(boolean leftInputDown, boolean rightInputDown, boolean forwardInputDown, boolean backInputDown) {
//        this.leftInputDown = leftInputDown;
//        this.rightInputDown = rightInputDown;
//        this.forwardInputDown = forwardInputDown;
//        this.backInputDown = backInputDown;
//    }
//
//    public boolean isSubmergedInWater() {
//        return this.status == Status.UNDER_WATER || this.status == Status.UNDER_FLOWING_WATER;
//    }
//
//    public enum Status {
//        IN_WATER,
//        UNDER_WATER,
//        UNDER_FLOWING_WATER,
//        ON_LAND,
//        IN_AIR;
//    }
//
//    public enum Type {
//        OAK(Blocks.OAK_PLANKS, "oak"),
//        SPRUCE(Blocks.SPRUCE_PLANKS, "spruce"),
//        BIRCH(Blocks.BIRCH_PLANKS, "birch"),
//        JUNGLE(Blocks.JUNGLE_PLANKS, "jungle"),
//        ACACIA(Blocks.ACACIA_PLANKS, "acacia"),
//        DARK_OAK(Blocks.DARK_OAK_PLANKS, "dark_oak");
//
//        private final String name;
//        private final Block block;
//
//        Type(Block block, String name) {
//            this.name = name;
//            this.block = block;
//        }
//
//        public String getName() {
//            return this.name;
//        }
//
//        public Block asPlank() {
//            return this.block;
//        }
//
//        public String toString() {
//            return this.name;
//        }
//
//        /**
//         * Get a boat type by it's enum ordinal
//         */
//        public static Type byId(int id) {
//            Type[] aboatentity$type = values();
//            if (id < 0 || id >= aboatentity$type.length) {
//                id = 0;
//            }
//
//            return aboatentity$type[id];
//        }
//
//        public static Type getTypeFromString(String nameIn) {
//            Type[] aboatentity$type = values();
//
//            for(int i = 0; i < aboatentity$type.length; ++i) {
//                if (aboatentity$type[i].getName().equals(nameIn)) {
//                    return aboatentity$type[i];
//                }
//            }
//
//            return aboatentity$type[0];
//        }
//    }
//}