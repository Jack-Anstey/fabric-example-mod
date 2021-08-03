package net.fabricmc.example.blocks;

import com.google.common.collect.Lists;
import com.ibm.icu.impl.CharacterPropertiesImpl;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateBeaconC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class BeaconScreen {//extends HandledScreen<BeaconHandler> {
    /*
    private static final Identifier TEXTURE = new Identifier("bigbeacons","textures/gui/container/bigbeacon.png");
    private static final Text field_26560 = new TranslatableText("block.minecraft.beacon.primary");
    private static final Text field_26561 = new TranslatableText("block.minecraft.beacon.secondary");
    private static final Text field_26562 = new TranslatableText("block.minecraft.beacon.tertiary");
    //private BeaconScreen.DoneButtonWidget doneButton;
    private final List<BeaconScreen.ButtonInterface> buttons = Lists.newArrayList();
    //private boolean consumeGem;
    private StatusEffect primaryEffect;
    private StatusEffect secondaryEffect;
    private boolean levelThree;
    //private CharacterPropertiesImpl children;

    public BeaconScreen(final BeaconHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 230;
        this.backgroundHeight = 233;
        handler.addListener(new ScreenHandlerListener() {
            public void onHandlerRegistered(ScreenHandler handlerx, DefaultedList<ItemStack> stacks) {
            }

            public void onSlotUpdate(ScreenHandler handlerx, int slotId, ItemStack stack) {
            }

            public void onPropertyUpdate(ScreenHandler handlerx, int property, int value) {
                BeaconScreen.this.primaryEffect = handler.getPrimaryEffect();
                BeaconScreen.this.secondaryEffect = handler.getSecondaryEffect();
                BeaconScreen.this.levelThree = handler.getLevelThree();
                //BeaconScreen.this.consumeGem = true;
            }
        });
    }

    private <T extends ClickableWidget & BeaconScreen.ButtonInterface> void addButton(T clickableWidget) {
        this.addDrawableChild(clickableWidget);
        this.buttons.add((BeaconScreen.ButtonInterface)clickableWidget);
    }

    protected void init() {
        super.init();
        //this.doneButton = (BeaconScreen.DoneButtonWidget)addButton(new BeaconScreen.DoneButtonWidget(this.x + 61, this.y + 107));
        this.addButton(new BeaconScreen.DoneButtonWidget(this.x + 61, this.y + 107));
        this.addButton(new BeaconScreen.CancelButtonWidget(this.x + 87, this.y + 107));
        //this.consumeGem = true;
        //this.doneButton.active = false;
    }

    public void tick() {
        super.tick();
        int i = ((BeaconHandler)this.handler).getProperties();
        if (i >= 0) {
            //if (this.consumeGem && i >= 0) {
            //this.consumeGem = false;

            int o;
            int p;
            int q;
            StatusEffect statusEffect2;
            BeaconScreen.EffectButtonWidget effectButtonWidget2;
            for (int j = 0; j <= 7; ++j) {
                o = BeaconEntity.EFFECTS_BY_LEVEL[j].length;
                //p = o * 22 + (o - 1) * 2;
                p = 46;
                //primary buttons
                for (q = 0; q < o; ++q) {
                    statusEffect2 = BeaconEntity.EFFECTS_BY_LEVEL[j][q];
                    effectButtonWidget2 = new BeaconScreen.EffectButtonWidget(this.x + 45 + q * 24 - p / 2, this.y + 22 + j * 25, statusEffect2, 1);
                    this.addButton(effectButtonWidget2);
                    //if (j >= i || ((statusEffect2 == StatusEffects.ABSORPTION) && i < 6) || ((statusEffect2 == StatusEffects.FIRE_RESISTANCE || statusEffect2 == StatusEffects.REGENERATION) && i < 5)) {
                    if (j <= 2 ? j >= i : j >= i - 1) {
                        effectButtonWidget2.active = false;
                    } else if (statusEffect2 == this.primaryEffect) {
                        effectButtonWidget2.setDisabled(true);
                    }
                }
            }

            //int n = true;
            o = BeaconEntity.EFFECTS_BY_LEVEL[8].length + 1;
            p = o * 22 + (o - 1) * 2;
            //left secondary button (regen)
            for (q = 0; q < o - 1; ++q) {
                statusEffect2 = BeaconEntity.EFFECTS_BY_LEVEL[8][q];
                effectButtonWidget2 = new BeaconScreen.EffectButtonWidget(this.x + 167 + q * 24 - p / 2, this.y + 47, statusEffect2, 2);
                this.addButton(effectButtonWidget2);
                if (3 >= i) {
                    effectButtonWidget2.active = false;
                } else if (statusEffect2 == this.secondaryEffect) {
                    effectButtonWidget2.setDisabled(true);
                }
            }
            //right secondary button
            if (this.primaryEffect != null && this.primaryEffect != StatusEffects.REGENERATION && this.primaryEffect != StatusEffects.FIRE_RESISTANCE) {
                BeaconScreen.EffectButtonWidget effectButtonWidget3 = new BeaconScreen.EffectButtonWidget(this.x + 167 + (o - 1) * 24 - p / 2, this.y + 47, this.primaryEffect, 2);
                this.addButton(effectButtonWidget3);
                if (3 >= i || (((this.primaryEffect == StatusEffects.ABSORPTION) && i < 8) || ((this.primaryEffect == StatusEffects.SATURATION) && i < 7))) {
                    effectButtonWidget3.active = false;
                } else if (this.primaryEffect == this.secondaryEffect) {
                    effectButtonWidget3.setDisabled(true);
                }
            }

            // <THREE>
            if (this.primaryEffect != null && this.primaryEffect != StatusEffects.FIRE_RESISTANCE) {
                StatusEffect statusEffect3 = this.primaryEffect;
                effectButtonWidget2 = new BeaconScreen.EffectButtonWidget(this.x + 157, this.y + 100, statusEffect3, 3);
                this.addButton(effectButtonWidget2);
                if (this.primaryEffect != this.secondaryEffect || i < 10) {
                    effectButtonWidget2.active = false;
                } else if (this.levelThree) {
                    effectButtonWidget2.setDisabled(true);
                }
            }
            // </THREE>
        }

        //this.doneButton.active = ((BeaconHandler)this.handler).hasPayment() && this.primaryEffect != null;
    }

    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        drawCenteredText(matrices, this.textRenderer, field_26560, 62, 10, 14737632);
        drawCenteredText(matrices, this.textRenderer, field_26561, 169, 10, 14737632);
        drawCenteredText(matrices, this.textRenderer, field_26562, 169, 84, 14737632);

        for (int i = 1; i < 9; i++) {
            drawCenteredText(matrices, this.textRenderer, Text.of("" + (i <= 3 ? i : i + 1)), 16, 29 + (i - 1) * 25, 14737632);
        }
        drawCenteredText(matrices, this.textRenderer, Text.of("" + 4), 138, 54, 14737632);
        drawCenteredText(matrices, this.textRenderer, Text.of("" + 10), 149, 108, 14737632);




        Iterator var4 = this.buttons.iterator();

        while(var4.hasNext()) {
            BeaconScreen.ButtonInterface abstractButtonWidget = (BeaconScreen.ButtonInterface)var4.next();
            if (abstractButtonWidget.method_37079()) {
                abstractButtonWidget.renderToolTip(matrices, mouseX - this.x, mouseY - this.y);
                break;
            }
        }

    }
    */
    /*
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.itemRenderer.zOffset = 100.0F;
        i+=100;
        this.itemRenderer.zOffset = 0.0F;

    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }


    @Environment(EnvType.CLIENT)
    interface ButtonInterface {
        boolean method_37079();

        void renderToolTip(MatrixStack matrices, int mouseX, int mouseY);

        void method_37080(int i);
    }

    @Environment(EnvType.CLIENT)
    class CancelButtonWidget extends BeaconScreen.IconButtonWidget {
        public CancelButtonWidget(int x, int y) {
            super(x, y, 112, 234);
        }

        public void onPress() {
            BeaconScreen.this.client.player.networkHandler.sendPacket(new CloseHandledScreenC2SPacket(BeaconScreen.this.client.player.currentScreenHandler.syncId));
            BeaconScreen.this.client.openScreen((Screen)null);
        }

        public void renderToolTip(MatrixStack matrices, int mouseX, int mouseY) {
            BeaconScreen.this.renderTooltip(matrices, ScreenTexts.CANCEL, mouseX, mouseY);
        }

        @Override
        public void method_37080(int i) {

        }

        @Override
        public void appendNarrations(NarrationMessageBuilder builder) {

        }
    }

    @Environment(EnvType.CLIENT)
    class DoneButtonWidget extends BeaconScreen.IconButtonWidget {
        public DoneButtonWidget(int x, int y) {
            super(x, y, 90, 234);
        }

        public void onPress() {
            //BeaconScreen.this.handler.setEffects(StatusEffect.getRawId(BeaconScreen.this.primaryEffect), StatusEffect.getRawId(BeaconScreen.this.secondaryEffect));
            BeaconScreen.this.client.getNetworkHandler().sendPacket(new UpdateBeaconC2SPacket(StatusEffect.getRawId(BeaconScreen.this.primaryEffect) + (BeaconScreen.this.levelThree ? 1000 : 0), StatusEffect.getRawId(BeaconScreen.this.secondaryEffect)));
            BeaconScreen.this.client.player.networkHandler.sendPacket(new CloseHandledScreenC2SPacket(BeaconScreen.this.client.player.currentScreenHandler.syncId));
            BeaconScreen.this.client.openScreen((Screen)null);
            //System.out.println("Done Button Pressed");
        }

        public void renderToolTip(MatrixStack matrices, int mouseX, int mouseY) {
            BeaconScreen.this.renderTooltip(matrices, ScreenTexts.DONE, mouseX, mouseY);
        }

        @Override
        public void method_37080(int i) {

        }

        @Override
        public void appendNarrations(NarrationMessageBuilder builder) {

        }
    }

    @Environment(EnvType.CLIENT)
    abstract static class IconButtonWidget extends BeaconScreen.BaseButtonWidget {
        private final int u;
        private final int v;

        protected IconButtonWidget(int x, int y, int u, int v) {
            super(x, y);
            this.u = u;
            this.v = v;
        }

        protected void renderExtra(MatrixStack matrixStack) {
            this.drawTexture(matrixStack, this.x + 2, this.y + 2, this.u, this.v, 18, 18);
        }
    }

    @Environment(EnvType.CLIENT)
    class EffectButtonWidget extends BeaconScreen.BaseButtonWidget {
        private final StatusEffect effect;
        private final Sprite sprite;
        private final int level;
        private final Text hoverText;

        public EffectButtonWidget(int x, int y, StatusEffect statusEffect, int level) {
            super(x, y);
            this.effect = statusEffect;
            this.sprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(statusEffect);
            this.level = level;
            this.hoverText = this.getHoverText(statusEffect, level);
        }

        private Text getHoverText(StatusEffect statusEffect, int level) {
            MutableText mutableText = new TranslatableText(statusEffect.getTranslationKey());
            if (level == 2 && ((statusEffect != StatusEffects.REGENERATION)||(BeaconScreen.this.primaryEffect == StatusEffects.REGENERATION))) {
                mutableText.append(" II");
            } else if (level == 3) {
                mutableText.append(" III");
            }

            return mutableText;
        }

        public void onPress() {
            if (!this.isDisabled()) {
                if (this.level == 1) {
                    BeaconScreen.this.levelThree = false;
                    BeaconScreen.this.primaryEffect = this.effect;
                    //BeaconScreen.this.handler.setLevelThree(false);
                } else if (this.level == 2) {
                    BeaconScreen.this.levelThree = false;
                    BeaconScreen.this.secondaryEffect = this.effect;
                    //BeaconScreen.this.handler.setLevelThree(false);
                } else {
                    BeaconScreen.this.levelThree = true;
                    //BeaconScreen.this.handler.setLevelThree(true);
                }

                BeaconScreen.this.buttons.clear();
                BeaconScreen.this.children().clear();
                BeaconScreen.this.init();
                BeaconScreen.this.tick();
            }
        }

        public void renderToolTip(MatrixStack matrices, int mouseX, int mouseY) {
            BeaconScreen.this.renderTooltip(matrices, this.hoverText, mouseX, mouseY);
        }

        @Override
        public void method_37080(int i) {

        }

        protected void renderExtra(MatrixStack matrixStack) {
            RenderSystem.setShaderTexture(0, this.sprite.getAtlas().getId());
            drawSprite(matrixStack, this.x + 2, this.y + 2, this.getZOffset(), 18, 18, this.sprite);
        }

        @Override
        public void appendNarrations(NarrationMessageBuilder builder) {

        }
    }

    @Environment(EnvType.CLIENT)
    abstract static class BaseButtonWidget extends PressableWidget implements BeaconScreen.ButtonInterface {
        private boolean disabled;

        protected BaseButtonWidget(int x, int y) {
            super(x, y, 22, 22, LiteralText.EMPTY);
        }

        public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, BeaconScreen.TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            //int i = true;
            int j = 0;
            if (!this.active) {
                j += this.width * 2;
            } else if (this.disabled) {
                j += this.width * 1;
            } else if (this.isHovered()) {
                j += this.width * 3;
            }

            this.drawTexture(matrices, this.x, this.y, j, 233, this.width, this.height);
            this.renderExtra(matrices);
        }

        protected abstract void renderExtra(MatrixStack matrixStack);

        public boolean isDisabled() {
            return this.disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public boolean method_37079() {
            return this.hovered;
        }
    }
    */
}