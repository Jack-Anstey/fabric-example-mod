package net.fabricmc.camp.items;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class CustomPickaxe extends PickaxeItem {

    public CustomPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings){
        super(material, attackDamage, attackSpeed, settings);
    }
}
