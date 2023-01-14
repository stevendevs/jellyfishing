package com.jellyfishing.client.model.fabric;

import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.resources.ResourceLocation;

public class ModelPerspectiveRegistryImpl {
    public static void registerModelProvider(ResourceLocation id) {
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(id));
    }
}