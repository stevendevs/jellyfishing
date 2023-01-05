package blueduck.jellyfishing.mixin;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.mixin.access.StructurePoolAccess;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructurePools.class)
public class StructurePoolsMixin {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void inject(StructurePool templatePool, CallbackInfoReturnable<StructurePool> cir) {
        addToPool(new Identifier("village/plains/houses"), templatePool, Jellyfishing.id("village/krusty_krab_plains"), StructurePool.Projection.RIGID, 10);
        addToPool(new Identifier("village/desert/houses"), templatePool, Jellyfishing.id("village/krusty_krab_snowy"), StructurePool.Projection.RIGID, 16);
        addToPool(new Identifier("village/savanna/houses"), templatePool, Jellyfishing.id("village/krusty_krab_taiga"), StructurePool.Projection.RIGID, 16);
        addToPool(new Identifier("village/taiga/houses"), templatePool, Jellyfishing.id("village/krusty_krab_desert"), StructurePool.Projection.RIGID, 16);
        addToPool(new Identifier("village/snowy/houses"), templatePool, Jellyfishing.id("village/krusty_krab_savanna"), StructurePool.Projection.RIGID, 10);

        if (BuiltinRegistries.STRUCTURE_POOL.getId(templatePool) == null) {
            cir.setReturnValue(BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_POOL, templatePool.getId(), templatePool));
        }
    }

    private static void addToPool(Identifier targetPool, StructurePool pool, Identifier elementId, StructurePool.Projection projection, int weight) {
        if (targetPool.equals(pool.getId())) {
            var element = StructurePoolElement.ofProcessedLegacySingle(elementId.toString(), StructureProcessorLists.EMPTY).apply(projection);
            for (int i = 0; i < weight; i++) {
                ((StructurePoolAccess)pool).getElements().add(element);
            }
            ((StructurePoolAccess)pool).getElementCounts().add(Pair.of(element, weight));
        }
    }
}