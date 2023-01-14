package com.jellyfishing.client.model;

import com.jellyfishing.core.Jellyfishing;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

public class SpatulaModel extends Model {
	public static final ResourceLocation TEXTURE = Jellyfishing.id("textures/entity/spatula.png");

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Jellyfishing.id("spatula"), "main");
	private final ModelPart root;

	public SpatulaModel(ModelPart modelPart) {
		super(RenderType::entityCutout);
		this.root = modelPart;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 6).addBox(-10.0F, -7.0F, 7.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 8).addBox(-9.0F, -16.0F, 8.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-10.0F, -17.0F, 8.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(9, 6).addBox(-11.0F, -18.0F, 8.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-12.0F, -23.0F, 8.0F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}