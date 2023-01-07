package com.jellyfishing.core.mixin;

import com.jellyfishing.core.registry.JellyfishingExtras;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.SpawnData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {
    @Shadow private SpawnData nextSpawnData;

    @Redirect(
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;finalizeSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/world/entity/SpawnGroupData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/entity/SpawnGroupData;"
//                    ordinal = 0
            )
    )
    private SpawnGroupData jellyfishing$entitySpawnEvent(Mob instance, ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, SpawnGroupData spawnData, CompoundTag dataTag) {
        if (this.nextSpawnData.getEntityToSpawn().size() == 1 && this.nextSpawnData.getEntityToSpawn().contains("id", 8)) {
            JellyfishingExtras.doSpecialSpawn(instance, level);
        }
        return Objects.requireNonNull(instance.finalizeSpawn(level, level.getCurrentDifficultyAt(instance.blockPosition()), MobSpawnType.SPAWNER, null, null));
    }
}