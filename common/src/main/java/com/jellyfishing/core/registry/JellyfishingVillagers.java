package com.jellyfishing.core.registry;

import com.google.common.collect.ImmutableSet;
import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class JellyfishingVillagers {
    public static final DeferredRegister<PoiType> POIS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.POINT_OF_INTEREST_TYPE);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.VILLAGER_PROFESSION);

    public static final RegistrySupplier<PoiType> FRYCOOK_POI = createPOI("frycook", 1, 10, JellyfishingBlocks.GRILL);
    public static final RegistrySupplier<VillagerProfession> FRYCOOK = createVillagerProfession("frycook", SoundEvents.DISPENSER_FAIL, FRYCOOK_POI);

    public static RegistrySupplier<PoiType> createPOI(String id, int ticketCount, int searchDistance, Supplier<Block> block) {
        return POIS.register(id, ()-> new PoiType(ImmutableSet.copyOf(block.get().getStateDefinition().getPossibleStates()), ticketCount, searchDistance));
    }

    public static RegistrySupplier<VillagerProfession> createVillagerProfession(String id, SoundEvent workSound, Supplier<PoiType> workstation) {
        return VILLAGER_PROFESSIONS.register(id, ()-> new VillagerProfession(Jellyfishing.id(id).toString(), holder -> holder.value().equals(workstation.get()), holder -> holder.value().equals(workstation.get()), ImmutableSet.of(), ImmutableSet.of(), workSound));
    }
}