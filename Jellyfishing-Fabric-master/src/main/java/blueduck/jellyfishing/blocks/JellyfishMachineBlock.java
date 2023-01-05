package blueduck.jellyfishing.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

//TODO
public class JellyfishMachineBlock extends BlockWithEntity {
    public static final IntProperty LEVEL;

    public JellyfishMachineBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;//JellyfishingTileEntities.JELLYFISH_MACHINE.create();
    }

    static {
        LEVEL = Properties.LEVEL_3;
    }
}