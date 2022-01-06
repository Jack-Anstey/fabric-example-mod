package net.fabricmc.camp;

import net.fabricmc.camp.blocks.BlockModelExample;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;

public class ExampleModelProvider implements ModelResourceProvider {
    public static final BlockModelExample FOUR_SIDED_FURNACE_MODEL = new BlockModelExample();
    public static final Identifier FOUR_SIDED_FURNACE_MODEL_BLOCK = new Identifier("camp:block/four_sided_furnace");
    public static final Identifier FOUR_SIDED_FURNACE_MODEL_ITEM = new Identifier("camp:item/four_sided_furnace");

    @Override
    public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) throws ModelProviderException {
        if(identifier.equals(FOUR_SIDED_FURNACE_MODEL_BLOCK) || identifier.equals(FOUR_SIDED_FURNACE_MODEL_ITEM)) {
            return FOUR_SIDED_FURNACE_MODEL;
        } else {
            return null;
        }
    }
}
