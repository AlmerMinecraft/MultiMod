package net.almer.multi_mod.item;

import net.almer.multi_mod.MultiMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ModItemGroups {
    public static final ItemGroup MULTI_MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MultiMod.MOD_ID, "multi_mod_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.multi_mod"))
                    .icon(() -> new ItemStack(ModItems.NETHERITE_BOOK))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModItems.MOSQUITO_SPAWN_EGG);

                        entries.add(ModItems.FREEZOMBE_SPAWN_EGG);

                        entries.add(ModItems.OLD_ILLAGER_SPAWN_EGG);
                        entries.add(ModItems.OLD_RAVAGER_SPAWN_EGG);

                        entries.add(ModItems.EMPTY_LOG);
                        entries.add(ModItems.EMPTY_BARK);

                        entries.add(ModItems.LEATHER_CHESTPLATE_WITH_POCKETS);

                        entries.add(ModItems.ACORN);
                        entries.add(ModItems.COOKED_ACORN);
                        entries.add(ModItems.ACORN_BLOCK);
                        entries.add(ModItems.HOG_TOOTH);
                        entries.add(ModItems.TOOTH_SWORD);
                        entries.add(ModItems.TOOTH_ARROW);
                        entries.add(ModItems.HOG_SPAWN_EGG);

                        entries.add(ModItems.COLLAR_OF_UNDYING);
                    }))
                    .build());
    public static void registerItemGroups(){
        MultiMod.LOGGER.info("Registering item groups for: " + MultiMod.MOD_ID);
    }
}
