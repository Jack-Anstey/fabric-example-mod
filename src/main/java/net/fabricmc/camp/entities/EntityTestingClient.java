package net.fabricmc.camp.entities;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.camp.entities.living.hostile.ExampleHostileEntityModel;
import net.fabricmc.camp.entities.living.hostile.ExampleHostileEntityRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_EXAMPLEHOSTILEENTITY_LAYER = new EntityModelLayer(new Identifier("camp", "examplehostileentity"), "main");
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        EntityRendererRegistry.INSTANCE.register(EntityTesting.exampleHostileEntityEntityType, ExampleHostileEntityRenderer::new);
        // In 1.17, use EntityRendererRegistry.register (seen below) instead of EntityRendererRegistry.INSTANCE.register (seen above)
//        EntityRendererRegistry.register(EntityTesting.exampleHostileEntityEntityType, (context) -> {
//            return new ExampleHostileEntityRenderer(context);
//        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_EXAMPLEHOSTILEENTITY_LAYER, ExampleHostileEntityModel::getTexturedModelData);
    }
}