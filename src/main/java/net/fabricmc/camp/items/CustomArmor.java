package net.fabricmc.camp.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

@Environment(EnvType.CLIENT)
public class CustomArmor extends ArmorItem {
    public CustomArmor(ArmorMaterial material, EquipmentSlot slot){
        super(material, slot, new Settings().group((ItemGroup.COMBAT)));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("item.camp.custom_armor.tooltip").formatted(Formatting.GREEN));
    }
}

