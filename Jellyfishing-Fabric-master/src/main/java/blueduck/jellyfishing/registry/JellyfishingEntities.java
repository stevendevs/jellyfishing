package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.entities.BlueJellyfishEntity;
import blueduck.jellyfishing.entities.JellyfishEntity;
//import blueduck.jellyfishing.entities.PattyWagonEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class JellyfishingEntities {
    private static final Map<Identifier, EntityType> ENTITY = new LinkedHashMap<>();

    public static final EntityType<JellyfishEntity> JELLYFISH = add("jellyfish", EntityType.Builder.create(JellyfishEntity::new, SpawnGroup.WATER_AMBIENT).setDimensions(0.5F, 0.4F).build(Jellyfishing.id("textures/entities/jellyfish.png").toString()));
    public static final EntityType<BlueJellyfishEntity> BLUE_JELLYFISH = add("blue_jellyfish", EntityType.Builder.create(BlueJellyfishEntity::new, SpawnGroup.WATER_AMBIENT).setDimensions(0.5F, 0.4F).build(Jellyfishing.id("textures/entities/blue_jellyfish.png").toString()));

    //public static final EntityType<PattyWagonEntity> PATTY_WAGON = add("patty_wagon", EntityType.Builder.<PattyWagonEntity>create(PattyWagonEntity::new, SpawnGroup.MISC).setDimensions(1.375F, 0.5625F).build(new Identifier(Jellyfishing.MODID, "textures/entities/patty_wagon").toString()));

    private static <E extends EntityType> E add(String name, E entity) {
        ENTITY.put(Jellyfishing.id(name), entity);
        return entity;
    }

    public static void init() {
        for (Identifier id : ENTITY.keySet()) {
            Registry.register(Registry.ENTITY_TYPE, id, ENTITY.get(id));
        }
    }
}