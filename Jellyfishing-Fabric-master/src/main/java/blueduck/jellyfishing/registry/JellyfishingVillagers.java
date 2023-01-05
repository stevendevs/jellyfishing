package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class JellyfishingVillagers {
    public static final PointOfInterestType FRYCOOK_POI = PointOfInterestHelper.register(Jellyfishing.id("frycook"), 1, 1, JellyfishingBlocks.GRILL);

    public static final VillagerProfession FRYCOOK = VillagerProfessionBuilder.create().id(Jellyfishing.id("frycook")).workSound(SoundEvents.BLOCK_DISPENSER_FAIL).workstation(FRYCOOK_POI).build();

    public static void init() {
        Registry.register(Registry.VILLAGER_PROFESSION, Jellyfishing.id("frycook"), FRYCOOK);
    }
}