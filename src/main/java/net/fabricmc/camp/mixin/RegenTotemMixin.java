package net.fabricmc.camp.mixin;

import net.fabricmc.camp.items.RegeneratingTotem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class RegenTotemMixin {

    @Shadow @Final public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info){
        ItemStack stack1 = getEquippedStack(EquipmentSlot.MAINHAND);
        ItemStack stack2 = getEquippedStack(EquipmentSlot.OFFHAND);

        boolean totemEquipped = (stack1.getItem() instanceof RegeneratingTotem) || (stack2.getItem() instanceof RegeneratingTotem);

        if(totemEquipped){
            //((LivingEntity)(Object)this).addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 150));
            //((LivingEntity)(Object)this).damage(DamageSource.GENERIC, 1); //TODO makes it so that it damages itself and not you the player!
            //this.damage(DamageSource.GENERIC, 1);
        }

    }
}
