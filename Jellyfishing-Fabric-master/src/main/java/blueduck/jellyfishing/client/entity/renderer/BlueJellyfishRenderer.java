package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.client.JellyfishingClient;
import blueduck.jellyfishing.client.entity.model.BlueJellyfishModel;
import blueduck.jellyfishing.entities.BlueJellyfishEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BlueJellyfishRenderer extends MobEntityRenderer<BlueJellyfishEntity, BlueJellyfishModel> {
    public BlueJellyfishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BlueJellyfishModel(ctx.getPart(JellyfishingClient.BLUE_JELLYFISH_LAYER)), 0.3F);
    }

    @Override
    public Identifier getTexture(BlueJellyfishEntity entity) {
        return Jellyfishing.id("textures/entity/blue_jellyfish.png");
    }
}