package com.jellyfishing.client.renderer;

import com.jellyfishing.client.model.AbstractJellyfishModel;
import com.jellyfishing.common.entities.JellyfishEntity;
import com.jellyfishing.core.Jellyfishing;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JellyfishRenderer extends MobRenderer<JellyfishEntity, AbstractJellyfishModel<JellyfishEntity>> {
    public JellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new AbstractJellyfishModel<>(context.bakeLayer(AbstractJellyfishModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(JellyfishEntity entity) {
        return Jellyfishing.id("textures/entity/jellyfish.png");
    }
}