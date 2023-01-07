package com.jellyfishing.core.registry;

import com.jellyfishing.common.entities.BlueJellyfishEntity;
import com.jellyfishing.common.entities.JellyfishEntity;
import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class JellyfishingEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<JellyfishEntity>> JELLYFISH = ENTITIES.register("jellyfish", () -> EntityType.Builder.of(JellyfishEntity::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.4F).build(Jellyfishing.id("textures/entities/blue_jellyfish.png").toString()));
    public static final RegistrySupplier<EntityType<BlueJellyfishEntity>> BLUE_JELLYFISH = ENTITIES.register("blue_jellyfish", () -> EntityType.Builder.of(BlueJellyfishEntity::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.4F).build(Jellyfishing.id("textures/entities/blue_jellyfish.png").toString()));

//    public static final RegistryObject<EntityType<PattyWagonEntity>> PATTY_WAGON = ENTITIES.register("patty_wagon", () -> EntityType.Builder.<PattyWagonEntity>create(PattyWagonEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build(new ResourceLocation("jellyfishing", "textures/entities/patty_wagon").toString()));
}