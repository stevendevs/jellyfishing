package com.jellyfishing.core.mixin.access;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructureTemplatePool.class)
public interface StructurePoolAccess {
    @Accessor(value = "templates")
    ObjectArrayList<StructurePoolElement> getTemplates();

    @Accessor(value = "rawTemplates")
    List<Pair<StructurePoolElement, Integer>> getRawTemplates();
}