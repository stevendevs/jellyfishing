package blueduck.jellyfishing.misc;

import blueduck.jellyfishing.items.JellyfishNetItem;
import blueduck.jellyfishing.mixin.access.EnchantmentTargetAccess;
import net.minecraft.item.Item;

@SuppressWarnings({"unused"})
public class JellyNetTarget extends EnchantmentTargetAccess {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof JellyfishNetItem;
    }
}