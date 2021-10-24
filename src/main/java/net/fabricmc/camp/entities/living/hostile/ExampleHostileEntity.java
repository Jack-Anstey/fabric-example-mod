package net.fabricmc.camp.entities.living.hostile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class ExampleHostileEntity extends PathAwareEntity {
    public ExampleHostileEntity(EntityType<? extends PathAwareEntity> entityType, World world){
        super(entityType, world);
    }
}
