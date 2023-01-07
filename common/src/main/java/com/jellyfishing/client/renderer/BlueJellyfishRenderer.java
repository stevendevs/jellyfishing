package com.jellyfishing.client.renderer;

import com.jellyfishing.client.model.AbstractJellyfishModel;
import com.jellyfishing.common.entities.BlueJellyfishEntity;
import com.jellyfishing.core.Jellyfishing;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlueJellyfishRenderer extends MobRenderer<BlueJellyfishEntity, AbstractJellyfishModel<BlueJellyfishEntity>> {
    public BlueJellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new AbstractJellyfishModel<>(context.bakeLayer(AbstractJellyfishModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(BlueJellyfishEntity entity) {
        return Jellyfishing.id("textures/entity/blue_jellyfish.png");
    }
}