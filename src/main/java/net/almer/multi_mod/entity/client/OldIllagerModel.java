package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.entity.OldIllagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class OldIllagerModel<T extends OldIllagerEntity> extends IllagerEntityModel<OldIllagerEntity> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart arms;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	public OldIllagerModel(ModelPart root) {
		super(root);
		this.root = root;
		this.head = root.getChild("head");
		this.hat = this.head.getChild("hat");
		this.hat.visible = false;
		this.arms = root.getChild("arms");
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
		this.leftArm = root.getChild("left_arm");
		this.rightArm = root.getChild("right_arm");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData waist = modelPartData.addChild("waist", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));
		ModelPartData Body = waist.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));
		Body.addChild("Body_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -24.0F, -3.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.5F))
				.uv(0, 34).cuboid(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.4F, 0.2618F, 0.0F, 0.0F));
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(20, 16).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F))
				.uv(28, 0).cuboid(-4.0F, -2.0F, -4.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, 1.2F, -6.4F));
		head.addChild("nose", ModelPartBuilder.create().uv(22, 0).cuboid(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
		head.addChild("hat", ModelPartBuilder.create().uv(-16, -8).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(40, 50).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
		modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(24, 50).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
		modelPartData.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(44, 34).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.8F, -4.0F));
		modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(44, 34).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, 2.8F, -4.0F));
		return TexturedModelData.of(modelData, 64, 66);
	}
	public ModelPart getPart() {
		return this.root;
	}

	public void setAngles(OldIllagerEntity illagerEntity, float f, float g, float h, float i, float j) {
		this.head.yaw = i * 0.017453292F;
		this.head.pitch = j * 0.017453292F;
		if (this.riding) {
			this.rightArm.pitch = -0.62831855F;
			this.rightArm.yaw = 0.0F;
			this.rightArm.roll = 0.0F;
			this.leftArm.pitch = -0.62831855F;
			this.leftArm.yaw = 0.0F;
			this.leftArm.roll = 0.0F;
			this.rightLeg.pitch = -1.4137167F;
			this.rightLeg.yaw = 0.31415927F;
			this.rightLeg.roll = 0.07853982F;
			this.leftLeg.pitch = -1.4137167F;
			this.leftLeg.yaw = -0.31415927F;
			this.leftLeg.roll = -0.07853982F;
		} else {
			this.rightArm.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 2.0F * g * 0.5F;
			this.rightArm.yaw = 0.0F;
			this.rightArm.roll = 0.0F;
			this.leftArm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F;
			this.leftArm.yaw = 0.0F;
			this.leftArm.roll = 0.0F;
			this.rightLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g * 0.5F;
			this.rightLeg.yaw = 0.0F;
			this.rightLeg.roll = 0.0F;
			this.leftLeg.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g * 0.5F;
			this.leftLeg.yaw = 0.0F;
			this.leftLeg.roll = 0.0F;
		}

		OldIllagerEntity.State state = illagerEntity.getState();
		if (state == OldIllagerEntity.State.ATTACKING) {
			if (illagerEntity.getMainHandStack().isEmpty()) {
				CrossbowPosing.meleeAttack(this.leftArm, this.rightArm, true, this.handSwingProgress, h);
			} else {
				CrossbowPosing.meleeAttack(this.rightArm, this.leftArm, illagerEntity, this.handSwingProgress, h);
			}
		} else if (state == OldIllagerEntity.State.SPELLCASTING) {
			this.rightArm.pivotZ = 0.0F;
			this.rightArm.pivotX = -5.0F;
			this.leftArm.pivotZ = 0.0F;
			this.leftArm.pivotX = 5.0F;
			this.rightArm.pitch = MathHelper.cos(h * 0.6662F) * 0.25F;
			this.leftArm.pitch = MathHelper.cos(h * 0.6662F) * 0.25F;
			this.rightArm.roll = 2.3561945F;
			this.leftArm.roll = -2.3561945F;
			this.rightArm.yaw = 0.0F;
			this.leftArm.yaw = 0.0F;
		} else if (state == OldIllagerEntity.State.BOW_AND_ARROW) {
			this.rightArm.yaw = -0.1F + this.head.yaw;
			this.rightArm.pitch = -1.5707964F + this.head.pitch;
			this.leftArm.pitch = -0.9424779F + this.head.pitch;
			this.leftArm.yaw = this.head.yaw - 0.4F;
			this.leftArm.roll = 1.5707964F;
		} else if (state == OldIllagerEntity.State.CROSSBOW_HOLD) {
			CrossbowPosing.hold(this.rightArm, this.leftArm, this.head, true);
		} else if (state == OldIllagerEntity.State.CROSSBOW_CHARGE) {
			CrossbowPosing.charge(this.rightArm, this.leftArm, illagerEntity, true);
		} else if (state == OldIllagerEntity.State.CELEBRATING) {
			this.rightArm.pivotZ = 0.0F;
			this.rightArm.pivotX = -5.0F;
			this.rightArm.pitch = MathHelper.cos(h * 0.6662F) * 0.05F;
			this.rightArm.roll = 2.670354F;
			this.rightArm.yaw = 0.0F;
			this.leftArm.pivotZ = 0.0F;
			this.leftArm.pivotX = 5.0F;
			this.leftArm.pitch = MathHelper.cos(h * 0.6662F) * 0.05F;
			this.leftArm.roll = -2.3561945F;
			this.leftArm.yaw = 0.0F;
		}
	}

	private ModelPart getAttackingArm(Arm arm) {
		return arm == Arm.LEFT ? this.leftArm : this.rightArm;
	}

	public ModelPart getHat() {
		return this.hat;
	}

	public ModelPart getHead() {
		return this.head;
	}

	public void setArmAngle(Arm arm, MatrixStack matrices) {
		this.getAttackingArm(arm).rotate(matrices);
	}
}