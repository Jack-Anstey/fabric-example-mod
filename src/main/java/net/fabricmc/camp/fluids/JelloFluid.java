package net.fabricmc.camp.fluids;

import net.fabricmc.camp.ExampleMod;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class JelloFluid extends CustomFluid{
    @Override
    public Fluid getStill() {
        return ExampleMod.STILL_JELLO;
    }

    @Override
    public Fluid getFlowing() {
        return ExampleMod.FLOWING_JELLO;
    }

    @Override
    public Item getBucketItem() {
        return ExampleMod.JELLO_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return ExampleMod.JELLO.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends JelloFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends JelloFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
