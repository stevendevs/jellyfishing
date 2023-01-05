package com.jellyfishing.core.registry.forge;

import com.google.common.collect.ImmutableSet;
import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class JellyfishingVillagersImpl {
    public static PoiType getPOI(ResourceLocation id, int ticketCount, int searchDistance, Block... blocks) {
        return new PoiType(getBlockStates(JellyfishingBlocks.GRILL.get()), 1, 1);
    }

    private static Set<BlockState> getBlockStates(Block arg) {
        return ImmutableSet.copyOf(arg.getStateDefinition().getPossibleStates());
    }

    public static VillagerProfession getVillagerProfession(ResourceLocation id, SoundEvent workSound, PoiType workstation) {
        return new VillagerProfession(Jellyfishing.id("frycook").toString(), entry -> entry.is(Registry.POINT_OF_INTEREST_TYPE.getResourceKey(workstation).get()), entry -> entry.is(Registry.POINT_OF_INTEREST_TYPE.getResourceKey(workstation).get()), ImmutableSet.of(), ImmutableSet.of(JellyfishingBlocks.GRILL.get()), workSound);
    }
}