package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.enchantments.CustomEnchantment;
import net.fabricmc.example.items.ComplexItem;
import net.fabricmc.example.items.CustomPickaxe;
import net.fabricmc.example.items.CustomToolMaterial;
import net.fabricmc.example.items.LightningKnives;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ExampleMod implements ModInitializer {

	/*This is where instances of items are created
	* Organized by comments and not much else*/

	//Basic Items
	public static final Item BASIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	//Complex Items
	public static final ComplexItem COMPLEX_ITEM = new ComplexItem(new FabricItemSettings().group(ItemGroup.MISC));
	public static final LightningKnives LIGHTNING_KNIVES = new LightningKnives(new FabricItemSettings().group(ItemGroup.MISC));

	//Tools & Weapons
	public static ToolItem CUSTOM_SWORD = new SwordItem(CustomToolMaterial.INSTANCE, 301, 20, new Item.Settings().group(ItemGroup.COMBAT));
	//everything but Swords and shovels are protected so you must write them with a separate class like this
	public static ToolItem CUSTOM_PICKAXE = new CustomPickaxe(CustomToolMaterial.INSTANCE, 10, -3f, new Item.Settings().group(ItemGroup.TOOLS));

	//Enchantments
	public static Enchantment FROST = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("camp", "frost"),
			new CustomEnchantment()
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//Register Items
		Registry.register(Registry.ITEM, new Identifier("camp", "basic_item"), BASIC_ITEM);
		Registry.register(Registry.ITEM, new Identifier("camp", "complex_item"), COMPLEX_ITEM);
		Registry.register(Registry.ITEM, new Identifier("camp", "lightning_knives"), LIGHTNING_KNIVES);
		Registry.register(Registry.ITEM, new Identifier("camp", "custom_pickaxe"), CUSTOM_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("camp", "custom_sword"), CUSTOM_SWORD);
	}
}
