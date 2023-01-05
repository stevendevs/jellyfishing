package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.client.JellyfishingClient;
import blueduck.jellyfishing.client.entity.model.AbstractJellyfishModel;
import blueduck.jellyfishing.entities.AbstractJellyfishEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class AbstractJellyfishRenderer extends MobEntityRenderer<AbstractJellyfishEntity, AbstractJellyfishModel> {
    public Identifier jellyfishTexture;

    public AbstractJellyfishRenderer(EntityRendererFactory.Context ctx, Identifier texture) {
        super(ctx, new AbstractJellyfishModel(ctx.getPart(JellyfishingClient.ABSTRACT_JELLYFISH_LAYER)), 0.3F);
        this.jellyfishTexture = texture;
    }

    @Override
    public Identifier getTexture(AbstractJellyfishEntity entity) {
        return jellyfishTexture;
    }
}