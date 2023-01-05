package blueduck.jellyfishing.items;

import net.minecraft.item.Item;

public class JellyfishNetItem extends Item {
    public JellyfishNetItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getEnchantability() {
        return 30;
    }
}