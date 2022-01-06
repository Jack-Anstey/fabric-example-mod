package net.fabricmc.camp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.camp.blocks.BlockModelExample;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class ExampleModClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        /**
         * Fluid Rendering
         */
        FluidRenderHandlerRegistry.INSTANCE.register(ExampleMod.STILL_JELLO, ExampleMod.FLOWING_JELLO, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0x15ff00 //lime green
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ExampleMod.STILL_JELLO, ExampleMod.FLOWING_JELLO);

        //if you want to use custom textures they need to be registered.
        //In this example this is unnecessary because the vanilla water textures are already registered.
        //To register your custom textures use this method.
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier("camp:block/jello_still"));
            registry.register(new Identifier("camp:block/jello_flowing"));
        });


        /**
         * Custom block creation
         */
        //BlockRenderLayerMap.INSTANCE.putBlock(ExampleMod.COMPLEX_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ExampleMod.COMPLEX_BLOCK, RenderLayer.getTranslucent());
        // Replace `RenderLayer.getCutout()` with `RenderLayer.getTranslucent()` if you have a translucent texture.


        /**
         * Block Models
         */
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ExampleModelProvider());
    }
}
