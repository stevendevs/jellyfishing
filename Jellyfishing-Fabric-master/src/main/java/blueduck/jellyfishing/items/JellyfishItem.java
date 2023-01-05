package blueduck.jellyfishing.items;

import blueduck.jellyfishing.entities.AbstractJellyfishEntity;
import blueduck.jellyfishing.misc.config.JellyfishingConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Objects;

public class JellyfishItem extends Item {
    public final EntityType<?> entityType;

    public JellyfishItem(Settings settings, EntityType<?> entityType) {
        super(settings);
        this.entityType = entityType;

        var fallibleItemDispenserBehavior = new FallibleItemDispenserBehavior() {
            public ItemStack dispenseSilently(BlockPointer source, ItemStack stack) {
                var direction = source.getBlockState().get(DispenserBlock.FACING);
                var entityType = ((JellyfishItem) stack.getItem()).entityType;
                var entity = (AbstractJellyfishEntity) entityType.spawnFromItemStack(source.getWorld(), stack, null, source.getPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                if (entity != null) {
                    entity.setFromBucket(true);
                }
                stack.decrement(1);
                return stack;
            }
        };
        if (!JellyfishingConfig.NOPLACE_JELLYFISH.get()) {
            DispenserBlock.registerBehavior(this, fallibleItemDispenserBehavior);
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();
        var player = context.getPlayer();
        var hand = context.getHand();
        if (world.isClient || JellyfishingConfig.NOPLACE_JELLYFISH.get()) {
            AbstractJellyfishEntity entity = (AbstractJellyfishEntity) this.entityType.create(world);
            assert player != null;
            assert entity != null;
            player.dropStack(new ItemStack(entity.getJellyItem(), world.getRandom().nextInt(3) + 2), 0F);
            entity.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
            if (!player.isCreative()) {
                player.getStackInHand(hand).decrement(1);
            }
        } else {
            var stack = context.getStack();
            var pos = context.getBlockPos();
            var direction = context.getSide();
            var blockstate = world.getBlockState(pos);
            BlockPos pos1;
            if (blockstate.getCollisionShape(world, pos).isEmpty()) {
                pos1 = pos;
            } else {
                pos1 = pos.offset(direction);
            }

            var entity = this.entityType.spawnFromItemStack((ServerWorld) world, stack, null, pos1, SpawnReason.BUCKET, true, false);
            if (entity != null) {
                ((FishEntity)entity).setFromBucket(true);
            }
            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                stack.decrement(1);
            }

        }
        return ActionResult.SUCCESS;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (JellyfishingConfig.NOPLACE_JELLYFISH.get()) {
            var entity = (AbstractJellyfishEntity) this.entityType.create(world);
            assert entity != null;
            player.dropStack(new ItemStack(entity.getJellyItem(), world.getRandom().nextInt(3) + 2), 0F);
            entity.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
            if (!player.isCreative()) {
                player.getStackInHand(hand).decrement(1);
            }
        }

        return super.use(world, player, hand);
    }
}
