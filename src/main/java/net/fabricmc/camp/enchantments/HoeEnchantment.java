package net.fabricmc.camp.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class HoeEnchantment extends Enchantment {
    public HoeEnchantment(){
        super(Rarity.UNCOMMON, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] {EquipmentSlot.MAINHAND} );
    }

    @Override
    public int getMinPower(int level){
        return 1;
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    //TODO take a look at mobs or redstone components. Redstone circuit stuff (bluestone) -->
}
