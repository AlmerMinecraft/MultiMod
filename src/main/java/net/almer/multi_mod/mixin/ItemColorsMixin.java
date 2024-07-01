package net.almer.multi_mod.mixin;

import it.unimi.dsi.fastutil.ints.IntList;
import net.almer.multi_mod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.MapColorComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.FoliageColors;
import net.minecraft.world.biome.GrassColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(ItemColors.class)
@Environment(EnvType.CLIENT)
public class ItemColorsMixin {
    @Inject(method = "create(Lnet/minecraft/client/color/block/BlockColors;)Lnet/minecraft/client/color/item/ItemColors;", at = @At("TAIL"))
    private static ItemColors create(BlockColors blockColors, CallbackInfoReturnable ci){
        ItemColors itemColors = new ItemColors();
        itemColors.register((stack, tintIndex) -> {
            return tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, -6265536);
        }, Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS, Items.LEATHER_HORSE_ARMOR, ModItems.LEATHER_CHESTPLATE_WITH_POCKETS);
        itemColors.register((stack, tintIndex) -> {
            return tintIndex != 1 ? -1 : DyedColorComponent.getColor(stack, 0);
        }, Items.WOLF_ARMOR);
        itemColors.register((stack, tintIndex) -> {
            return GrassColors.getColor(0.5, 1.0);
        }, Blocks.TALL_GRASS, Blocks.LARGE_FERN);
        itemColors.register((stack, tintIndex) -> {
            if (tintIndex != 1) {
                return -1;
            } else {
                FireworkExplosionComponent fireworkExplosionComponent = (FireworkExplosionComponent)stack.get(DataComponentTypes.FIREWORK_EXPLOSION);
                IntList intList = fireworkExplosionComponent != null ? fireworkExplosionComponent.colors() : IntList.of();
                int i = intList.size();
                if (i == 0) {
                    return -7697782;
                } else if (i == 1) {
                    return ColorHelper.Argb.fullAlpha(intList.getInt(0));
                } else {
                    int j = 0;
                    int k = 0;
                    int l = 0;

                    for(int m = 0; m < i; ++m) {
                        int n = intList.getInt(m);
                        j += ColorHelper.Argb.getRed(n);
                        k += ColorHelper.Argb.getGreen(n);
                        l += ColorHelper.Argb.getBlue(n);
                    }

                    return ColorHelper.Argb.getArgb(j / i, k / i, l / i);
                }
            }
        }, Items.FIREWORK_STAR);
        itemColors.register((stack, tintIndex) -> {
            return tintIndex > 0 ? -1 : ColorHelper.Argb.fullAlpha(((PotionContentsComponent)stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT)).getColor());
        }, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW);
        Iterator var2 = SpawnEggItem.getAll().iterator();

        while(var2.hasNext()) {
            SpawnEggItem spawnEggItem = (SpawnEggItem)var2.next();
            itemColors.register((stack, tintIndex) -> {
                return ColorHelper.Argb.fullAlpha(spawnEggItem.getColor(tintIndex));
            }, spawnEggItem);
        }

        itemColors.register((stack, tintIndex) -> {
            BlockState blockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(blockState, (BlockRenderView)null, (BlockPos)null, tintIndex);
        }, Blocks.GRASS_BLOCK, Blocks.SHORT_GRASS, Blocks.FERN, Blocks.VINE, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.BIRCH_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.LILY_PAD);
        itemColors.register((stack, tintIndex) -> {
            return FoliageColors.getMangroveColor();
        }, Blocks.MANGROVE_LEAVES);
        itemColors.register((stack, tintIndex) -> {
            return tintIndex == 0 ? -1 : ColorHelper.Argb.fullAlpha(((MapColorComponent)stack.getOrDefault(DataComponentTypes.MAP_COLOR, MapColorComponent.DEFAULT)).rgb());
        }, Items.FILLED_MAP);
        return itemColors;
    }
}
