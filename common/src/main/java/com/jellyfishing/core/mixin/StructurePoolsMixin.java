package com.jellyfishing.core.mixin;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.mixin.access.StructurePoolAccess;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pools.class)
public class StructurePoolsMixin {
//    @Inject(at = @At("HEAD"), method = "register")
//    private static void register(BootstapContext<StructureTemplatePool> bootstapContext, String string, StructureTemplatePool pool, CallbackInfo ci) {
//        if (((StructurePoolAccess) pool).getRawTemplates().contains(new ResourceLocation("village/plains/houses"))) {
//            addToPool(pool, Jellyfishing.id("village/krusty_krab_plains").toString(), 10);
//        }
//        else if (pool.getFallback().is(new ResourceLocation("village/desert/houses"))) {
//            addToPool(pool, Jellyfishing.id("village/krusty_krab_desert").toString(), 10);
//        }
//        else if (pool.getFallback().is(new ResourceLocation("village/savanna/houses"))) {
//            addToPool(pool, Jellyfishing.id("village/krusty_krab_savanna").toString(), 10);
//        }
//        else if (pool.getFallback().is(new ResourceLocation("village/taiga/houses"))) {
//            addToPool(pool, Jellyfishing.id("village/krusty_krab_taiga").toString(), 10);
//        }
//        else if (pool.getFallback().is(new ResourceLocation("village/snowy/houses"))) {
//            addToPool(pool, Jellyfishing.id("village/krusty_krab_snowy").toString(), 10);
//        }
//    }
//
//    @Unique
//    private static void addToPool(StructureTemplatePool pool, String name, int weight) {
//        StructurePoolElement element = StructurePoolElement.legacy(name)
//                .apply(StructureTemplatePool.Projection.RIGID);
//        for (int i = 0; i < weight; i++) {
//            ((StructurePoolAccess) pool).getTemplates().add(element);
//        }
//        ((StructurePoolAccess) pool).getRawTemplates().add(Pair.of(element, weight));
//    }
}