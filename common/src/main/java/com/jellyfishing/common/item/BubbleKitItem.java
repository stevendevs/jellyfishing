package com.jellyfishing.common.item;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Objects;

public class BubbleKitItem extends ItemNameBlockItem {
    public BubbleKitItem(Properties settings) {
        super(JellyfishingBlocks.BUBBLE_BLOCK.get(), settings);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        if (!context.canPlace()) {
            return InteractionResult.FAIL;
        } else {
            BlockPlaceContext itemPlacementContext = this.updatePlacementContext(context);
            if (itemPlacementContext == null) {
                return InteractionResult.FAIL;
            } else {
                BlockState blockState = this.getPlacementState(itemPlacementContext);
                if (blockState == null) {
                    return InteractionResult.FAIL;
                } else if (!this.placeBlock(itemPlacementContext, blockState)) {
                    return InteractionResult.FAIL;
                } else {
                    BlockPos blockPos = itemPlacementContext.getClickedPos();
                    Level level = itemPlacementContext.getLevel();
                    Player player = itemPlacementContext.getPlayer();
                    ItemStack stack = itemPlacementContext.getItemInHand();
                    BlockState blockState1 = level.getBlockState(blockPos);
                    Block block = blockState1.getBlock();
                    if (block == blockState.getBlock()) {
                        blockState1 = this.placeFromTag(blockPos, level, stack, blockState1);
                        this.updateCustomBlockEntityTag(blockPos, level, player, stack, blockState1);
                        block.setPlacedBy(level, blockPos, blockState1, player, stack);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockPos, stack);
                        }
                    }

                    SoundType soundGroup = blockState1.getSoundType();
                    level.playSound(player, blockPos, this.getPlaceSound(blockState1), SoundSource.BLOCKS, (soundGroup.getVolume() + 1.0F) / 2.0F, soundGroup.getPitch() * 0.8F);
                    if (player == null || !player.isCreative()) {
                        stack.hurtAndBreak(1, Objects.requireNonNull(context.getPlayer()), (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
    }

    private BlockState placeFromTag(BlockPos pos, Level level, ItemStack stack, BlockState state) {
        BlockState blockState = state;
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            var tag1 = tag.getCompound("BlockStateTag");
            var stateManager = state.getBlock().getStateDefinition();

            for(String s : tag1.getAllKeys()) {
                Property<?> property = stateManager.getProperty(s);
                if (property != null) {
                    var s1 = Objects.requireNonNull(tag1.get(s)).getAsString();
                    blockState = with(blockState, property, s1);
                }
            }
        }

        if (blockState != state) {
            level.setBlock(pos, blockState, 2);
        }

        return blockState;
    }

    private static <T extends Comparable<T>> BlockState with(BlockState state, Property<T> property, String name) {
        return property.getValue(name).map((value) -> state.setValue(property, value)).orElse(state);
    }
}