package net.almer.multi_mod.block;

import net.almer.multi_mod.MultiMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block EMPTY_LOG = register("empty_log", new EmptyLogBlock(AbstractBlock.Settings.copyShallow(Blocks.OAK_LOG)));
    public static final Block ACORN_BLOCK = register("acorn_block", new Block(AbstractBlock.Settings.create().burnable().mapColor(DyeColor.BROWN).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    private static Block register(String id, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(MultiMod.MOD_ID, id), block);
    }
    public static void registerBlocks(){
        MultiMod.LOGGER.info("Registering blocks for: " + MultiMod.MOD_ID);
    }
}
