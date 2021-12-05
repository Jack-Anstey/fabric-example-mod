package net.fabricmc.camp.entities.living.hostile;

import net.fabricmc.camp.entities.EntityTestingClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ExampleHostileEntityRenderer extends MobEntityRenderer<ExampleHostileEntity, ExampleHostileEntityModel> {
    public ExampleHostileEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ExampleHostileEntityModel(context.getPart(EntityTestingClient.MODEL_EXAMPLEHOSTILEENTITY_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ExampleHostileEntity entity) {
        return new Identifier("camp", "textures/entity/examplehostileentity/enemy.png");
    }
}
