package net.fabricmc.camp.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Complex_Block extends Block {

    /**
     * Constructor for the Block
     * @param settings
     */
    public Complex_Block(Settings settings){
        super(settings);
    }

    /**
     * Constructor for using transparent textures
     */
    public Complex_Block(){
        super(Settings.of(Material.STONE).nonOpaque().hardness(4.0f));
    }

    /**
     * Runs when an entity walks on this block (does not work when a slab is combined to make this block)
     * @param world world that the block is in
     * @param pos the position that the block is in
     * @param state the state that the block is in
     * @param entity the entity that is currently stepping on the block
     */
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity){
       entity.addVelocity(0, 1, 0);
    }

    /*
    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.INVISIBLE;
    }
     */
}

