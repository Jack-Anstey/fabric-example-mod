package net.fabricmc.example.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CustomArmor extends ArmorItem {
    public CustomArmor(ArmorMaterial material, EquipmentSlot slot){
        super(material, slot, new Item.Settings().group((ItemGroup.COMBAT)));
    }

    /*
    @Override
    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player){
        boolean hasAllArmorWorn = true;
        for (int i = 0; i < 4; i++) {
            if (!(player.inventory.armorItemInSlot(i).getItem() instanceof CoffeeCatArmor)) {
                hasAllArmorWorn=false;
            }
        }
        if (hasAllArmorWorn){
            player.addPotionEffect(new EffectInstance(Effects.SPEED,100,1));
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION,100,1));
            //Add more if you want
        }
    }
    */
}
