package net.fabricmc.camp.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class RegeneratingTotem extends Item{

    private int currentHealth = 10; //TODO make this actually work
    //TODO learn to see if you can have the image in your inventory be different than when its in your hand
    //TODO Answer: I do not think it is possible since its an item. If it were a block, we could do this :/

    public RegeneratingTotem(Settings settings){
        super(settings);
    }

    //https://fabricmc.net/wiki/tutorial:tooltip
    //https://fabricmc.net/wiki/tutorial:lang
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        // default white text

       // currentHealth = this.getMaxDamage();

        //boolean damageAble = this.isDamageable();
        //tooltip.add( new TranslatableText("item.camp.regen_totem.tooltip", damageAble));

        // formatted red text
        //tooltip.add( new TranslatableText("item.camp.regen_totem.tooltipred").formatted(Formatting.RED));

        // formatted red text alt from default
        tooltip.add( new TranslatableText("item.camp.regen_totem.tooltip", currentHealth).formatted(Formatting.GREEN));


    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 150));

        if(currentHealth <= 0){
            playerEntity.getStackInHand(hand).decrement(1);
            currentHealth = 10;
        } else{
            currentHealth--;
        }
        //playerEntity.getStackInHand(hand).decrement(1);
        //playerEntity.getStackInHand(hand).usageTick(world, playerEntity, currentHealth--);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
