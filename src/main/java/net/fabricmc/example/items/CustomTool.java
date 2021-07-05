package net.fabricmc.example.items;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

import java.util.Properties;

public class CustomTool extends PickaxeItem {

    public CustomTool(ToolMaterial material,int attackDamage, float attackSpeed, Settings settings){
        super(material, attackDamage, attackSpeed, settings);
    }
}
