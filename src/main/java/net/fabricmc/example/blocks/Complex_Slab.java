package net.fabricmc.example.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Complex_Slab extends SlabBlock {

    public Complex_Slab(Settings settings) {
        super(settings);
    }

    /*
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.sendMessage(new LiteralText("Hello, world!"), false);
            //shoots the player into the sky lol
            player.addVelocity(0, 10, 0);
        }
        return ActionResult.SUCCESS;
    }
    */

    /*
    @Override
    public void onEntityWalk(World worldIn, BlockPos posIn, Entity entityIn){
        super.onEntityWalk(worldIn, posIn, entityIn);
        entityIn.addVelocity(0, 10, 0);
    }
    */

    /**
     * This method changes the shape of a block to a slab. Adjust the numbers to change the size of the slab
     * or to put it into a different form entirely
     * @param state the current state of the block
     * @param view the current view of the block
     * @param pos the block's position in the world
     * @param context the current context of the block
     * @return a VoxelShapes of the block
     *
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 0.5f);
    }
     */
}