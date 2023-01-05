package blueduck.jellyfishing.entities;

import blueduck.jellyfishing.registry.JellyfishingItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class JellyfishEntity extends AbstractJellyfishEntity {
    public JellyfishEntity(EntityType<? extends FishEntity> type, World worldIn) {
        super(type, worldIn, new ItemStack(JellyfishingItems.JELLYFISH, 1), JellyfishingItems.JELLYFISH_JELLY, 1, true, 500, 3, 0.1, 0.2, 0.1);
    }
}