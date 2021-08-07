package net.fabricmc.example.items;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class CustomArmor extends ArmorItem implements ClientModInitializer {
    public CustomArmor(ArmorMaterial material, EquipmentSlot slot){
        super(material, slot, new Item.Settings().group((ItemGroup.COMBAT)));
    }

    /**
     * All of this stuff is for ticks to make it so that we can constantly check to see
     * if the player is wearing all four pieces of armor
     */
    private final Map<RegistryKey<World>, Integer> tickTracker = new HashMap<>();
    private int ticks;
    @Override
    public void onInitializeClient(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            this.ticks++; //this is just to track ticks since minecraft doesn't do so for us
        });

        ClientTickEvents.END_WORLD_TICK.register(world -> {
            //This is what should track the world
            boolean hasAllArmorWorn = true;
            PlayerEntity player = world.getClosestPlayer(1, 1, 1, 1, false);
            for (int i = 0; i < 4; i++) {
                if (!(player.getInventory().getArmorStack(i).getItem() instanceof CustomArmor)) {
                    hasAllArmorWorn=false;
                }
            }
            if (hasAllArmorWorn){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED));
                //Add more if you want
            }
            ServerLifecycleTests.LOGGER.info("Ticked END WORLD TICK at " + this.ticks + " ticks.");
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED));
        });

        ClientTickEvents.START_WORLD_TICK.register(world -> {
            //This is what should track the world
            boolean hasAllArmorWorn = true;
            PlayerEntity player = world.getClosestPlayer(1, 1, 1, 1, false);
            for (int i = 0; i < 4; i++) {
                if (!(player.getInventory().getArmorStack(i).getItem() instanceof CustomArmor)) {
                    hasAllArmorWorn=false;
                }
            }
            if (hasAllArmorWorn){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED));
                //Add more if you want
            }
            ServerLifecycleTests.LOGGER.info("Ticked START WORLD TICK at " + this.ticks + " ticks.");
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED));
        });
    }
}

