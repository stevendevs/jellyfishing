package com.jellyfishing.common.item;

import com.jellyfishing.common.entities.AbstractJellyfishEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class JellyfishItem extends Item {
    public final EntityType<?> entityType;

    public JellyfishItem(Properties settings, EntityType<?> entityType) {
        super(settings);
        this.entityType = entityType;

        var fallibleItemDispenserBehavior = new OptionalDispenseItemBehavior() {
            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                var direction = source.getBlockState().getValue(DispenserBlock.FACING);
                var entityType = ((JellyfishItem) stack.getItem()).entityType;
                var entity = (AbstractJellyfishEntity) entityType.spawn(source.getLevel(), stack, null, source.getPos().offset(direction.getNormal()), MobSpawnType.DISPENSER, direction != Direction.UP, false);
                if (entity != null) {
                    entity.setFromBucket(true);
                }
                stack.shrink(1);
                return stack;
            }
        };
        if (!false) {
            DispenserBlock.registerBehavior(this, fallibleItemDispenserBehavior);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        if (level.isClientSide() || false) {
            AbstractJellyfishEntity entity = (AbstractJellyfishEntity) this.entityType.create(level);
            use(level, player, hand, entity);
        } else {
            ItemStack stack = context.getItemInHand();
            BlockPos pos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = level.getBlockState(pos);
            BlockPos pos1;
            if (blockstate.getCollisionShape(level, pos).isEmpty()) {
                pos1 = pos;
            } else {
                pos1 = pos.offset(direction.getNormal());
            }

            var entity = this.entityType.spawn((ServerLevel) level, stack, null, pos1, MobSpawnType.BUCKET, true, false);
            if (entity != null) {
                ((AbstractFish)entity).setFromBucket(true);
            }
            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                stack.shrink(1);
            }

        }
        return InteractionResult.SUCCESS;
    }

    private void use(Level level, Player player, InteractionHand hand, AbstractJellyfishEntity entity) {
        player.spawnAtLocation(new ItemStack(entity.getJellyItem(), level.getRandom().nextInt(3) + 2), 0F);
        entity.remove(Entity.RemovalReason.UNLOADED_WITH_PLAYER);
        if (!player.isCreative()) {
            player.getItemInHand(hand).shrink(1);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (false) {
            AbstractJellyfishEntity entity = (AbstractJellyfishEntity) this.entityType.create(level);
            use(level, player, usedHand, entity);
        }

        return super.use(level, player, usedHand);
    }
}