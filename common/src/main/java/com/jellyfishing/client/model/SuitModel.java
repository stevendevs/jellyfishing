package com.jellyfishing.client.model;

import com.google.common.collect.ImmutableList;
import com.jellyfishing.core.Jellyfishing;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

/**
 * Jellyfish - Evo
 * Created using Blockbench 4.5.2
 */

@SuppressWarnings("unused")
public class SuitModel extends HumanoidModel<LivingEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Jellyfishing.id("suit"), "main");

	private final ModelPart head;
	private final ModelPart flower;
	private final ModelPart body;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	public final ModelPart hat;

	public SuitModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.flower = this.head.getChild("flower");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
		this.hat = root.getChild("hat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(5, 0)
				.addBox(-6.0F, -11.0F, -5.0F, 12.0F, 12.0F, 12.0F, CubeDeformation.NONE),
				PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition flower = head.addOrReplaceChild("flower", CubeListBuilder.create().texOffs(0, 26)
				.addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, CubeDeformation.NONE),
				PartPose.offsetAndRotation(-5.7362F, -11.2977F, -5.2504F, 0.0F, 0.3927F, 0.3927F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(13, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(37, 25).addBox(-3.2F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(37, 25).addBox(-0.6F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(31, 42).addBox(-3.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, CubeDeformation.NONE), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(31, 42).addBox(-2.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, CubeDeformation.NONE), PartPose.offset(1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head, this.flower);
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		flower.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setAllVisible(boolean visible) {
		this.head.visible = visible;
		this.hat.visible = false;
		this.body.visible = visible;
		this.rightArm.visible = visible;
		this.leftArm.visible = visible;
		this.rightLeg.visible = visible;
		this.leftLeg.visible = visible;
	}
}