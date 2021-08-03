package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.blocks.*;
import net.fabricmc.example.enchantments.CustomEnchantment;
import net.fabricmc.example.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
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

	//Blocks (JSON MODEL NEEDED FOR BOTH BLOCK AND ITEM FOLDER)
	public static final Block BASIC_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(0.4f));
	public static final Complex_Slab COMPLEX_SLAB = new Complex_Slab(FabricBlockSettings.of(Material.STONE).hardness(4.0f));
	public static final Complex_Block COMPLEX_BLOCK = new Complex_Block(FabricBlockSettings.of(Material.STONE).hardness(4.0f));

	//Armor
	public static final ArmorMaterial CUSTOM_MATERIAL = new CustomArmorMaterial();

	/**
	 * This stuff is for beacons!

	public static BlockEntityType<BeaconEntity> BEACON_ENTITY;
	public static final ScreenHandlerType<BeaconHandler> BEACON_HANDLER;

	//public static final StatusEffect FLIGHT = new FlightEffect();

	public static final String MOD_ID = "bigbeacons";
	public static final Identifier BIGBEACON = new Identifier(MOD_ID, "beacon");

	static {
		BEACON_HANDLER = ScreenHandlerRegistry.registerSimple(BIGBEACON, BeaconHandler::new);
		//BIG_BEACON = Registry.register(Registry.BLOCK, BIGBEACON, new Beacon(FabricBlockSettings.of(Material.GLASS, MaterialColor.DIAMOND).hardness(3.0f).luminance(15).solidBlock(BigBeacons::never)));
		//BEACON_ITEM = Registry.register(Registry.ITEM, BIGBEACON, new BlockItem(BIG_BEACON, new Item.Settings().group(ItemGroup.MISC).rarity(Rarity.RARE)));
		BEACON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BIGBEACON, FabricBlockEntityTypeBuilder.create(BeaconEntity::new, Blocks.BEACON).build());
		//BlockRenderLayerMap.INSTANCE.putBlock(BigBeacons.BIG_BEACON, RenderLayer.getCutout());
	}
	//public static final CustomBeacon CUSTOM_BEACON = new CustomBeacon(FabricBlockSettings.of(Material.METAL).hardness(1.0f));
	*/

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//Register Armor
		Registry.register(Registry.ITEM, new Identifier("camp", "customhelm"), new CustomArmor(CUSTOM_MATERIAL, EquipmentSlot.HEAD));
		Registry.register(Registry.ITEM, new Identifier("camp", "customchest"), new CustomArmor(CUSTOM_MATERIAL, EquipmentSlot.CHEST));
		Registry.register(Registry.ITEM, new Identifier("camp", "customlegs"), new CustomArmor(CUSTOM_MATERIAL, EquipmentSlot.LEGS));
		Registry.register(Registry.ITEM, new Identifier("camp", "customboots"), new CustomArmor(CUSTOM_MATERIAL, EquipmentSlot.FEET));

		//Register Blocks
		Registry.register(Registry.BLOCK, new Identifier("camp", "basic_block"), BASIC_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("camp", "basic_block"), new BlockItem(BASIC_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));

		Registry.register(Registry.BLOCK, new Identifier("camp", "complex_slab"), COMPLEX_SLAB);
		Registry.register(Registry.ITEM, new Identifier("camp", "complex_slab"), new BlockItem(COMPLEX_SLAB, new FabricItemSettings().group(ItemGroup.MISC)));

		Registry.register(Registry.BLOCK, new Identifier("camp", "complex_block"), COMPLEX_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("camp", "complex_block"), new BlockItem(COMPLEX_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));


		//Register Items
		Registry.register(Registry.ITEM, new Identifier("camp", "basic_item"), BASIC_ITEM);
		Registry.register(Registry.ITEM, new Identifier("camp", "complex_item"), COMPLEX_ITEM);
		Registry.register(Registry.ITEM, new Identifier("camp", "lightning_knives"), LIGHTNING_KNIVES);
		Registry.register(Registry.ITEM, new Identifier("camp", "custom_pickaxe"), CUSTOM_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("camp", "custom_sword"), CUSTOM_SWORD);

		/*
		//Register Beacons
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("custombeacon", "c2s-acknowledge"), (server, player, handler, buf, responseSender) -> {
			((ModdedPlayer)player).setHasMod(true);
		});
		*/
	}
}
