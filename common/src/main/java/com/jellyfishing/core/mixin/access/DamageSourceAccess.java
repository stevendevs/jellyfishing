package com.jellyfishing.core.mixin.access;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(DamageSource.class)
public interface DamageSourceAccess {
    @Invoker("bypassArmor")
    DamageSource invokeBypassArmor();
}