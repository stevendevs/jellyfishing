package blueduck.jellyfishing.mixin.access;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unused"})
@Mixin(VillagerEntity.class)
public interface VillagerAccess {
    @Mutable
    @Accessor("GATHERABLE_ITEMS")
    static void setGatherableItems(Set<Item> items) {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("ITEM_FOOD_VALUES")
    static void setItemFoodValues(Map<Item, Integer> food) {
        throw new AssertionError();
    }
}