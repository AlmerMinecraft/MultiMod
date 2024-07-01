package net.almer.multi_mod.sounds;

import net.almer.multi_mod.MultiMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent MOSQUITO_AMBIENT = register("mosquito_ambient");
    private static SoundEvent register(String id){
        Identifier id2 = Identifier.of(MultiMod.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, id2, SoundEvent.of(id2));
    }
    public static void registerSounds(){
        MultiMod.LOGGER.info("Registering sounds for: " + MultiMod.MOD_ID);
    }
}
