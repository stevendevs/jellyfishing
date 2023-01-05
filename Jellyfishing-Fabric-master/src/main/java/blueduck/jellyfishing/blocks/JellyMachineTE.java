package blueduck.jellyfishing.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.util.math.BlockPos;

//TODO
public class JellyMachineTE extends BlockEntity implements ClientPlayerTickable {
    public JellyMachineTE(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    public JellyMachineTE(BlockPos pos, BlockState state) {
        this(null, pos, state);//JellyfishingTileEntities.JELLYFISH_MACHINE, pos, state);
    }

    @Override
    public void tick() {}
}