package net.almer.multi_mod.screen;

import com.mojang.datafixers.util.Pair;
import net.almer.multi_mod.item.ChestplateWithPocketsItem;
import net.almer.multi_mod.item.ModItems;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.ClickType;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;

public class PocketScreenHandler extends ScreenHandler {
    public static final Identifier BLOCK_ATLAS_TEXTURE = Identifier.ofVanilla("textures/atlas/blocks.png");
    public static final Identifier EMPTY_HELMET_SLOT_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_helmet");
    public static final Identifier EMPTY_CHESTPLATE_SLOT_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_chestplate");
    public static final Identifier EMPTY_LEGGINGS_SLOT_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_leggings");
    public static final Identifier EMPTY_BOOTS_SLOT_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_boots");
    public static final Identifier EMPTY_OFFHAND_ARMOR_SLOT = Identifier.ofVanilla("item/empty_armor_slot_shield");
    private Inventory inventory;
    private static final Map<EquipmentSlot, Identifier> EMPTY_ARMOR_SLOT_TEXTURES;
    private static final EquipmentSlot[] EQUIPMENT_SLOT_ORDER;
    public final boolean onServer;
    private final PlayerEntity owner;
    public PocketScreenHandler(PlayerInventory inventory, boolean onServer, final PlayerEntity owner, Inventory inventory1) {
        super((ScreenHandlerType)null, 0);
        this.inventory = inventory1;
        inventory1.onOpen(owner);
        this.onServer = onServer;
        this.owner = owner;
        int i;
        int j;
        for(i = 0; i < 1; ++i) {
            for(j = 0; j < 5; ++j) {
                this.addSlot(new Slot(inventory1, 41 + j + i * 2, 80 + j * 18, 23 + i * 18));
            }
        }
        for(i = 0; i < 4; ++i) {
            EquipmentSlot equipmentSlot = EQUIPMENT_SLOT_ORDER[i];
            Identifier identifier = (Identifier)EMPTY_ARMOR_SLOT_TEXTURES.get(equipmentSlot);
            this.addSlot(new ModArmorSlot(inventory, owner, equipmentSlot, 39 - i, 8, 8 + i * 18, identifier));
        }
        for(i = 0; i < 3; ++i) {
            for(j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }

        this.addSlot(new Slot(inventory, 40, 77, 62) {
            public void setStack(ItemStack stack, ItemStack previousStack) {
                owner.onEquipStack(EquipmentSlot.OFFHAND, previousStack, stack);
                super.setStack(stack, previousStack);
            }
            public Pair<Identifier, Identifier> getBackgroundSprite() {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.EMPTY_OFFHAND_ARMOR_SLOT);
            }
        });
    }
    public static boolean isInHotbar(int slot) {
        return slot >= 36 && slot < 45 || slot == 45;
    }
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = (Slot)this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            EquipmentSlot equipmentSlot = player.getPreferredEquipmentSlot(itemStack);
            if (slot == 0) {
                if (!this.insertItem(itemStack2, 41, 45, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot >= 1 && slot < 5) {
                if (!this.insertItem(itemStack2, 41, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 5 && slot < 9) {
                if (!this.insertItem(itemStack2, 41, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && !((Slot)this.slots.get(8 - equipmentSlot.getEntitySlotId())).hasStack()) {
                int i = 8 - equipmentSlot.getEntitySlotId();
                if (!this.insertItem(itemStack2, i, i + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentSlot == EquipmentSlot.OFFHAND && !((Slot)this.slots.get(45)).hasStack()) {
                if (!this.insertItem(itemStack2, 45, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 9 && slot < 36) {
                if (!this.insertItem(itemStack2, 41, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 36 && slot < 41) {
                if (!this.insertItem(itemStack2, 41, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 41, 45, false)) {
                return ItemStack.EMPTY;
            } else if (slot >= 41 && slot< 45) {
                if (!this.insertItem(itemStack2, 0, 45, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY, itemStack);
            } else {
                slot2.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
            if (slot == 0) {
                player.dropItem(itemStack2, false);
            }
        }

        return itemStack;
    }
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return true;
    }
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
        if(player.getEquippedStack(EquipmentSlot.CHEST) != null) {
            ItemStack itemStack = player.getEquippedStack(EquipmentSlot.CHEST);
            itemStack.applyComponentsFrom(ComponentMap.builder().addAll(ComponentMap.of(ComponentMap.EMPTY, ComponentMap.EMPTY)).build());
        }
        else{
            for(int i = 0; i < this.inventory.size(); i++) {
                player.dropStack(this.inventory.getStack(i));
            }
        }
    }
    static {
        EMPTY_ARMOR_SLOT_TEXTURES = Map.of(EquipmentSlot.FEET, EMPTY_BOOTS_SLOT_TEXTURE, EquipmentSlot.LEGS, EMPTY_LEGGINGS_SLOT_TEXTURE, EquipmentSlot.CHEST, EMPTY_CHESTPLATE_SLOT_TEXTURE, EquipmentSlot.HEAD, EMPTY_HELMET_SLOT_TEXTURE);
        EQUIPMENT_SLOT_ORDER = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    }
}
