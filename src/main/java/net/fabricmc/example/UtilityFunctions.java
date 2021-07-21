package net.fabricmc.example;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Random;

public class UtilityFunctions {

    /**
     * This method finds the block at which the character is looking at within a given distance
     * @param player PlayerEntity to find where they are looking
     * @param distance maximum distance at which a block will be considered to be looked at
     * @param ignoreFluids ignore or look through water when checking for blocks
     * @return the BlockPos that the character is looking at. NOTE: this can return NULL
     */
    public static BlockPos getBlockAtCursor(PlayerEntity player, double distance, boolean ignoreFluids){
        RaycastContext.FluidHandling fluidMode = ignoreFluids ? RaycastContext.FluidHandling.NONE : RaycastContext.FluidHandling.ANY;

        RaycastContext raycastContext = new RaycastContext(player.getEyePos(), player.getEyePos().add(player.getRotationVector().multiply(distance)), RaycastContext.ShapeType.COLLIDER, fluidMode, player);
        BlockHitResult blockHit = player.getEntityWorld().raycast(raycastContext);
        if(blockHit.getType() == HitResult.Type.MISS){
            return null;
        }
        return blockHit.getBlockPos();
    }

    /**
     * Creates a lightning strike at a given BlockPos
     * @param world the world in which the lightning will be created
     * @param blockPos the position where the lightning will strike
     */
    public static void lightningStrike(World world, BlockPos blockPos){
        if (world instanceof ServerWorld serverWorld) {
            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(serverWorld);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            serverWorld.spawnEntity(lightningEntity);
        }
    }

    /**
     * Create multiple strikes around a given BlockPos
     * @param world The world in which the lightning is made
     * @param blockPos the position in which the lightning is centered on
     * @param numberOfStrikes controls the number of strikes made. Minimum of one
     * @param width distance in which lightning can randomly strike
     */
    public static void createMultipleStrikes(World world, BlockPos blockPos, int numberOfStrikes, int width){
        Random rand = new Random();
        int randomX;
        int randomY;
        int randomZ;
        if(numberOfStrikes < 1){
            numberOfStrikes = 1;
        }

        lightningStrike(world, blockPos);
        for(int times = 1; times < numberOfStrikes; times++){
            randomX = rand.nextInt(2*width)-width;
            randomY = rand.nextInt(2*width)-width;
            randomZ = rand.nextInt(2*width)-width;
            BlockPos randomBlockPos = new BlockPos(blockPos.getX()+randomX, blockPos.getY()+randomY, blockPos.getZ()+randomZ);
            lightningStrike(world, randomBlockPos);
        }
    }

    /**
     * Makes an explosion for a given location and is a given size
     * @param world world in which the explosion is made
     * @param blockPos the position in the world where the explosion is centered
     * @param explosionRadius the size of the explosion (bigger is larger)
     */
    public static void createExplosion(World world, BlockPos blockPos, float explosionRadius){
        world.createExplosion(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), explosionRadius, Explosion.DestructionType.DESTROY);
    }

}
