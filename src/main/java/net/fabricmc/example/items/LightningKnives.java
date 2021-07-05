package net.fabricmc.example.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LightningKnives extends Item {

    /* TODO: learn to see if there is a on right click type of method that
        can be used so I can make lightning and explosions based on where the user clicks*/

    public LightningKnives(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        playerEntity.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

}
