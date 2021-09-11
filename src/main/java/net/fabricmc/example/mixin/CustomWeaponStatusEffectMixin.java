package net.fabricmc.example.mixin;

import net.fabricmc.example.items.CustomPickaxe;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class CustomWeaponStatusEffectMixin {
    //@Shadow @Final private getEquippedStack(EquipmentSlot.MAINHAND) held;

    @Shadow @Final public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info){
        ItemStack stack = getEquippedStack(EquipmentSlot.MAINHAND);
        boolean weaponEquipped = (stack.getItem() instanceof CustomPickaxe);
        if(weaponEquipped){
            //status effect code here
            ((LivingEntity)(Object)this).addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 150));
        }
    }
}
