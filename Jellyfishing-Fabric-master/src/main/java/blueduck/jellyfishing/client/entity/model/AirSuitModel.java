package blueduck.jellyfishing.client.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

/**
 * Created using Blockbench 3.7.4
 * For Minecraft 1.15
 */

//TODO
@Environment(EnvType.CLIENT)
public class AirSuitModel extends BipedEntityModel<LivingEntity> {
	public AirSuitModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = getModelData(Dilation.NONE, 0);
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(5, 0).cuboid(-6.0F, -11.0F, -5.0F, 12.0F, 12.0F, 12.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, -1.0F));
		head.addChild("flower", ModelPartBuilder.create().uv(0, 26).cuboid(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)),
				ModelTransform.of(-5.7362F, -11.2977F, -5.2504F, 0.0F, 0.3927F, 0.3927F));

		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(13, 25).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.2F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(37, 25).cuboid(-3.2F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.4F)),
				ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(37, 25).cuboid(-0.6F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.4F)),
				ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 38).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.1F))
				.uv(31, 42).cuboid(-3.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, new Dilation(0.0F)),
					ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 38).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.1F))
				.uv(31, 42).cuboid(-2.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, new Dilation(0.0F)),
					ModelTransform.pivot(1.9F, 12.0F, 0.0F));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setVisible(boolean visible) {
		this.head.visible = visible;
		this.hat.visible = false;
		this.body.visible = visible;
		this.rightArm.visible = visible;
		this.leftArm.visible = visible;
		this.rightLeg.visible = visible;
		this.leftLeg.visible = visible;
	}
}
//public class AirSuitModel extends BipedEntityModel<LivingEntity> {
//	private final ModelPart body;
//	private final ModelPart rightArm;
//	private final ModelPart leftArm;
//	private final ModelPart rightLeg;
//	private final ModelPart leftLeg;
//	private final ModelPart head;
//
//	public AirSuitModel(ModelPart root) {
//		super(root);
//		this.body = root.getChild("body");
//		this.rightArm = root.getChild("rightArm");
//		this.leftArm = root.getChild("leftArm");
//		this.rightLeg = root.getChild("rightLeg");
//		this.leftLeg = root.getChild("leftLeg");
//		this.head = root.getChild("head");
//	}
//
//	public static TexturedModelData getTexturedModelData() {
//		ModelData modelData = getModelData(Dilation.NONE, 0);
//		ModelPartData modelPartData = modelData.getRoot();
//
//		modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 24).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.2F)),
//				ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//		modelPartData.addChild("rightArm", ModelPartBuilder.create().uv(36, 36).cuboid(-3.2F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.4F)),
//				ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
//		modelPartData.addChild("leftArm", ModelPartBuilder.create().uv(24, 24).cuboid(-0.6F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.4F)),
//				ModelTransform.pivot(5.0F, 2.0F, 0.0F));
//		modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(18, 45).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.1F))
//				.uv(36, 0).cuboid(-3.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, new Dilation(0.0F)),
//					ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
//		modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(44, 20).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.1F))
//				.uv(0, 40).cuboid(-2.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, new Dilation(0.0F)),
//					ModelTransform.pivot(1.9F, 12.0F, 0.0F));
//
//		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -11.0F, -5.0F, 12.0F, 12.0F, 12.0F, new Dilation(0.0F)),
//				ModelTransform.pivot(0.0F, 0.0F, -1.0F));
//		head.addChild("flower", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)),
//				ModelTransform.of(-5.7362F, -11.2977F, -5.2504F, 0.0F, 0.3927F, 0.3927F));
//
//		return TexturedModelData.of(modelData, 64, 64);
//	}
//
//	@Override
//	public void setVisible(boolean visible) {
//		this.head.visible = visible;
//		this.hat.visible = false;
//		this.body.visible = visible;
//		this.rightArm.visible = visible;
//		this.leftArm.visible = visible;
//		this.rightLeg.visible = visible;
//		this.leftLeg.visible = visible;
//	}
//}