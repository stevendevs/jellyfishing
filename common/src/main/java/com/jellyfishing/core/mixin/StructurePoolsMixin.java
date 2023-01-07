package com.jellyfishing.core.mixin;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.mixin.access.StructurePoolAccess;
import com.mojang.datafixers.util.Pair;
//import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Pools.class)
public class StructurePoolsMixin {
//    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
//    private static void inject(BootstapContext<StructureTemplatePool> bootstapContext, String string, StructureTemplatePool templatePool, CallbackInfo ci) {
//        addToPool(new ResourceLocation("village/plains/houses"), templatePool, Jellyfishing.id("village/krusty_krab_plains"), StructureTemplatePool.Projection.RIGID, 10);
//        addToPool(new ResourceLocation("village/desert/houses"), templatePool, Jellyfishing.id("village/krusty_krab_snowy"), StructureTemplatePool.Projection.RIGID, 16);
//        addToPool(new ResourceLocation("village/savanna/houses"), templatePool, Jellyfishing.id("village/krusty_krab_taiga"), StructureTemplatePool.Projection.RIGID, 16);
//        addToPool(new ResourceLocation("village/taiga/houses"), templatePool, Jellyfishing.id("village/krusty_krab_desert"), StructureTemplatePool.Projection.RIGID, 16);
//        addToPool(new ResourceLocation("village/snowy/houses"), templatePool, Jellyfishing.id("village/krusty_krab_savanna"), StructureTemplatePool.Projection.RIGID, 10);
//
//        if (BuiltinRegistries.TEMPLATE_POOL.getKey(templatePool) != null) {
//            cir.setReturnValue(BuiltinRegistries.register(BuiltinRegistries.TEMPLATE_POOL, templatePool.getName(), templatePool).value());
//        }
//    }
//
//    private static void addToPool(ResourceLocation targetPool, StructureTemplatePool pool, ResourceLocation elementId, StructureTemplatePool.Projection projection, int weight) {
//        if (targetPool.equals(pool.getName())) {
//            var element = StructurePoolElement.legacy(elementId.toString(), ProcessorLists.EMPTY).apply(projection);
//            for (int i = 0; i < weight; i++) {
//                ((StructurePoolAccess)pool).getTemplates().add(element);
//            }
//            ((StructurePoolAccess)pool).getRawTemplates().add(Pair.of(element, weight));
//        }
//    }
}