package net.almer.multi_mod.mixin;

import net.almer.multi_mod.MultiModClient;
import net.almer.multi_mod.item.ModItems;
import net.almer.multi_mod.screen.PocketScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public class MinecraftClientMixin {
    @Inject(method = "handleInputEvents()V", at = @At("HEAD"))
    private void handleInputEvents(CallbackInfo ci){
        while(MultiModClient.POCKETS_KEY.wasPressed()) {
            if(MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.LEATHER_CHESTPLATE_WITH_POCKETS)) {
                MinecraftClient.getInstance().getTutorialManager().onInventoryOpened();
                MinecraftClient.getInstance().setScreen(new PocketScreen(MinecraftClient.getInstance().player));
            }
        }
    }
}
