package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.MultiMod;
import net.almer.multi_mod.MultiModClient;
import net.almer.multi_mod.entity.OldIllagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class OldIllagerEntityRenderer extends IllagerEntityRenderer<OldIllagerEntity> {
    private static final Identifier TEXTURE = Identifier.of(MultiMod.MOD_ID, "textures/entity/old_illager.png");
    public OldIllagerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new OldIllagerModel(context.getPart(MultiModClient.OLD_ILLAGER_RENDER_LAYER)), 0.5F);
        this.addFeature(new HeldItemFeatureRenderer(this, context.getHeldItemRenderer()));
    }
    public Identifier getTexture(OldIllagerEntity vindicatorEntity) {
        return TEXTURE;
    }
}
