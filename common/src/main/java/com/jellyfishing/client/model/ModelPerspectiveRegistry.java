package com.jellyfishing.client.model;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModelPerspectiveRegistry {
    public static final Map<Item, ModelResourceLocation> HAND_PERSPECTIVE_LIST = new ConcurrentHashMap<>();
    public static final Map<Item, ModelResourceLocation> LEFT_PERSPECTIVE_LIST = new ConcurrentHashMap<>();
    public static final Map<Item, ModelResourceLocation> RIGHT_PERSPECTIVE_LIST = new ConcurrentHashMap<>();
    public static final Map<Item, ModelResourceLocation> GUI_PERSPECTIVE_LIST = new ConcurrentHashMap<>();

    @ExpectPlatform
    public static void registerModelProvider(ResourceLocation id) {
        throw new AssertionError();
    }

    public static void registerMultiplePerspective(ItemLike item, ModelResourceLocation handId, ModelResourceLocation guiId) {
        HAND_PERSPECTIVE_LIST.put(item.asItem(), handId);
        GUI_PERSPECTIVE_LIST.put(item.asItem(), guiId);
    }

    public static void registerLeftRightPerspective(ItemLike item, ModelResourceLocation leftId, ModelResourceLocation rightId, ModelResourceLocation guiId) {
        LEFT_PERSPECTIVE_LIST.put(item.asItem(), leftId);
        RIGHT_PERSPECTIVE_LIST.put(item.asItem(), rightId);
        GUI_PERSPECTIVE_LIST.put(item.asItem(), guiId);
    }

    public static ModelResourceLocation getHandModel(ItemLike like) {
        return HAND_PERSPECTIVE_LIST.get(like.asItem());
    }

    public static ModelResourceLocation getLeftModel(ItemLike like) {
        return LEFT_PERSPECTIVE_LIST.get(like.asItem());
    }
    public static ModelResourceLocation getRightModel(ItemLike like) {
        return RIGHT_PERSPECTIVE_LIST.get(like.asItem());
    }

    public static ModelResourceLocation getGuiModel(ItemLike like) {
        return GUI_PERSPECTIVE_LIST.get(like.asItem());
    }
}