package blueduck.jellyfishing.mixin.access;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings({"unused"})
@Mixin(EnchantmentTarget.class)
public abstract class EnchantmentTargetAccess {
    @Shadow public abstract boolean isAcceptableItem(Item item);
}