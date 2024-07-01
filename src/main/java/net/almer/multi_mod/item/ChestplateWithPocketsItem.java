package net.almer.multi_mod.item;

import net.almer.multi_mod.MultiModClient;
import net.almer.multi_mod.screen.PocketScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.stream.IntStream;

public class ChestplateWithPocketsItem extends ArmorItem {
    private DefaultedList<ItemStack> inventoryList;
    private Inventory inventory;
    public ChestplateWithPocketsItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
        this.inventoryList = DefaultedList.ofSize(5, ItemStack.EMPTY);
        this.inventory = new SimpleInventory(this.inventoryList.size());
        for(int i = 0; i < this.inventoryList.size(); i++){
            this.inventory.setStack(i, this.inventoryList.get(i));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public Inventory getInventory(){
        return this.inventory;
    }

}
