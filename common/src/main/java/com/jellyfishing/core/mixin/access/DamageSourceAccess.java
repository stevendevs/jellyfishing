package com.jellyfishing.core.mixin.access;

import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DamageSource.class)
public interface DamageSourceAccess {
    @Invoker("bypassArmor")
    DamageSource invokeBypassArmor();
}