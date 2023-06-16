package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.util.VillagerProfessionBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;

public class JellyfishingVillagers {
    public static final VillagerProfession FRYCOOK = Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Jellyfishing.id("frycook"),
            VillagerProfessionBuilder.create()
                    .id(Jellyfishing.id("frycook"))
                    .workstation(ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Jellyfishing.id("frycook")))
                    .workSound(SoundEvents.VILLAGER_WORK_TOOLSMITH)
                    .build()
    );
}