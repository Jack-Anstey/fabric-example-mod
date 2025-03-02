package net.fabricmc.camp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.camp.blocks.BlockModelExample;
import net.fabricmc.camp.blocks.Complex_Block;
import net.fabricmc.camp.blocks.Complex_Slab;
import net.fabricmc.camp.enchantments.CustomEnchantment;
import net.fabricmc.camp.entities.EntityTesting;
import net.fabricmc.camp.fluids.JelloFluid;
import net.fabricmc.camp.items.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

import java.util.List;


public class ExampleMod implements ModInitializer {

	//Recipes website --> https://crafting.thedestruc7i0n.ca/

	/*This is where instances of items are created
	* Organized by comments and not much else*/

	//Basic Items
	public static final Item BASIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	//Complex Items
	public static final ComplexItem COMPLEX_ITEM = new ComplexItem(new FabricItemSettings().group(ItemGroup.MISC));
	public static final LightningKnives LIGHTNING_KNIVES = new LightningKnives(new FabricItemSettings().group(ItemGroup.MISC));
	public static final RegeneratingTotem REGENERATING_TOTEM = new RegeneratingTotem(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1).maxDamageIfAbsent(13).maxDamage(13).rarity(Rarity.EPIC)); //adjust the max stack size
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
	//public static final Complex_Block COMPLEX_BLOCK = new Complex_Block(FabricBlockSettings.of(Material.STONE).hardness(4.0f));
	public static final Complex_Block COMPLEX_BLOCK = new Complex_Block(); //for the translucent texture
	public static final Block FOUR_SIDED_FURNACE = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f).requiresTool());

	//Armor
	public static final ArmorMaterial CUSTOM_MATERIAL = new CustomArmorMaterial();

	//Spawn eggs
	public static final Item EXAMPLE_ENTITY_SPAWN_EGG = new SpawnEggItem(EntityTesting.exampleHostileEntityEntityType, 0x0f2ca3, 0x2ce861, new Item.Settings().group(ItemGroup.MISC));

	//Custom Fluid
	public static FlowableFluid STILL_JELLO; //define still
	public static FlowableFluid FLOWING_JELLO; //define flowing
	public static Item JELLO_BUCKET; //define bucket
	public static Block JELLO; //define block
	public static LakeFeature JELLO_LAKE; //generate in the world


	//Ore Generation (have your custom blocks be added to an existing biome!)
	/**
	 * Ore Wool in the overworld (broken in 1.18)
	 */
	/*
	private static ConfiguredFeature<?, ?> ORE_WOOL_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
					OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
					Blocks.WHITE_WOOL.getDefaultState(),
					9)) // Vein size
			.range(new RangeDecoratorConfig(
					// You can also use one of the other height providers if you don't want a uniform distribution
					UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
			.spreadHorizontally()
			.repeat(20); // Number of veins per chunk
	 */

	/**
	 * Custom Block in the Overworld (Broken in 1.18)
	 */
	/*
	private static ConfiguredFeature<?, ?> COMPLEX_BLOCK_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
					OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
					ExampleMod.COMPLEX_BLOCK.getDefaultState(),
					9)) // Vein size
			.range(new RangeDecoratorConfig(
					// You can also use one of the other height providers if you don't want a uniform distribution
					UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
			.spreadHorizontally()
			.repeat(20); // Number of veins per chunk
	 */

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

		Registry.register(Registry.BLOCK, new Identifier("camp", "four_sided_furnace"), FOUR_SIDED_FURNACE);
		Registry.register(Registry.ITEM, new Identifier("camp", "four_sided_furnace"), new BlockItem(FOUR_SIDED_FURNACE, new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(1).saturationModifier(6.0f).snack().meat().alwaysEdible().build())));

		//Register Items
		Registry.register(Registry.ITEM, new Identifier("camp", "basic_item"), BASIC_ITEM);
		Registry.register(Registry.ITEM, new Identifier("camp", "complex_item"), COMPLEX_ITEM);
		Registry.register(Registry.ITEM, new Identifier("camp", "lightning_knives"), LIGHTNING_KNIVES);
		Registry.register(Registry.ITEM, new Identifier("camp", "custom_pickaxe"), CUSTOM_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("camp", "custom_sword"), CUSTOM_SWORD);
		Registry.register(Registry.ITEM, new Identifier("camp", "regen_totem"), REGENERATING_TOTEM);

		//Register Spawn Eggs
		Registry.register(Registry.ITEM, new Identifier("camp", "example_hostile_entity_spawn_egg"), EXAMPLE_ENTITY_SPAWN_EGG);

		//Custom Fluid
		STILL_JELLO = Registry.register(Registry.FLUID, new Identifier("camp", "jello"), new JelloFluid.Still());
		FLOWING_JELLO = Registry.register(Registry.FLUID, new Identifier("camp", "flowing_jello"), new JelloFluid.Flowing());
		JELLO_BUCKET = Registry.register(Registry.ITEM, new Identifier("camp", "jello_bucket"),
				new BucketItem(STILL_JELLO, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

		//Block for custom fluid
		JELLO = Registry.register(Registry.BLOCK, new Identifier("camp", "jello"), new FluidBlock(STILL_JELLO, FabricBlockSettings.copy(Blocks.WATER)){});

		/*
		//Generation in the world for Custom Fluid
		JELLO_LAKE = Registry.register(Registry.FEATURE, new Identifier("camp", "jello_lake"), new LakeFeature(SingleStateFeatureConfig::deserialize));

		// generate in swamps, similar to water lakes, but with a chance of 40 (the higher the number, the lower the generation chance)
		Biomes.SWAMP.addFeature(
				GenerationStep.Feature.LOCAL_MODIFICATIONS,
				JELLO_LAKE.configure(new SingleStateFeatureConfig(JELLO.getDefaultState()))
						.createDecoratedFeature(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(40)))
		);
		*/

		//Register Ore Overworld Generation

		/*
		RegistryKey<ConfiguredFeature<?, ?>> oreWoolOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
				new Identifier("camp", "ore_wool_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreWoolOverworld.getValue(), ORE_WOOL_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreWoolOverworld);
		*/

		/*
		RegistryKey<ConfiguredFeature<?, ?>> complexBlockOverWorld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
				new Identifier("camp", "complex_block_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, complexBlockOverWorld.getValue(), COMPLEX_BLOCK_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, complexBlockOverWorld);
		 */

		/*
		//Register Beacons
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("custombeacon", "c2s-acknowledge"), (server, player, handler, buf, responseSender) -> {
			((ModdedPlayer)player).setHasMod(true);
		});
		*/
	}
}
