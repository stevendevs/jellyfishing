package blueduck.jellyfishing.client.entity.model;

import blueduck.jellyfishing.entities.AbstractJellyfishEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

/**
 * Jellyfish - Coda1552
 * Created using Tabula 8.0.0
 */

@Environment(EnvType.CLIENT)
@SuppressWarnings({"unused"})
public class AbstractJellyfishModel extends EntityModel<AbstractJellyfishEntity> {
    private final ModelPart body;
    private final ModelPart tentacle3;
    private final ModelPart tentacle2;
    private final ModelPart tentacle4;
    private final ModelPart tentacle1;

    public AbstractJellyfishModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tentacle1 = this.body.getChild("tentacle1");
        this.tentacle2 = this.body.getChild("tentacle2");
        this.tentacle3 = this.body.getChild("tentacle3");
        this.tentacle4 = this.body.getChild("tentacle4");
    }

    public static TexturedModelData getTexturedModelData() {
        var modelData = new ModelData();
        var modelPartData = modelData.getRoot();
        modelPartData.addChild("body", ModelPartBuilder.create().cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, false), ModelTransform.pivot(1.5F,3.0F,1.5F));
        var modelPartData1 = modelPartData.addChild("body", ModelPartBuilder.create().cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, false), ModelTransform.pivot(0.0F,21.0F,0.0F));
        modelPartData1.addChild("tentacle1", ModelPartBuilder.create().cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, false), ModelTransform.pivot(1.5F,3.0F,1.5F));
        modelPartData1.addChild("tentacle2", ModelPartBuilder.create().cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, false), ModelTransform.pivot(-1.5F,3.0F,1.5F));
        modelPartData1.addChild("tentacle3", ModelPartBuilder.create().cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, false), ModelTransform.pivot(-1.5F,3.0F,-1.5F));
        modelPartData1.addChild("tentacle4", ModelPartBuilder.create().cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, false), ModelTransform.pivot(1.5F,3.0F,-1.5F));
        return TexturedModelData.of(modelData,24,12);
    }

    @Override
    public void setAngles(AbstractJellyfishEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isTouchingWater()) {
            body.pitch = (float) (30 + MathHelper.sin(limbAngle) * 0.5);
        }
        else {
            body.pitch = 0;
        }
        float rotateValue = animationProgress * 0.1F;
        tentacle1.pitch = Math.abs(MathHelper.sin(rotateValue)) * 0.5F;
        tentacle1.roll = Math.abs(MathHelper.sin(rotateValue)) * -0.5F;
        tentacle2.pitch = Math.abs(MathHelper.sin(rotateValue)) * 0.5F;
        tentacle2.roll = Math.abs(MathHelper.sin(rotateValue)) * 0.5F;
        tentacle3.pitch = Math.abs(MathHelper.sin(rotateValue)) * -0.5F;
        tentacle3.roll = Math.abs(MathHelper.sin(rotateValue)) * 0.5F;
        tentacle4.pitch = Math.abs(MathHelper.sin(rotateValue)) * -0.5F;
        tentacle4.roll = Math.abs(MathHelper.sin(rotateValue)) * -0.5F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha));
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}