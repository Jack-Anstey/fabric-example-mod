package net.fabricmc.camp.mixin;

import net.fabricmc.camp.items.CustomArmor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class CustomArmorMixin {

    /**
     * Get a list of the armor items
     * @return the Iterable list of armorItems
     */
    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info){
        Iterable<ItemStack> armorItems = getArmorItems();
        boolean hasAllArmorWorn = true; //assume you're wearing all the armor parts
        for(ItemStack armor : armorItems){ //iterate through the list
            if(!(armor.getItem() instanceof CustomArmor)){ //if it's not a part of CustomArmor
               hasAllArmorWorn = false; //swap
               break; //break
            }
        }

        if(hasAllArmorWorn){
            //status effect code here
            ((LivingEntity)(Object)this).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 150));
        }
    }
}
