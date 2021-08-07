package net.fabricmc.example.interfaces;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface ArmorCallBack {
    Event<ArmorCallBack> EVENT = EventFactory.createArrayBacked(ArmorCallBack.class,
            (listeners) -> (player, sheep) -> {
                for (ArmorCallBack listener : listeners) {
                    ActionResult result = listener.interact(player, sheep);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(PlayerEntity player, SheepEntity sheep);
}
