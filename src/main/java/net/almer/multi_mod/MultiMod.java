package net.almer.multi_mod;

import com.mojang.serialization.MapCodec;
import net.almer.multi_mod.block.ModBlocks;
import net.almer.multi_mod.entity.*;
import net.almer.multi_mod.item.ModFoodComponents;
import net.almer.multi_mod.item.ModItemGroups;
import net.almer.multi_mod.item.ModItems;
import net.almer.multi_mod.sounds.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiMod implements ModInitializer {
	public static final String MOD_ID = "multi-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerBlocks();

		FabricDefaultAttributeRegistry.register(ModEntityTypes.MOSQUITO_ENTITY, MosquitoEntity.createMosquitoAttributes());
		FabricDefaultAttributeRegistry.register(ModEntityTypes.FREEZOMBE_ENTITY, FreezombeEntity.createFreeZombeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntityTypes.OLD_ILLAGER_ENTITY, OldIllagerEntity.createOldIllagerAttributes());
		FabricDefaultAttributeRegistry.register(ModEntityTypes.OLD_RAVAGER_ENTITY, OldRavagerEntity.createRavagerAttributes());
		FabricDefaultAttributeRegistry.register(ModEntityTypes.HOG_ENTITY, HogEntity.createHoglinAttributes());

		ModEntityTypes.registerEntities();
		ModSounds.registerSounds();
		ModFoodComponents.registerFood();
	}
}