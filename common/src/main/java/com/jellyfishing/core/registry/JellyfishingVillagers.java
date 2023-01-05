package com.jellyfishing.core.registry;

import com.jellyfishing.core.Jellyfishing;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;

public class JellyfishingVillagers {
    public static final DeferredRegister<VillagerProfession> VILLAGERS = DeferredRegister.create(Jellyfishing.MOD_ID, Registry.VILLAGER_PROFESSION_REGISTRY);
    public static final DeferredRegister<PoiType> POIS = DeferredRegister.create(Jellyfishing.MOD_ID, Registry.POINT_OF_INTEREST_TYPE_REGISTRY);

    public static final RegistrySupplier<PoiType> FRYCOOK_POI = POIS.register("frycook", ()-> getPOI(Jellyfishing.id("frycook"), 1, 1, JellyfishingBlocks.GRILL.get()));
    public static final RegistrySupplier<VillagerProfession> FRYCOOK = VILLAGERS.register("frycook", ()-> getVillagerProfession(Jellyfishing.id("frycook"), SoundEvents.DISPENSER_FAIL, FRYCOOK_POI.get()));

    @ExpectPlatform
    public static PoiType getPOI(ResourceLocation id, int ticketCount, int searchDistance, Block... blocks) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static VillagerProfession getVillagerProfession(ResourceLocation id, SoundEvent workSound, PoiType workstation) {
        throw new AssertionError();
    }
}