package net.fabricmc.example.mixin;

import net.fabricmc.example.items.CustomArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(CustomArmor.class)
public class CustomArmorMixin {
   // @Inject(at = @At(value = "INVOKE", target = "net.fabricmc.example/"))
    /**
     * but yeah personally, I would inject into PlayerEntity#updateTurtleHelmet()
     * because logically thats where MC does its player armor checks to add status effects anyways
     *
     * Other notes: status effects have to be applied Server side in order to work (as each minecraft world
     * is hosted in its own virtual "server")
     *
     * Literally no fucking clue on how to do that
     */
}
