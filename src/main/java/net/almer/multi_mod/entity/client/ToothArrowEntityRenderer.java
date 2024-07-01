package net.almer.multi_mod.entity.client;

import net.almer.multi_mod.entity.ToothArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class ToothArrowEntityRenderer extends ProjectileEntityRenderer<ToothArrowEntity> {
    public static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/projectiles/arrow.png");
    public ToothArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }
    public Identifier getTexture(ToothArrowEntity arrowEntity) {
        return TEXTURE;
    }
}
