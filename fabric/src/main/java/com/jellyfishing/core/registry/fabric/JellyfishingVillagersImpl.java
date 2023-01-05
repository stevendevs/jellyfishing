package com.jellyfishing.core.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;

public class JellyfishingVillagersImpl {
    public static PoiType getPOI(ResourceLocation id, int ticketCount, int searchDistance, Block... blocks) {
        return PointOfInterestHelper.register(id, ticketCount, searchDistance, blocks);
    }

    public static VillagerProfession getVillagerProfession(ResourceLocation id, SoundEvent workSound, PoiType workstation) {
        return VillagerProfessionBuilder.create().id(id).workSound(workSound).workstation(Registry.POINT_OF_INTEREST_TYPE.getResourceKey(workstation).get()).build();
    }
}