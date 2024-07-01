package net.almer.multi_mod;

import net.almer.multi_mod.block.ModBlocks;
import net.almer.multi_mod.entity.ModEntityTypes;
import net.almer.multi_mod.entity.ToothArrowEntity;
import net.almer.multi_mod.entity.client.*;
import net.almer.multi_mod.item.CollarOfUndyingItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.util.InputUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;
import java.nio.channels.IllegalBlockingModeException;

@Environment(EnvType.CLIENT)
public class MultiModClient implements ClientModInitializer {
    public static final EntityModelLayer MOSQUITO_RENDER_LAYER = new EntityModelLayer(Identifier.of(MultiMod.MOD_ID, "mosquito"), "main");
    public static final EntityModelLayer FREEZOMBE_RENDER_LAYER = new EntityModelLayer(Identifier.of(MultiMod.MOD_ID, "freezombe"), "main");
    public static final EntityModelLayer OLD_ILLAGER_RENDER_LAYER = new EntityModelLayer(Identifier.of(MultiMod.MOD_ID, "old_illager"), "main");
    public static final EntityModelLayer OLD_RAVAGER_RENDER_LAYER = new EntityModelLayer(Identifier.of(MultiMod.MOD_ID, "old_ravager"), "main");
    public static final EntityModelLayer HOG_RENDER_LAYER = new EntityModelLayer(Identifier.of(MultiMod.MOD_ID, "hog"), "main");
    public static KeyBinding POCKETS_KEY;
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntityTypes.MOSQUITO_ENTITY, (context) -> {
            return new MosquitoEntityRenderer(context);
        });
        EntityRendererRegistry.register(ModEntityTypes.FREEZOMBE_ENTITY, (context) -> {
            return new FreezombeEntityRenderer(context);
        });
        EntityRendererRegistry.register(ModEntityTypes.OLD_ILLAGER_ENTITY, (context) -> {
            return new OldIllagerEntityRenderer(context);
        });
        EntityRendererRegistry.register(ModEntityTypes.OLD_RAVAGER_ENTITY, (context) -> {
            return new OldRavagerEntityRenderer(context);
        });
        EntityRendererRegistry.register(ModEntityTypes.TOOTH_ARROW_ENTITY, (context) -> {
            return new ToothArrowEntityRenderer(context);
        });
        EntityRendererRegistry.register(ModEntityTypes.HOG_ENTITY, (context) -> {
            return new HogEntityRenderer(context);
        });
        EntityModelLayerRegistry.registerModelLayer(MOSQUITO_RENDER_LAYER, MosquitoModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FREEZOMBE_RENDER_LAYER, FreezombeModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(OLD_ILLAGER_RENDER_LAYER, OldIllagerModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(OLD_RAVAGER_RENDER_LAYER, OldRavagerModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HOG_RENDER_LAYER, HogModel::getTexturedModelData);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMPTY_LOG, RenderLayer.getCutout());

        ModelPredicateProviderRegistry.register(Items.TOTEM_OF_UNDYING, Identifier.ofVanilla("is_collar"), (stack, world, entity, seed) -> {
            if(stack.getComponents().get(DataComponentTypes.CUSTOM_NAME) != null){
                return stack.getComponents().get(DataComponentTypes.CUSTOM_NAME).contains(Text.literal(CollarOfUndyingItem.COLLAR_NAME)) ? 1.0f : 0.0f;
            }
            else{
                return 0.0f;
            }
        });

        POCKETS_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.multi-mod.pockets", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, "category.multi-mod.keybinds"));
    }
}
