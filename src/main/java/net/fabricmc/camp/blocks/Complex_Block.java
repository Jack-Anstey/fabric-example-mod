package net.fabricmc.camp.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Complex_Block extends Block {

    /**
     * Contructor for the Block
     * @param settings
     */
    public Complex_Block(Settings settings){
        super(settings);
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

}

