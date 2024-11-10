package net.nekogrile.tutorialmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nekogrile.tutorialmod.TutorialMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MOD_ID);

    // Déclare le son "squalala" correctement
    public static final RegistryObject<SoundEvent> SOUND_SQUALALA = SOUND_EVENTS.register("squalala",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TutorialMod.MOD_ID, "squalala")));

    // Méthode pour enregistrer les sons sur le bus d'événements
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
