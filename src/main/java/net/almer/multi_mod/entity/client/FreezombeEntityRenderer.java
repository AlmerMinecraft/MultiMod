package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.MultiMod;
import net.almer.multi_mod.MultiModClient;
import net.almer.multi_mod.entity.FreezombeEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class FreezombeEntityRenderer extends ZombieEntityRenderer {
    private static final Identifier TEXTURE = Identifier.of(MultiMod.MOD_ID, "textures/entity/freezombe.png");
    public FreezombeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
