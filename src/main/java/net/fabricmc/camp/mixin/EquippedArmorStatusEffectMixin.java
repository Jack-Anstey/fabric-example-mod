package net.fabricmc.camp.mixin;

import net.fabricmc.camp.items.CustomArmor;
import net.minecraft.entity.EquipmentSlot;
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
public class EquippedArmorStatusEffectMixin {
    /*
    @Shadow @Final private DefaultedList<ItemStack> equippedArmor;
    */

    /**
     * This is close to working: currently can't check all four slots and gets confused while
     * equipping and dequipping. In addition, the status effect isn't permanent for some reason
     * @param slot
     * @param armor
     * @param info
     */
    /*
    @Inject(at = @At("HEAD"), method = "setArmorInSlot")
    public void setArmorInSlot(EquipmentSlot slot, ItemStack armor, CallbackInfo info){
        boolean hasAllArmorWorn = true;
        StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.SPEED, 150);
        for(int index = 0; index < 4; index++){ //4 may be a magic number, but it is the number of armor pieces in the game
            if(!(armor.getItem() instanceof CustomArmor)){
                hasAllArmorWorn = false;
                break;
            }
        }
        System.out.println("hasAllArmorWorn = " + hasAllArmorWorn);
        instance.setPermanent(hasAllArmorWorn);
        ((LivingEntity)(Object)this).addStatusEffect(instance);
    }
    */
}
