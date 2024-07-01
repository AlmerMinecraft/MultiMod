package net.almer.multi_mod.item;

import net.almer.multi_mod.MultiMod;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModFoodComponents {
    public static final FoodComponent ACORN = new FoodComponent.Builder().saturationModifier(0.3f).snack().nutrition(2).build();
    public static void registerFood(){
        MultiMod.LOGGER.info("Registering food for: " + MultiMod.MOD_ID);
    }
}
