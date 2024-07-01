package net.almer.multi_mod.entity;

import net.almer.multi_mod.item.ModItems;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class ToothArrowEntity extends PersistentProjectileEntity {
    public ToothArrowEntity(EntityType<? extends PersistentProjectileEntity> entityEntityType, World world) {
        super(entityEntityType, world);
    }
    public ToothArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ModEntityTypes.TOOTH_ARROW_ENTITY, x, y, z, world, stack, shotFrom);
    }
    public ToothArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ModEntityTypes.TOOTH_ARROW_ENTITY, owner, world, stack, shotFrom);
    }
    public void tick() {
        super.tick();
        if (this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 600) {
            this.getWorld().sendEntityStatus(this, (byte)0);
            this.setStack(new ItemStack(ModItems.TOOTH_ARROW));
        }
    }
    protected void onHit(LivingEntity target) {
        super.onHit(target);
    }
    @Override
    protected ItemStack getDefaultItemStack() {
        return ModItems.TOOTH_ARROW.getDefaultStack();
    }
}
