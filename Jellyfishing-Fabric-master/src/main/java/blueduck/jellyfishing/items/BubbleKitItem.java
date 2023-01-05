package blueduck.jellyfishing.items;

import blueduck.jellyfishing.registry.JellyfishingBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BubbleKitItem extends AliasedBlockItem {
    public BubbleKitItem(Settings settings) {
        super(JellyfishingBlocks.BUBBLE_BLOCK, settings);
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        if (!context.canPlace()) {
            return ActionResult.FAIL;
        } else {
            var itemPlacementContext = this.getPlacementContext(context);
            if (itemPlacementContext == null) {
                return ActionResult.FAIL;
            } else {
                var blockState = this.getPlacementState(itemPlacementContext);
                if (blockState == null) {
                    return ActionResult.FAIL;
                } else if (!this.place(itemPlacementContext, blockState)) {
                    return ActionResult.FAIL;
                } else {
                    var blockPos = itemPlacementContext.getBlockPos();
                    var world = itemPlacementContext.getWorld();
                    var player = itemPlacementContext.getPlayer();
                    var stack = itemPlacementContext.getStack();
                    var blockState1 = world.getBlockState(blockPos);
                    var block = blockState1.getBlock();
                    if (block == blockState.getBlock()) {
                        blockState1 = this.placeFromTag(blockPos, world, stack, blockState1);
                        this.postPlacement(blockPos, world, player, stack, blockState1);
                        block.onPlaced(world, blockPos, blockState1, player, stack);
                        if (player instanceof ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)player, blockPos, stack);
                        }
                    }

                    var soundGroup = blockState1.getSoundGroup();
                    world.playSound(player, blockPos, this.getPlaceSound(blockState1), SoundCategory.BLOCKS, (soundGroup.getVolume() + 1.0F) / 2.0F, soundGroup.getPitch() * 0.8F);
                    if (player == null || !player.abilities.creativeMode) {
                        stack.damage(1, Objects.requireNonNull(context.getPlayer()), (livingEntity) -> livingEntity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    }

                    return ActionResult.success(world.isClient);
                }
            }
        }
    }

    @Override
    protected boolean postPlacement(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        return super.postPlacement(pos, world, player, stack, state);
    }

    private BlockState placeFromTag(BlockPos pos, World world, ItemStack stack, BlockState state) {
        var blockState = state;
        var nbtCompound = stack.getNbt();
        if (nbtCompound != null) {
            var nbtCompound1 = nbtCompound.getCompound("BlockStateTag");
            var stateManager = state.getBlock().getStateManager();

            for(String s : nbtCompound1.getKeys()) {
                var property = stateManager.getProperty(s);
                if (property != null) {
                    var s1 = Objects.requireNonNull(nbtCompound1.get(s)).asString();
                    blockState = with(blockState, property, s1);
                }
            }
        }

        if (blockState != state) {
            world.setBlockState(pos, blockState, 2);
        }

        return blockState;
    }

    private static <T extends Comparable<T>> BlockState with(BlockState state, Property<T> property, String name) {
        return property.parse(name).map((value) -> state.with(property, value)).orElse(state);
    }
}