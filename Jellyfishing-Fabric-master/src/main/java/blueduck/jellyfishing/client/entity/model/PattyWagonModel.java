package blueduck.jellyfishing.client.entity.model;

//import blueduck.jellyfishing.entities.PattyWagonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

/**
 * PattyWagonModel - Evo
 * Created using Blockbench 3.6.6
 * For Minecraft 1.15
 */

//TODO
//@Environment(EnvType.CLIENT)
//public class PattyWagonModel extends EntityModel<PattyWagonEntity> {
//    private static ModelPart front;
//    private static ModelPart back;
//    private static ModelPart right;
//    private static ModelPart left;
//    private static ModelPart base;
//    private static ModelPart plate;
//    private static ModelPart wheel1;
//    private static ModelPart wheel2;
//    private static ModelPart wheel3;
//    private static ModelPart wheel4;
//
//    public PattyWagonModel(ModelPart root) {
//        front = root.getChild("front");
//        back = root.getChild("back");
//        right = root.getChild("right");
//        left = root.getChild("left");
//        base = root.getChild("base");
//        plate = base.getChild("plate");
//        wheel1 = root.getChild("wheel1");
//        wheel2 = root.getChild("wheel2");
//        wheel3 = root.getChild("wheel3");
//        wheel4 = root.getChild("wheel4");
//    }
//
//    public static TexturedModelData getTexturedModelData() {
//        ModelData modelData = new ModelData();
//        ModelPartData modelPartData = modelData.getRoot();
//        setRotationAngle(front, 0.0F, 3.1416F, 0.0F);
//        setRotationAngle(right, 0.0F, -1.5708F, 0.0F);
//        setRotationAngle(left, 0.0F, 1.5708F, 0.0F);
//        setRotationAngle(base, 0.0F, -1.5708F, 1.5708F);
//        setRotationAngle(plate, 0.0F, 0.1745F, 0.0F);
//        modelPartData.addChild("front", ModelPartBuilder.create().uv(0,47).cuboid(-8.0F, -10.0F, 8.0F, 16.0F, 8.0F, 2.0F).uv(16,18).cuboid(-7.0F, -14.0F, 4.0F, 14.0F, 4.0F, 5.0F).uv(8,40).cuboid(4.0F, -9.0F, 10.0F, 3.0F, 3.0F, 1.0F).uv(0,40).cuboid(-7.0F, -9.0F, 10.0F, 3.0F, 3.0F, 1.0F).uv(35,63).cuboid(-2.5F, -11.0F, 6.9F, 5.0F, 5.0F, 1.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        modelPartData.addChild("back", ModelPartBuilder.create().uv(44,0).cuboid(-8.0F, -10.0F, 8.0F, 16.0F, 8.0F, 2.0F).uv(0,50).cuboid(7.0F, -24.0F, 7.0F, 0.0F, 15.0F, 7.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        modelPartData.addChild("right", ModelPartBuilder.create().uv(26,37).cuboid(-8.0F, -10.0F, 6.0F, 16.0F, 8.0F, 2.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        modelPartData.addChild("left", ModelPartBuilder.create().uv(26,27).cuboid(-8.0F, -10.0F, 6.0F, 16.0F, 8.0F, 2.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        ModelPartData modelPartData1 = modelPartData.addChild("base", ModelPartBuilder.create().uv(0,0).cuboid(-10.0F, -8.0F, 0.0F, 20.0F, 16.0F, 2.0F).uv(36,47).cuboid(-2.0F, -6.0F, 2.0F, 10.0F, 12.0F, 2.0F).uv(0,18).cuboid(5.0F, -6.0F, 4.0F, 3.0F, 12.0F, 10.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        modelPartData1.addChild("plate", ModelPartBuilder.create().uv(0,14).cuboid(0.0F, -3.5F, -2.0F, 0.0F, 7.0F, 4.0F), ModelTransform.pivot(-10.4F,0.0F,1.0F));
//        modelPartData.addChild("wheel1", ModelPartBuilder.create().uv(58,15).cuboid(8.0F, -4.0F, -8.0F, 2.0F, 5.0F, 5.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        modelPartData.addChild("wheel2", ModelPartBuilder.create().uv(14,57).cuboid(8.0F, -4.0F, 4.0F, 2.0F, 5.0F, 5.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        modelPartData.addChild("wheel3", ModelPartBuilder.create().uv(55,56).cuboid(-10.0F, -4.0F, -8.0F, 2.0F, 5.0F, 5.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        modelPartData.addChild("wheel4", ModelPartBuilder.create().uv(49,10).cuboid(-10.0F, -4.0F, 4.0F, 2.0F, 5.0F, 5.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
//        return TexturedModelData.of(modelData,128,128);
//    }
//
//    @Override
//    public void setAngles(PattyWagonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){}
//
//    @Override
//    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//        front.render(matrixStack, buffer, packedLight, packedOverlay);
//        back.render(matrixStack, buffer, packedLight, packedOverlay);
//        right.render(matrixStack, buffer, packedLight, packedOverlay);
//        left.render(matrixStack, buffer, packedLight, packedOverlay);
//        base.render(matrixStack, buffer, packedLight, packedOverlay);
//        wheel1.render(matrixStack, buffer, packedLight, packedOverlay);
//        wheel2.render(matrixStack, buffer, packedLight, packedOverlay);
//        wheel3.render(matrixStack, buffer, packedLight, packedOverlay);
//        wheel4.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
//
//    public static void setRotationAngle(ModelPart bone, float x, float y, float z) {
//        bone.pitch = x;
//        bone.yaw = y;
//        bone.roll = z;
//    }
//}