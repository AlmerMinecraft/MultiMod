package net.almer.multi_mod.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CollarOfUndyingItem extends Item {
    public static final String COLLAR_NAME = "uuuAAAAjnfkjsdnfjsndkjfnsdkjfnskjdfpp";
    public CollarOfUndyingItem(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(user.isSneaking()){
            if(stack.isOf(ModItems.COLLAR_OF_UNDYING)){
                if(!user.getAbilities().creativeMode) {
                    user.getStackInHand(hand).decrement(1);
                }
                ItemStack itemStack = Items.TOTEM_OF_UNDYING.getDefaultStack();
                itemStack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(COLLAR_NAME));
                if(entity.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
                    entity.setStackInHand(Hand.MAIN_HAND, itemStack);
                }
                else{
                    entity.setStackInHand(Hand.OFF_HAND, itemStack);
                }
                user.swingHand(hand);
            }
        }
        return ActionResult.SUCCESS;
    }
}
