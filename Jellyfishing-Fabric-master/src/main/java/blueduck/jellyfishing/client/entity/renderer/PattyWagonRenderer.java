package blueduck.jellyfishing.client.entity.renderer;

import blueduck.jellyfishing.Jellyfishing;
//import blueduck.jellyfishing.entities.PattyWagonEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

//TODO
//public class PattyWagonRenderer<T extends PattyWagonEntity> extends EntityRenderer<T> {
//    public PattyWagonRenderer(EntityRendererFactory.Context ctx) {
//        super(ctx);
//    }
//
//    public void render(T entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
//        this.renderBlock(entity.getEntityWorld().getBlockState(BlockPos.ORIGIN), matrixStack, vertexConsumerProvider, i);
//    }
//
//    @Override
//    public Identifier getTexture(PattyWagonEntity entity) {
//        return Jellyfishing.id("textures/entity/patty_wagon");
//    }
//
//    public void renderBlock(BlockState stateIn, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
//        MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(stateIn, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.DEFAULT_UV);
//    }
//}
