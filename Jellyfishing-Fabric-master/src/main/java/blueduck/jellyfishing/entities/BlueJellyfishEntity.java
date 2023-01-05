package blueduck.jellyfishing.entities;

import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlueJellyfishEntity extends AbstractJellyfishEntity {
    public BlueJellyfishEntity(EntityType<? extends FishEntity> type, World worldIn) {
        super(type, worldIn, new ItemStack(JellyfishingItems.BLUE_JELLYFISH, 1), JellyfishingItems.BLUE_JELLYFISH_JELLY, 1, true, 200, 6, 0.8, 0.75, 0.3);
    }
}