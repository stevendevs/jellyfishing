package com.jellyfishing.client.model;

import com.google.common.collect.ImmutableList;
import com.jellyfishing.common.entities.AbstractJellyfishEntity;
import com.jellyfishing.core.Jellyfishing;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * Jellyfish - Coda1552
 * Created using Tabula 8.0.0
 */

public class AbstractJellyfishModel<T extends AbstractJellyfishEntity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Jellyfishing.id("jellyfish"), "main");

	private final ModelPart body;
	private final ModelPart tentacle1;
	private final ModelPart tentacle2;
	private final ModelPart tentacle3;
	private final ModelPart tentacle4;

	public AbstractJellyfishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tentacle1 = this.body.getChild("tentacle1");
		this.tentacle2 = this.body.getChild("tentacle2");
		this.tentacle3 = this.body.getChild("tentacle3");
		this.tentacle4 = this.body.getChild("tentacle4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));
		PartDefinition tentacle1 = body.addOrReplaceChild("tentacle1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, 1.5F));
		PartDefinition tentacle2 = body.addOrReplaceChild("tentacle2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 3.0F, 1.5F));
		PartDefinition tentacle3 = body.addOrReplaceChild("tentacle3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 3.0F, -1.5F));
		PartDefinition tentacle4 = body.addOrReplaceChild("tentacle4", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, -1.5F));

		return LayerDefinition.create(meshdefinition, 24, 12);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if  (!entity.isInWater()) {
			body.xRot = (float) (30 + Mth.sin(limbSwing) * 0.5);
		}
		else {
			body.xRot = 0;
		}

		float rotateVal = ageInTicks * 0.02F - 20;

		tentacle1.xRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;
		tentacle1.zRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;

		tentacle2.xRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;
		tentacle2.zRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;

		tentacle3.xRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
		tentacle3.zRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;

		tentacle4.xRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
		tentacle4.zRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach((modelRenderer) -> modelRenderer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha));
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelPart root, float x, float y, float z) {
		root.xRot = x;
		root.yRot = y;
		root.zRot = z;
	}
}