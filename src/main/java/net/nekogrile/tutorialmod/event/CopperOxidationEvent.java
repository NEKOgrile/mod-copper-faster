package net.nekogrile.tutorialmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nekogrile.tutorialmod.TutorialMod;
import net.nekogrile.tutorialmod.item.ModItems;
import net.nekogrile.tutorialmod.sound.ModSounds;

import java.util.Random;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CopperOxidationEvent {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        // Vérifie si le joueur tient l'objet "Vine Special" dans la main principale
        if (event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.VINE_SPECIAL.get()) {
            BlockPos pos = event.getPos();
            BlockState state = event.getLevel().getBlockState(pos);

            // Vérifie si le niveau est un ServerLevel pour permettre les modifications
            if (event.getLevel() instanceof ServerLevel serverLevel) {
                BlockState newState = null;

                // Le cuivre normal devient exposed, exposed devient weathered, et weathered devient oxidized
                if (state.is(Blocks.COPPER_BLOCK)) {
                    newState = Blocks.EXPOSED_COPPER.defaultBlockState();
                } else if (state.is(Blocks.EXPOSED_COPPER)) {
                    newState = Blocks.WEATHERED_COPPER.defaultBlockState();
                } else if (state.is(Blocks.WEATHERED_COPPER)) {
                    newState = Blocks.OXIDIZED_COPPER.defaultBlockState();
                }



                // Si un nouvel état a été déterminé, on met à jour le bloc
                if (newState != null) {
                    serverLevel.setBlockAndUpdate(pos, newState);

                    // Joue le son "squalala" avec une probabilité de 10%
                    serverLevel.playSound(null, pos, ModSounds.SOUND_SQUALALA.get(), SoundSource.BLOCKS, 1.0F, 1.0F);



                    // Annule l'événement pour empêcher d'autres interactions
                    event.setCancellationResult(InteractionResult.SUCCESS);
                    event.setCanceled(true);
                }
            }
        }
    }
}
