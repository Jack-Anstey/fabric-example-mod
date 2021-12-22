package net.fabricmc.camp.mixin;

import net.fabricmc.camp.items.CustomPickaxe;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
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
        boolean hasEnchant = false;

        if(weaponEquipped){
            for(NbtElement enchantment : stack.getEnchantments()){ //sketchy but it works!!!!
                if(enchantment.toString().contains("sweeping")){
                    hasEnchant = true;
                    break;
                }
            }

            if(!hasEnchant){
                stack.addEnchantment(Enchantments.SWEEPING, 3);
            }
        }
    }
}

