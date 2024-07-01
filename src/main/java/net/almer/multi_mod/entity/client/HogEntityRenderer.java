package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.MultiMod;
import net.almer.multi_mod.MultiModClient;
import net.almer.multi_mod.entity.HogEntity;
import net.almer.multi_mod.entity.OldRavagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HogEntityRenderer extends MobEntityRenderer<HogEntity, HogModel> {
    private static final Identifier TEXTURE = Identifier.of(MultiMod.MOD_ID, "textures/entity/hog.png");
    public HogEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new HogModel(context.getPart(MultiModClient.HOG_RENDER_LAYER)), 1.0f);
    }
    @Override
    public Identifier getTexture(HogEntity entity) {
        return TEXTURE;
    }
}
