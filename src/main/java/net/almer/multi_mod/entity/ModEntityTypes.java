package net.almer.multi_mod.entity;

import net.almer.multi_mod.MultiMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    public static final EntityType<MosquitoEntity> MOSQUITO_ENTITY = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(MultiMod.MOD_ID, "mosquito"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MosquitoEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 0.6f)).build());
    public static final EntityType<FreezombeEntity> FREEZOMBE_ENTITY = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(MultiMod.MOD_ID, "freezombe"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FreezombeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<OldIllagerEntity> OLD_ILLAGER_ENTITY = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(MultiMod.MOD_ID, "old_illager"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OldIllagerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<OldRavagerEntity> OLD_RAVAGER_ENTITY = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(MultiMod.MOD_ID, "old_ravager"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OldRavagerEntity::new)
                    .dimensions(EntityDimensions.fixed(1.95f, 2.2f)).build());
    public static final EntityType<ToothArrowEntity> TOOTH_ARROW_ENTITY = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(MultiMod.MOD_ID, "tooth_arrow"),
            FabricEntityTypeBuilder.<ToothArrowEntity>create(SpawnGroup.MISC, ToothArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .trackRangeBlocks(4).trackedUpdateRate(10).build());
    public static final EntityType<HogEntity> HOG_ENTITY = Registry.register(
            Registries.ENTITY_TYPE, Identifier.of(MultiMod.MOD_ID, "hog"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HogEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 1.2f)).build());
    public static void registerEntities(){
        MultiMod.LOGGER.info("Registering entities for: " + MultiMod.MOD_ID);
    }
}
