package net.fabricmc.example.items;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CustomToolMaterial implements ToolMaterial {

    //number of times you can hit
    private int durability = 500;

    //Speed at which things are mined. wood = 2.0, diamond = 8.0
    private float miningSpeedMultipler = 10.0f;

    //Attack damage
    private float attackDamage = 10.0f;

    //Mining level (3+ to mine obsidian)
    private int miningLevel = 4;

    //Higher the number, teh easier and the better the enchantments
    private int enchantability = 25;

    //Repair ingredient for the tool that uses the material
    private Ingredient ingredient = Ingredient.ofItems(Items.POTATO);

    //INSTANCE for ExampleMod so that the ToolMaterial can be referenced
    public static final CustomToolMaterial INSTANCE = new CustomToolMaterial();

    @Override
    public int getDurability(){
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier(){
        return miningSpeedMultipler;
    }

    @Override
    public float getAttackDamage(){
        return attackDamage;
    }

    @Override
    public int getMiningLevel(){
        return miningLevel;
    }

    @Override
    public int getEnchantability(){
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient(){
        return ingredient;
    }
    
}
