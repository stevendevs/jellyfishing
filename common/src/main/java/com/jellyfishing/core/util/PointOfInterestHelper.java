package com.jellyfishing.core.util;


import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

/**
 * Credit/Copied from FAPI
 * <p>
 * This class provides utilities to create a {@link PoiType}.
 *
 * <p>A point of interest is typically used by villagers to specify their workstation blocks, meeting zones and homes.
 * Points of interest are also used by bees to specify where their bee hive is and nether portals to find existing portals.
 */
public final class PointOfInterestHelper {
    private PointOfInterestHelper() {
    }

    /**
     * Creates and registers a {@link PoiType}.
     *
     * @param id The id of this {@link PoiType}.
     * @param ticketCount the amount of tickets.
     * @param searchDistance the search distance.
     * @param blocks all the blocks where a {@link PoiRecord} of this type will be present.
     * @return a new {@link PoiType}.
     */
    public static PoiType register(ResourceLocation id, int ticketCount, int searchDistance, Block... blocks) {
        final ImmutableSet.Builder<BlockState> builder = ImmutableSet.builder();

        for (Block block : blocks) {
            builder.addAll(block.getStateDefinition().getPossibleStates());
        }

        return register(id, ticketCount, searchDistance, builder.build());
    }

    /**
     * Creates and registers a {@link PoiType}.
     *
     * @param id the id of this {@link PoiType}.
     * @param ticketCount the amount of tickets.
     * @param searchDistance the search distance.
     * @param blocks all {@link BlockState block states} where a {@link PoiRecord} of this type will be present
     * @return a new {@link PoiType}.
     */
    public static PoiType register(ResourceLocation id, int ticketCount, int searchDistance, Iterable<BlockState> blocks) {
        final ImmutableSet.Builder<BlockState> builder = ImmutableSet.builder();

        return register(id, ticketCount, searchDistance, builder.addAll(blocks).build());
    }

    // INTERNAL METHODS

    private static PoiType register(ResourceLocation id, int ticketCount, int searchDistance, Set<BlockState> states) {
        return PoiTypes.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, id), states, ticketCount, searchDistance);
    }
}