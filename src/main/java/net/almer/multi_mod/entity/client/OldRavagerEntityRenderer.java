package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.MultiMod;
import net.almer.multi_mod.entity.OldRavagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.RavagerEntityModel;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class OldRavagerEntityRenderer extends MobEntityRenderer<OldRavagerEntity, OldRavagerModel> {
    private static final Identifier TEXTURE = Identifier.of(MultiMod.MOD_ID, "textures/entity/old_ravager.png");
    public OldRavagerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new OldRavagerModel(context.getPart(EntityModelLayers.RAVAGER)), 1.1F);
    }
    public Identifier getTexture(OldRavagerEntity ravagerEntity) {
        return TEXTURE;
    }
}
