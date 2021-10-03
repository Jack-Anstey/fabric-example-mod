package net.fabricmc.camp.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

@Environment(EnvType.CLIENT)
public class CustomArmor extends ArmorItem {
    public CustomArmor(ArmorMaterial material, EquipmentSlot slot){
        super(material, slot, new Item.Settings().group((ItemGroup.COMBAT)));
    }
}

