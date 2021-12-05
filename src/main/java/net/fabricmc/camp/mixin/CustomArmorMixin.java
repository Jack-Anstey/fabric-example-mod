package net.fabricmc.camp.mixin;

import net.fabricmc.camp.items.CustomArmor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class CustomArmorMixin {
    /*
    @Shadow @Final private DefaultedList<ItemStack> equippedArmor;

    //@Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info){
        boolean hasAllArmorWorn = true;
        for(int index = 0; index < 4; index++){ //4 may be a magic number, but it is the number of armor pieces in the game
            if(!(equippedArmor.get(index).getItem() instanceof CustomArmor)){
                hasAllArmorWorn = false;
                break;
            }
        }
        if(hasAllArmorWorn){
            //status effect code here
            ((LivingEntity)(Object)this).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 150));
        }
    }
     */
}
