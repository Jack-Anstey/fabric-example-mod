package net.fabricmc.example.blocks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.ModdedPlayer;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Stainable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.text.Text.Serializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BeaconEntity {//extends BlockEntity implements NamedScreenHandlerFactory {
    /*
    public static final StatusEffect[][] EFFECTS_BY_LEVEL;
    private static final Set EFFECTS;
    private List<BeamSegment> beamSegments = Lists.newArrayList();
    private List<BeamSegment> field_19178 = Lists.newArrayList();
    private int level;
    //private int field_19179 = -1;
    @Nullable
    private StatusEffect primary;
    @Nullable
    private StatusEffect secondary;
    @Nullable
    private Text customName;
    private ContainerLock lock;
    private final PropertyDelegate propertyDelegate;
    private boolean levelThree;
    private int minY;

    public BeaconEntity(BlockPos pos, BlockState state) {
        super(ExampleMod.BEACON_ENTITY, pos, state);
        this.lock = ContainerLock.EMPTY;
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch(index) {
                    case 0:
                        return BeaconEntity.this.level;
                    case 1:
                        return StatusEffect.getRawId(BeaconEntity.this.primary);
                    case 2:
                        return StatusEffect.getRawId(BeaconEntity.this.secondary);
                    case 3:
                        return BeaconEntity.this.levelThree ? 1 : 0;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0:
                        BeaconEntity.this.level = value;
                        break;
                    case 1:
                        if (!BeaconEntity.this.world.isClient && !BeaconEntity.this.beamSegments.isEmpty()) {
                            playSound(BeaconEntity.this.world, BeaconEntity.this.pos, SoundEvents.BLOCK_BEACON_POWER_SELECT);
                        }

                        BeaconEntity.this.primary = BeaconEntity.getPotionEffectById(value);
                        break;
                    case 2:
                        BeaconEntity.this.secondary = BeaconEntity.getPotionEffectById(value);
                        break;
                    case 3:
                        BeaconEntity.this.levelThree = value == 1;
                }

            }
            public int size() {
                return 4;
            }
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, BeaconEntity blockEntity) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos blockPos2;
        if (blockEntity.minY < j) {
            blockPos2 = pos;
            blockEntity.field_19178 = Lists.newArrayList();
            blockEntity.minY = blockPos2.getY() - 1;
        } else {
            blockPos2 = new BlockPos(i, blockEntity.minY + 1, k);
        }

        BeaconEntity.BeamSegment beamSegment = blockEntity.field_19178.isEmpty() ? null : (BeaconEntity.BeamSegment)blockEntity.field_19178.get(blockEntity.field_19178.size() - 1);
        int l = world.getTopY(Type.WORLD_SURFACE, i, k);
        int n;
        for(n = 0; n < 10 && blockPos2.getY() <= l; ++n) {
            BlockState blockState = world.getBlockState(blockPos2);
            Block block = blockState.getBlock();
            if (block instanceof Stainable) {
                float[] fs = ((Stainable)block).getColor().getColorComponents();
                if (blockEntity.field_19178.size() <= 1) {
                    beamSegment = new BeaconEntity.BeamSegment(fs);
                    blockEntity.field_19178.add(beamSegment);
                } else if (beamSegment != null) {
                    if (Arrays.equals(fs, beamSegment.color)) {
                        beamSegment.increaseHeight();
                    } else {
                        beamSegment = new BeaconEntity.BeamSegment(new float[]{(beamSegment.color[0] + fs[0]) / 2.0F, (beamSegment.color[1] + fs[1]) / 2.0F, (beamSegment.color[2] + fs[2]) / 2.0F});
                        blockEntity.field_19178.add(beamSegment);
                    }
                }
            } else {
                if (beamSegment == null || blockState.getOpacity(world, blockPos2) >= 15 && !blockState.isOf(Blocks.BEDROCK)) {
                    blockEntity.field_19178.clear();
                    blockEntity.minY = l;
                    break;
                }

                beamSegment.increaseHeight();
            }

            blockPos2 = blockPos2.up();
            ++blockEntity.minY;
        }

        n = blockEntity.level;
        if (world.getTime() % 80L == 0L) {
            if (!blockEntity.beamSegments.isEmpty()) {
                //blockEntity.level = updateLevel(world, i, j, k);
                blockEntity.updateLevel(i, j, k);
            }

            if (blockEntity.level > 0 && !blockEntity.beamSegments.isEmpty()) {
                blockEntity.applyPlayerEffects();
                playSound(world, pos, SoundEvents.BLOCK_BEACON_AMBIENT);
            }
        }

        if (blockEntity.minY >= l) {
            blockEntity.minY = world.getBottomY() - 1;
            boolean bl = n > 0;
            blockEntity.beamSegments = blockEntity.field_19178;
            if (!world.isClient) {
                boolean bl2 = blockEntity.level > 0;
                if (!bl && bl2) {
                    playSound(world, pos, SoundEvents.BLOCK_BEACON_ACTIVATE);
                    Iterator var14 = world.getNonSpectatingEntities(ServerPlayerEntity.class, (new Box((double)i, (double)j, (double)k, (double)i, (double)(j - 4), (double)k)).expand(10.0D, 5.0D, 10.0D)).iterator();
                    //    FOR ADVANCEMENTS    FIX LATER
                    //BeaconBlockEntity dummyBeacon = new BeaconBlockEntity(pos, state);
                    //((BeaconAdvancementMixin)dummyBeacon).setLevel(blockEntity.getLevel());
                    while(var14.hasNext()) {
                        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)var14.next();
                        Criteria.CONSTRUCT_BEACON.trigger(serverPlayerEntity, blockEntity.level);
                    }

                } else if (bl && !bl2) {
                    playSound(world, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE);
                }
            }
        }

    }

    // should be static, get around to that eventually
    private void updateLevel(int x, int y, int z) {
        this.level = 0;

        for(int i = 1; i <= 16; this.level = i++) {
            int j = y - i;
            if (j < world.getBottomY()) {
                break;
            }

            boolean bl = true;

            for(int k = x - i; k <= x + i && bl; ++k) {
                for(int l = z - i; l <= z + i; ++l) {
                    if (!this.world.getBlockState(new BlockPos(k, j, l)).isIn(BlockTags.BEACON_BASE_BLOCKS)) {
                        bl = false;
                        break;
                    }
                }
            }

            if (!bl) {
                break;
            }
        }

        if (this.level < 4) {
            this.secondary = null;
        }
        if (this.level < 10) {
            this.levelThree = false;
        }
        //   <my fix for effects remaining when beacon base broken>
        boolean effectAllowed = false;
        for (int o = 0; o <= (Math.min(this.level - 1, 8)); o++) {
            int p = EFFECTS_BY_LEVEL[o].length;
            for (int q = 0; q < p; q++) {
                if (EFFECTS_BY_LEVEL[o][q] == this.primary) {
                    effectAllowed = true;
                    break;
                }
            }
        }
        if (!effectAllowed) {
            this.primary = null;
            this.secondary = null;
            this.levelThree = false;
        }
        //   </my fix for effects remaining when beacon base broken>
    }

    public void markRemoved() {
        playSound(this.world, this.pos, SoundEvents.BLOCK_BEACON_DEACTIVATE);
        super.markRemoved();
    }

    private void applyPlayerEffects() {
        double d = (double)(this.level * 10 + 10);
        int j = (9 + this.level * 2) * 20;
        Box box = (new Box(this.pos)).expand(d).stretch(0.0D, (double)this.world.getHeight(), 0.0D);
        List<PlayerEntity> list = this.world.getNonSpectatingEntities(PlayerEntity.class, box);
        Iterator var7 = list.iterator();
        PlayerEntity playerEntity2;
        if (!this.world.isClient && this.primary != null) {
            int i = 0;
            if (this.level >= 4 && this.primary == this.secondary) {
                i = 1;
            }
            if (this.levelThree) {
                i = 2;
            }

            while(var7.hasNext()) {
                playerEntity2 = (PlayerEntity)var7.next();
                playerEntity2.addStatusEffect(new StatusEffectInstance(this.primary, j, i, true, true));
            }

            if (this.level >= 4 && this.primary != this.secondary && this.secondary != null) {
                var7 = list.iterator();

                while(var7.hasNext()) {
                    playerEntity2 = (PlayerEntity)var7.next();
                    playerEntity2.addStatusEffect(new StatusEffectInstance(this.secondary, j, 0, true, true));
                }
            }
        }
        if (this.level >= 16) {
            var7 = list.iterator();

            while (var7.hasNext()) {
                playerEntity2 = (PlayerEntity) var7.next();
                playerEntity2.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, j, 0, true, false));
                //playerEntity2.addStatusEffect(new StatusEffectInstance(ExampleMod.FLIGHT, j, 0, true, false));
                //og line
            }
        }
    }

    public static void playSound(World world, BlockPos pos, SoundEvent sound) {
        world.playSound((PlayerEntity)null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    @Environment(EnvType.CLIENT)
    public List<BeaconEntity.BeamSegment> getBeamSegments() {
        return (List)(this.level == 0 ? ImmutableList.of() : this.beamSegments);
    }

    public int getLevel() {
        return this.level;
    }

    @Nullable
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(this.pos, 3, this.toInitialChunkDataNbt());
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.writeNbt(new NbtCompound());
    }

    @Environment(EnvType.CLIENT)
    public double getSquaredRenderDistance() {
        return 256.0D;
    }

    @Nullable
    private static StatusEffect getPotionEffectById(int id) {
        StatusEffect statusEffect = StatusEffect.byRawId(id);
        return EFFECTS.contains(statusEffect) ? statusEffect : null;
    }

    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        this.primary = getPotionEffectById(tag.getInt("Primary"));
        this.secondary = getPotionEffectById(tag.getInt("Secondary"));
        this.levelThree = tag.getBoolean("LevelThree");
        if (tag.contains("CustomName", 8)) {
            this.customName = Serializer.fromJson(tag.getString("CustomName"));
        }

        this.lock = ContainerLock.fromNbt(tag);
    }

    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("Primary", StatusEffect.getRawId(this.primary));
        tag.putInt("Secondary", StatusEffect.getRawId(this.secondary));
        tag.putInt("Levels", this.level);
        tag.putBoolean("LevelThree", this.levelThree);
        if (this.customName != null) {
            tag.putString("CustomName", Serializer.toJson(this.customName));
        }

        this.lock.writeNbt(tag);
        return tag;
    }

    public void setCustomName(@Nullable Text text) {
        this.customName = text;
    }

    @Nullable
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        if (((ModdedPlayer)playerEntity).hasMod())
            return new BeaconHandler(syncId, playerInventory, this.propertyDelegate, ScreenHandlerContext.create(this.world, this.getPos()));
        return new BeaconScreenHandler(syncId, playerInventory, this.propertyDelegate, ScreenHandlerContext.create(this.world, this.getPos()));
    }

    public Text getDisplayName() {
        return (Text)(this.customName != null ? this.customName : new TranslatableText("container.beacon"));
    }

    public void setWorld(World world) {
        super.setWorld(world);
        this.minY = world.getBottomY() - 1;
    }

    static {
        EFFECTS_BY_LEVEL = new StatusEffect[][]{{StatusEffects.SPEED, StatusEffects.HASTE}, {StatusEffects.RESISTANCE, StatusEffects.JUMP_BOOST}, {StatusEffects.STRENGTH}, {StatusEffects.REGENERATION}, {StatusEffects.FIRE_RESISTANCE}, {StatusEffects.SATURATION}, {StatusEffects.ABSORPTION}, {StatusEffects.LUCK}, {StatusEffects.REGENERATION}};
        EFFECTS = Arrays.stream(EFFECTS_BY_LEVEL).flatMap(Arrays::stream).collect(Collectors.toSet());
    }

    public static class BeamSegment {
        private final float[] color;
        private int height;

        public BeamSegment(float[] color) {
            this.color = color;
            this.height = 1;
        }

        protected void increaseHeight() {
            ++this.height;
        }

        @Environment(EnvType.CLIENT)
        public float[] getColor() {
            return this.color;
        }

        @Environment(EnvType.CLIENT)
        public int getHeight() {
            return this.height;
        }
    }
    */
}