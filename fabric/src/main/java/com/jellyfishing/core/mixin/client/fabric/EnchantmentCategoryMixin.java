package com.jellyfishing.core.mixin.client.fabric;

import com.jellyfishing.common.item.JellyfishNetItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net.minecraft.world.item.enchantment.EnchantmentCategory$9",//ARMOR
})
public class EnchantmentCategoryMixin {
    @Inject(method = "canEnchant(Lnet/minecraft/world/item/Item;)Z", at = @At("RETURN"), cancellable = true)
    private void canEnchant(Item itemIn, CallbackInfoReturnable<Boolean> cir){
        if (itemIn instanceof JellyfishNetItem) {
            cir.setReturnValue(true);
        }
    }
}