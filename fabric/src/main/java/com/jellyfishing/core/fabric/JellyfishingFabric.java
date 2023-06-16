package com.jellyfishing.core.fabric;

import com.jellyfishing.core.Jellyfishing;
import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.jellyfishing.core.util.PointOfInterestHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.storage.LevelStorageException;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.LevelSummary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CompletionException;


public class JellyfishingFabric implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(Jellyfishing.MOD_ID);

    public static void joinLocalWorld(String worldName) {
        Minecraft client = Minecraft.getInstance();
        LevelStorageSource levelStorage = client.getLevelSource();

        try {
            List<LevelSummary> levels = levelStorage.loadLevelSummaries(levelStorage.findLevelCandidates()).join();

            for (LevelSummary level : levels) {
                if (level.getLevelId().equalsIgnoreCase(worldName)) {
                    client.createWorldOpenFlows().loadLevel(client.screen, level.getLevelId());
                    return;
                }
            }

            LOGGER.warn("couldn't find local world {}", worldName);
        } catch (CompletionException | LevelStorageException e) {
            LOGGER.error("couldn't load local world {}", worldName, e);
        }
    }

    @Override
    public void onInitialize() {
        registerVillagerHouses();

        Jellyfishing.init();


    }

//    public static final PoiType FRYCOOK_POI = PointOfInterestHelper.register(
//            Jellyfishing.id("frycook"), 1, 1, JellyfishingBlocks.GRILL.get()
//    );

    public static void registerVillagerHouses() {
        final String[] types = new String[] {"desert", "plains", "savanna", "snowy", "taiga"};
        for (String type : types) {
            DynamicRegistrySetupCallback.EVENT.register(registryManager ->
                    registryManager.registerEntryAdded(Registries.TEMPLATE_POOL, ((rawId, id, pool) -> {
                        if (id.equals(new ResourceLocation("minecraft", "village/" + type + "/houses"))) {
                            pool.templates.add(StructurePoolElement.single(Jellyfishing.MOD_ID + ":village/krusty_krab_" + type).apply(StructureTemplatePool.Projection.RIGID));
                        }
                    }))
            );
        }
    }
}