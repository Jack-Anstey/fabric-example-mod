package net.fabricmc.example.items;

import net.fabricmc.example.UtilityFunctions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningKnives extends Item {

    public LightningKnives(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        BlockPos blockPos = UtilityFunctions.getBlockAtCursor(playerEntity, 20.0d, true);
        if(blockPos!=null){
            playerEntity.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
            UtilityFunctions.createMultipleStrikes(world, blockPos, 4, 10);

            //TODO: this is causing some character clipping issues but it still "works" so I'm keeping it for now
            UtilityFunctions.createExplosion(world, blockPos, playerEntity,3.0f);
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

}
