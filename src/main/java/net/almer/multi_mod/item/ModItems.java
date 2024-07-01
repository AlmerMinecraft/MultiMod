package net.almer.multi_mod.item;

import net.almer.multi_mod.MultiMod;
import net.almer.multi_mod.block.ModBlocks;
import net.almer.multi_mod.entity.ModEntityTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item MOSQUITO_SPAWN_EGG = register("mosquito_spawn_egg", new SpawnEggItem(ModEntityTypes.MOSQUITO_ENTITY, 0x554e61, 0xf26a1b, new Item.Settings()));
    public static final Item FREEZOMBE_SPAWN_EGG = register("freezombe_spawn_egg", new SpawnEggItem(ModEntityTypes.FREEZOMBE_ENTITY, 0x5585a6, 0x1a753b, new Item.Settings()));
    public static final Item OLD_ILLAGER_SPAWN_EGG = register("old_illager_spawn_egg", new SpawnEggItem(ModEntityTypes.OLD_ILLAGER_ENTITY, 0x2f661d, 0x6d767a, new Item.Settings()));
    public static final Item OLD_RAVAGER_SPAWN_EGG = register("old_ravager_spawn_egg", new SpawnEggItem(ModEntityTypes.OLD_RAVAGER_ENTITY, 0x3b5445, 0x86989c, new Item.Settings()));
    public static final Item EMPTY_LOG = register("empty_log", new BlockItem(ModBlocks.EMPTY_LOG, new Item.Settings()));
    public static final Item EMPTY_BARK = register("empty_bark", new Item(new Item.Settings()));
    public static final Item LEATHER_CHESTPLATE_WITH_POCKETS = register("leather_chestplate_with_pockets", new ChestplateWithPocketsItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings()
            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(5))
            .component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT)));
    public static final Item NETHERITE_BOOK = register("netherite_book", new WrittenBookItem(new Item.Settings().maxCount(16).fireproof().component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item ACORN = register("acorn", new Item(new Item.Settings()));
    public static final Item COOKED_ACORN = register("cooked_acorn", new Item(new Item.Settings().food(ModFoodComponents.ACORN)));
    public static final Item ACORN_BLOCK = register("acorn_block", new BlockItem(ModBlocks.ACORN_BLOCK, new Item.Settings()));
    public static final Item HOG_TOOTH = register("hog_tooth", new Item(new Item.Settings()));
    public static final Item TOOTH_SWORD = register("tooth_sword", new SwordItem(ToolMaterials.IRON, new Item.Settings().maxDamage(430).maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 4, -2.4F))));
    public static final Item TOOTH_ARROW = register("tooth_arrow", new ToothArrowItem(new Item.Settings()));
    public static final Item HOG_SPAWN_EGG = register("hog_spawn_egg", new SpawnEggItem(ModEntityTypes.HOG_ENTITY, 0x4a424f, 0xaa8abf, new Item.Settings()));
    public static final Item COLLAR_OF_UNDYING = register("collar_of_undying", new CollarOfUndyingItem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
    private static Item register(String id, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(MultiMod.MOD_ID, id), item);
    }
    public static void registerItems(){
        MultiMod.LOGGER.info("Registering items for: " + MultiMod.MOD_ID);
    }
}
