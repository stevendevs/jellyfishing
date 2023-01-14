package com.jellyfishing.core.util.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class ClientUtil {
    public static final Set<Item> PERSPECTIVE_ITEM_LIST = new HashSet<>();
    public static final Set<Item> leftRightPerspectiveItemList = new HashSet<>();

    public ClientUtil() {
    }

    public static void registerMultiplePerspective(Item... items) {
        for (Item item : items) {
            PERSPECTIVE_ITEM_LIST.add(item);
            ModelLoadingRegistry.INSTANCE.registerModelProvider((resourceManager, out) -> out.accept(new ResourceLocation(BuiltInRegistries.ITEM.getKey(item) + "_in_hand")));
        }
    }

    public static void registerLeftRightPerspective(Item... items) {
        for (Item item: items) {
            leftRightPerspectiveItemList.add(item);
            ModelLoadingRegistry.INSTANCE.registerModelProvider((resourceManager, out) -> {
                out.accept(new ModelResourceLocation(new ResourceLocation(BuiltInRegistries.ITEM.getKey(item) + "_left"), "inventory"));
                out.accept(new ModelResourceLocation(new ResourceLocation(BuiltInRegistries.ITEM.getKey(item) + "_right"), "inventory"));
            });
        }
    }
}