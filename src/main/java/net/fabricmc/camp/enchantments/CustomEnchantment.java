package net.fabricmc.camp.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class CustomEnchantment extends Enchantment {

    public CustomEnchantment(){
        super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND} );
    }

    @Override
    public int getMinPower(int level){
        return 1;
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){
        //instanceof checks to see if one class is a specific subclassS
        //slows the target
        if(target instanceof LivingEntity){
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40*level, level-1));
        }

        super.onTargetDamaged(user, target, level);
    }
}
