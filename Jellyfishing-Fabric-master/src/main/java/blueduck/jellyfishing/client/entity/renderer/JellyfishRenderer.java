package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.client.JellyfishingClient;
import blueduck.jellyfishing.client.entity.model.JellyfishModel;
import blueduck.jellyfishing.entities.JellyfishEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class JellyfishRenderer extends MobEntityRenderer<JellyfishEntity, JellyfishModel> {
    public static EntityRendererFactory.Context ctx;

    public JellyfishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new JellyfishModel(ctx.getPart(JellyfishingClient.JELLYFISH_LAYER)), 0.3F);
        JellyfishRenderer.ctx = ctx;
    }

    @Override
    public Identifier getTexture(JellyfishEntity entity) {
        return Jellyfishing.id("textures/entity/jellyfish.png");
    }
}