//package com.jellyfishing.common.block;
//
//import net.minecraft.tags.BlockTags;
//
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.SlimeBlock;
//import net.minecraft.world.level.block.state.BlockState;
//
//public class BlueJellyBlock extends SlimeBlock {
//    public BlueJellyBlock(Block.Properties properties) {
//        super(properties);
//    }
//
//    @Override
//    public void onLanded(IBlockReader worldIn, Entity entityIn) {
//        if (entityIn.isSuppressingBounce()) {
//            super.onLanded(worldIn, entityIn);
//        } else {
//            this.bounceUp(entityIn);
//        }
//
//    }
//
//    private void bounceUp(Entity p_226946_1_) {
//        Vector3d vec3d = p_226946_1_.getMotion();
//        if (vec3d.y < 0.0D) {
//            double d0 = p_226946_1_ instanceof LivingEntity ? 1.0D : 0.8D;
//            p_226946_1_.setMotion(vec3d.x, -vec3d.y * d0 * 1.2, vec3d.z);
//        }
//
//    }
//
//    @Override
//    public boolean isStickyBlock(BlockState state)
//    {
//        return true;
//    }
//
//
//    @Override
//    public boolean canStickTo(BlockState state, BlockState other)
//    {
//        if (BlockTags.makeWrapperTag("jellyfishing:jelly_blocks").contains(other.getBlock()) && state != other) {
//            return false;
//        }
//        return true;
//    }
//
//}
