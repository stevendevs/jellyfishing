//// Made with Blockbench 4.5.2
//// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
//// Paste this class into your mod and generate all required imports
//
//
//public class steve<T extends Entity> extends EntityModel<T> {
//	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "steve"), "main");
//	private final ModelPart Head;
//	private final ModelPart Body;
//	private final ModelPart RightArm;
//	private final ModelPart LeftArm;
//	private final ModelPart RightLeg;
//	private final ModelPart LeftLeg;
//
//	public steve(ModelPart root) {
//		this.Head = root.getChild("Head");
//		this.Body = root.getChild("Body");
//		this.RightArm = root.getChild("RightArm");
//		this.LeftArm = root.getChild("LeftArm");
//		this.RightLeg = root.getChild("RightLeg");
//		this.LeftLeg = root.getChild("LeftLeg");
//	}
//
//	public static LayerDefinition createBodyLayer() {
//		MeshDefinition meshdefinition = new MeshDefinition();
//		PartDefinition partdefinition = meshdefinition.getRoot();
//
//		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(5, 0).addBox(-6.0F, -12.0F, -5.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
//
//		PartDefinition flower = Head.addOrReplaceChild("flower", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//		PartDefinition cube_r1 = flower.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7362F, -11.2977F, -5.2504F, 0.0F, 0.3927F, 0.3927F));
//
//		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(13, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(37, 25).addBox(-3.2F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
//
//		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(29, 52).addBox(-0.6F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(5.0F, 2.0F, 0.0F));
//
//		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
//
//		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(13, 55).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
//		.texOffs(31, 42).addBox(-2.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
//		.texOffs(31, 42).addBox(-7.0F, 9.0F, -4.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
//
//		return LayerDefinition.create(meshdefinition, 53, 68);
//	}
//
//	@Override
//	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//
//	}
//
//	@Override
//	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//	}
//}