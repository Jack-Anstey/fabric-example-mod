package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.items.ComplexItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ExampleMod implements ModInitializer {

	//this is where instances of items are created
	public static final Item BASIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final ComplexItem COMPLEX_ITEM = new ComplexItem(new FabricItemSettings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		//Register Items
		Registry.register(Registry.ITEM, new Identifier("camp", "basic_item"), BASIC_ITEM);

		Registry.register(Registry.ITEM, new Identifier("camp", "complex_item"), COMPLEX_ITEM);

	}
}
