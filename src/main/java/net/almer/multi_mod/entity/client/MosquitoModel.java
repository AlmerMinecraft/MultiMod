package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.entity.MosquitoEntity;
import net.almer.multi_mod.entity.client.animation.MosquitoEntityAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class MosquitoModel<T extends MosquitoEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart nose;
	private final ModelPart wingL;
	private final ModelPart wingR;
	public MosquitoModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.nose = head.getChild("nose");
		this.wingL = head.getChild("wingL");
		this.wingR = head.getChild("wingR");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.4F, 0.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 1.6F, 0.0F, -0.9599F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(9, 8).cuboid(-1.0F, -1.5F, -3.4F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData nose = head.addChild("nose", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.6F, 0.0F));

		ModelPartData nose_r1 = nose.addChild("nose_r1", ModelPartBuilder.create().uv(0, 11).cuboid(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -4.2F, -3.8F, 0.48F, 0.0F, 0.0F));

		ModelPartData wingL = head.addChild("wingL", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.5F, -1.0F));

		ModelPartData wingL_r1 = wingL.addChild("wingL_r1", ModelPartBuilder.create().uv(8, 0).cuboid(0.0F, 0.0F, -4.0F, 5.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, 0.0F, -0.6545F, 0.0F));

		ModelPartData wingR = head.addChild("wingR", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.5F, -1.0F));

		ModelPartData wingR_r1 = wingR.addChild("wingR_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-5.0F, 0.0F, -4.0F, 5.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, 0.0F, 0.6545F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	public ModelPart getPart(){
		return this.body;
	}
	@Override
	public void setAngles(MosquitoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.updateAnimation(entity.idleAnimationState, MosquitoEntityAnimation.DEFAULT, ageInTicks, 1f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		body.render(matrices, vertices, light, overlay, color);
	}
	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
}