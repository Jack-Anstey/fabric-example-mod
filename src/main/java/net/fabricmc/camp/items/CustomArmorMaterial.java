package net.fabricmc.camp.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CustomArmorMaterial implements ArmorMaterial {
    /**
     * These two int arrays define the durability and protection values for the armor
     * Order is helmet, chest, legs, then boots
     * Leather is {1,2,3,1}, Diamond/Netherite {3,6,8,3}
     * Durability is how long armor can last and protection is damage reduction
     */
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {1, 17, 1, 1};
    private static final int enchantValue = 25;

    @Override
    public int getDurability(EquipmentSlot slot){
        //leather is 5, diamond 33, netherite 35
        return BASE_DURABILITY[slot.getEntitySlotId()]*33;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot){
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability(){
        return enchantValue;
    }

    @Override
    public SoundEvent getEquipSound(){
        return SoundEvents.ITEM_ARMOR_EQUIP_TURTLE;
    }

    @Override
    public Ingredient getRepairIngredient(){
        //return Ingredient.ofItems(ExampleMod.BASIC_ITEM);
        return Ingredient.ofItems(Items.POTATO);
    }

    @Override
    public String getName(){
        return "customarmor";
    }

    /**
     * getToughness: This is a second protection value where the armor is more durable against high value attacks. Value goes as 'X.0F'
     * @return toughness
     */
    @Override
    public float getToughness(){
        return 3.0f;
    }

    @Override
    public float getKnockbackResistance(){
        return 0.1f;
    }

}
