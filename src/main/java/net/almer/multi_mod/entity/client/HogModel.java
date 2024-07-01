package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.entity.HogEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class HogModel extends QuadrupedEntityModel<HogEntity> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	public HogModel(ModelPart root) {
		super(root, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rightHindLeg = root.getChild("right_hind_leg");
		this.leftHindLeg = root.getChild("left_hind_leg");
		this.rightFrontLeg = root.getChild("right_front_leg");
		this.leftFrontLeg = root.getChild("left_front_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 11.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(28, 8).cuboid(-5.0F, -23.0F, -5.0F, 10.0F, 13.0F, 8.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, 13.4F, -13.3F, -0.7418F, 0.0F, 0.0F));

		ModelPartData body_r2 = body.addChild("body_r2", ModelPartBuilder.create().uv(28, 8).cuboid(-5.0F, -23.0F, -5.0F, 10.0F, 13.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.2F, 8.2F, 0.6545F, 0.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(15, 27).cuboid(-2.0F, 0.0F, -10.0F, 4.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.6F, 1.6F, -9.4F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.7F, 1.6F, -9.4F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.4F, -6.0F));

		ModelPartData leg0 = modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -4.4F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 18.0F, 7.0F));

		ModelPartData leg1 = modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -4.4F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.0F, 18.0F, 7.0F));

		ModelPartData leg2 = modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 18.0F, -5.0F));

		ModelPartData leg3 = modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.0F, 18.0F, -5.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}
}