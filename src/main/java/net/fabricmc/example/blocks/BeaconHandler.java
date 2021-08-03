package net.fabricmc.example.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.ExampleMod;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.tag.ItemTags;
import org.jetbrains.annotations.Nullable;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;

public class BeaconHandler { //extends ScreenHandler {
    /*
    //private final Inventory payment;
    //private final BeaconHandler.PaymentSlot paymentSlot;
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;

    public BeaconHandler(int syncId, Inventory inventory) {
        this(syncId, inventory, new ArrayPropertyDelegate(4), ScreenHandlerContext.EMPTY);
    }

    public BeaconHandler(int syncId, Inventory inventory, PropertyDelegate propertyDelegate, ScreenHandlerContext context) {
        super(ExampleMod.BEACON_HANDLER, syncId);

        checkDataCount(propertyDelegate, 4);
        this.propertyDelegate = propertyDelegate;
        this.context = context;
        //this.paymentSlot = new BeaconHandler.PaymentSlot(this.payment, 0, 136, 110);
        //this.addSlot(this.paymentSlot);
        this.addProperties(propertyDelegate);
        //int i = true;
        //int j = true;


        int m;
        int z = 7;  // inventory y-centering
        for(m = 0; m < 3; ++m) {
            for(int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inventory, l + m * 9 + 9, 62 + l * 18, 137 + m * 18 + z));
            }
        }

        for(m = 0; m < 9; ++m) {
            this.addSlot(new Slot(inventory, m, 62 + m * 18, 195 + z));
        }

    }

    public void close(PlayerEntity player) {
        super.close(player);

    }

    //was overridden
    public boolean canUse(PlayerEntity player) {
        //return canUse(this.context, player, Blocks.BEACON);
        return true;
    }

    public void setProperty(int id, int value) {
        super.setProperty(id, value);
        this.sendContentUpdates();
    }
    */
    /*
    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 0) {
                if (!this.insertItem(itemStack2, 1, 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(itemStack2, itemStack);
            } else if (index >= 1 && index < 28) {
                if (!this.insertItem(itemStack2, 28, 36, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 28 && index < 36) {
                if (!this.insertItem(itemStack2, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 1, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    @Environment(EnvType.CLIENT)
    public int getProperties() {
        return this.propertyDelegate.get(0);
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    public StatusEffect getPrimaryEffect() {
        return StatusEffect.byRawId(this.propertyDelegate.get(1));
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    public StatusEffect getSecondaryEffect() {
        return StatusEffect.byRawId(this.propertyDelegate.get(2));
    }

    @Environment(EnvType.CLIENT)
    public boolean getLevelThree() {
        return this.propertyDelegate.get(3) == 1;
    }

    public void setLevelThree(boolean three) {
        this.propertyDelegate.set(3, three ? 1 : 0);
    }

    public void setEffects(int primaryEffectId, int secondaryEffectId) {
        //if (this.paymentSlot.hasStack()) {
        if (primaryEffectId - 1000 > 0) {
            primaryEffectId -= 1000;
            this.propertyDelegate.set(3, 1);
        }
        this.propertyDelegate.set(1, primaryEffectId);
        this.propertyDelegate.set(2, secondaryEffectId);
        //this.paymentSlot.takeStack(1);
        //}

    }

    @Environment(EnvType.CLIENT)
    public boolean hasPayment() {
        //return !this.payment.getStack(0).isEmpty();
        return true;
    }
    */
}