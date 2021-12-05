package net.fabricmc.camp.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;

@Environment(EnvType.CLIENT)
public class CustomArmor extends ArmorItem {
    public CustomArmor(ArmorMaterial material, EquipmentSlot slot){
        super(material, slot, new Settings().group((ItemGroup.COMBAT)));
    }
}

