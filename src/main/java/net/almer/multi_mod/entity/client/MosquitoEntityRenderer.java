package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.MultiMod;
import net.almer.multi_mod.MultiModClient;
import net.almer.multi_mod.entity.MosquitoEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MosquitoEntityRenderer extends MobEntityRenderer<MosquitoEntity, MosquitoModel<MosquitoEntity>> {
    public static final Identifier TEXTURE = Identifier.of(MultiMod.MOD_ID, "textures/entity/mosquito.png");
    public MosquitoEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MosquitoModel(context.getPart(MultiModClient.MOSQUITO_RENDER_LAYER)), 1f);
    }
    @Override
    public Identifier getTexture(MosquitoEntity entity) {
        return TEXTURE;
    }
}
