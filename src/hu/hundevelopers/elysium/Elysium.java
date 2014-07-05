package hu.hundevelopers.elysium;

import hu.hundevelopers.elysium.api.Plants;
import hu.hundevelopers.elysium.block.*;
import hu.hundevelopers.elysium.event.ElysiumFuelHandler;
import hu.hundevelopers.elysium.event.ElysiumHandler;
import hu.hundevelopers.elysium.item.*;
import hu.hundevelopers.elysium.proxy.CommonProxy;
import hu.hundevelopers.elysium.tile.ElysianTileEntityPortal;
import hu.hundevelopers.elysium.world.ElysiumWorldProvider;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenOcean;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenPlain;
import hu.hundevelopers.elysium.world.gen.CaveTypeElysium;
import hu.hundevelopers.elysium.world.gen.WorldGenElysium;

import java.io.File;

import buildcraft.core.proxy.CoreProxy;
import buildcraft.energy.BlockBuildcraftFluid;
import buildcraft.energy.ItemBucketBuildcraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Elysium.MODID, name=Elysium.NAME, version = Elysium.VERSION)
public class Elysium
{
    public static final String MODID = "elysium";
    public static final String NAME = "The Elysium";
    public static final String VERSION = "1.0";
    
    @Instance(Elysium.MODID)
	private static Elysium instance;

	public static Elysium getInstance()
	{
		return instance;
	}

	@SidedProxy(clientSide = "hu.hundevelopers.elysium.proxy.ClientProxy", serverSide = "hu.hundevelopers.elysium.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Configuration config;
	public static final CreativeTabs tabElysium = new CreativeTabs("elysium")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(blockPalestone);
		}
	};

	/** Dimension ID **/
	public static int dimensionID = DimensionManager.getNextFreeDimId();
	public static int maxDragon;

	//Rendering ids

	//Fluids
	public static Fluid fluidElysiumWater;
	public static Fluid fluidElysiumEnergy;

	private static Fluid elysiumFluidWater;
	private static Fluid elysiumFluidEnergy;

	//Rendering Ids
	public static int pipeStoneReinderingID;

	//Blocks
	public static Block blockPalestone;
    
	public static Block blockDirt;
	public static Block blockGrass;
	public static Block blockSand;
	public static Block blockRilt;
	public static Block blockLog;
	public static Block blockLeaves;
	public static Block blockGastroShell;
	public static Block blockSapling;
	public static Block blockPlanks;
	public static Block blockFlower;
	public static Block blockTallGrass;

	public static Block oreSulphure;
	public static Block oreCobalt;
	public static Block oreIridium;
	public static Block oreSilicon;
	public static Block oreJade;
	public static Block oreTourmaline;
	public static Block oreBeryl;

	public static Block blockElysiumWater;
	public static Block blockElysiumEnergy;

	public static Block blockFloatingShell;
	public static Block blockFloatingConch;
	
	public static Block blockTiberium;

//	public static Block blockPalestone;
//	public static Block blockPalestoneMossy;

//	public static Block blockPalestoneBrick;
//	public static Block blockPalestoneBrickCracked;
//	public static Block blockPalestoneBrickMossy;
//	public static Block blockPalestonePillar;
//	public static Block blockPalestoneBrickChiseld;

	public static Block blockPortalCore;

//	public static Block expeller;

	public static Block blockSulphure;
	public static Block blockCobalt;
	public static Block blockIridium;
	public static Block blockSilicon;
	public static Block blockJade;
	public static Block blockTourmaline;
	public static Block blockBeryl;
	public static Block blockPipe;

//	public static Block blockFancyWorkbench;
//	public static Block blockFancyTank;

//	public static Block blockCrystal;
	
	

	//Items

	public static Item itemPrism;
	public static Item itemWhistle;

	public static Item itemSeedsPepper;
	public static Item itemAsphodelPetals;

	public static Item itemBeryl;
	public static Item itemIngotCobalt;
	public static Item itemIngotIridium;
	public static Item itemJade;
	public static Item itemSiliconChunk;
	public static Item itemSturdyHide;
	public static Item itemSulphur;
	public static Item itemTourmaline;

	public static Item itemSwordFostimber;
	public static Item itemPickaxeFostimber;
	public static Item itemAxeFostimber;
	public static Item itemShovelFostimber;
	public static Item itemHoeFostimber;

	public static Item itemSwordPalestone;
	public static Item itemPickaxePalestone;
	public static Item itemAxePalestone;
	public static Item itemSpadePalestone;
	public static Item itemHoePalestone;

	public static Item itemOverKill;
	public static Item itemDebug;
	public static Item itemWaterBucket;
	
	/** Biomes **/
	public static BiomeGenBase biomePlain = null;
	public static BiomeGenBase biomeOcean = null;
	
	private int biomeIdPlains;
	private int biomeIdOcean;

	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	elysiumFluidWater = new Fluid("elysium_water");
		FluidRegistry.registerFluid(elysiumFluidWater);
		fluidElysiumWater = FluidRegistry.getFluid("elysium_water");
		
		if (fluidElysiumWater.getBlock() == null)
		{
			blockElysiumWater = new ElysiumWaterBlock(fluidElysiumWater, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("elysium_water");
			registerBlock(blockElysiumWater);
			fluidElysiumWater.setBlock(blockElysiumWater);
		} else {
			blockElysiumWater = fluidElysiumWater.getBlock();
		}
		
		if (blockElysiumWater != null) {
			itemWaterBucket = new ElysiumBucket(blockElysiumWater).setTextureName("elysium_bucket_water").setUnlocalizedName("elysium_bucket_water");
			itemWaterBucket.setContainerItem(Items.bucket);
			registerItem(itemWaterBucket);
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("elysium_water", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemWaterBucket), new ItemStack(Items.bucket));
		}
		
//

    	elysiumFluidEnergy= new Fluid("elysium_energy");
		FluidRegistry.registerFluid(elysiumFluidEnergy);
		fluidElysiumEnergy = FluidRegistry.getFluid("elysium_energy");
		
		if (fluidElysiumEnergy.getBlock() == null)
		{
			blockElysiumEnergy = new ElysiumEnergyLiquid(fluidElysiumEnergy, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("elysium_water");
			registerBlock(blockElysiumEnergy);
			fluidElysiumWater.setBlock(blockElysiumEnergy);
		} else {
			blockElysiumEnergy = fluidElysiumWater.getBlock();
		}
		
		//
		
		ElysiumHandler.INSTANCE.buckets.put(blockElysiumWater, itemWaterBucket);

		MinecraftForge.EVENT_BUS.register(ElysiumHandler.INSTANCE);
		
    	//Config
		config = new Configuration(new File(event.getModConfigurationDirectory(), "Elysium.cfg"));
		
		try
		{
			config.load();

			Property ELYSIUM_PLAINS = Elysium.config.get("biomeIds", "ELYSIUM_PLAINS", Ids.BIOME_PLAIN);
			biomeIdPlains = ELYSIUM_PLAINS.getInt();
			
			Property ELYSIUM_OCEAN = Elysium.config.get("biomeIds", "ELYSIUM_OCEAN", Ids.BIOME_OCEAN);
			biomeIdOcean = ELYSIUM_OCEAN.getInt();
			
			Property MAX_DRAGON_IN_END = Elysium.config.get("other", "MAX_DRAGON_IN_END", Ids.MAX_DRAGON_IN_END, "How many dragons can be spawned to the End at the same time!");
			maxDragon = MAX_DRAGON_IN_END.getInt();
			
	    }
		finally
		{
			config.save();
		}
		
		MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event)
    {
		if (event.map.getTextureType() == 0)
		{
			elysiumFluidWater.setIcons(blockElysiumWater.getBlockTextureFromSide(1), blockElysiumWater.getBlockTextureFromSide(2));
		}
	}
    
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		//Block Registering

		blockPalestone = (new ElysiumBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone").setBlockTextureName("palestone");
		registerBlock(blockPalestone);
		
		blockDirt = (new ElysiumBlock(Material.ground)).setHardness(0.5F).setStepSound(Block.soundTypeGravel).setBlockName("elysium_dirt").setBlockTextureName("dirt");
		registerBlock(blockDirt);

		blockGrass = (new ElysiumBlockGrass(Material.ground)).setHardness(0.6F).setStepSound(Block.soundTypeGrass).setBlockName("elysium_grass").setBlockTextureName("grass");
		registerBlock(blockGrass);

		blockSand = (new ElysiumBlockFalling(Material.sand)).setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName("leucosand").setBlockTextureName("sand");
		registerBlock(blockSand);

		blockRilt = (new ElysiumBlockRilt(Material.sand)).setHardness(0.6F).setStepSound(Block.soundTypeGravel).setBlockTextureName("rilt").setBlockName("rilt");
		registerBlock(blockRilt);
	
		blockSapling = (new ElysiumBlockSapling()).setHardness(0F).setBlockTextureName("elysium_sapling").setBlockName("elysium_sapling");
		GameRegistry.registerBlock(blockSapling, ElysimSaplingItemBlock.class, blockSapling.getUnlocalizedName());

		blockLog = (new ElysiumBlockLog()).setHardness(2.0F).setBlockTextureName("elysium_log").setBlockName("elysium_log");
		GameRegistry.registerBlock(blockLog, ElysimLogItemBlock.class, blockLog.getUnlocalizedName());

		blockLeaves = (new ElysiumBlockLeaves()).setLightOpacity(1).setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_leaves").setBlockName("elysium_leaves");
		GameRegistry.registerBlock(blockLeaves, ElysimLeavesItemBlock.class, blockLeaves.getUnlocalizedName());

		blockPlanks = (new ElysiumBlockWood()).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockTextureName("elysium_planks").setBlockName("elysium_planks");
		GameRegistry.registerBlock(blockPlanks, ElysimPlanksItemBlock.class, blockPlanks.getUnlocalizedName());

//		blockGastroShell = (new ElysiumBlockGastroShell(idGastroShellBlock.getInt(), Material.rock)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setBlockName("gastroshell");
//		registerBlock(blockGastroShell, "Gastro Shell");

		blockFlower = (new ElysiumBlockFlower()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_flower").setBlockName("elysium_flower");
		GameRegistry.registerBlock(blockFlower, ElysimFlowerItemBlock.class, blockFlower.getUnlocalizedName());

		blockTallGrass = new ElysiumBlockTallGrass().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_tallgrass").setBlockName("elysium_tallgrass");
		GameRegistry.registerBlock(blockTallGrass, ElysimTallGrassItemBlock.class, blockTallGrass.getUnlocalizedName());

		oreSulphure = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreSulphur").setBlockName("oreSulphur");
		registerBlock(oreSulphure);

		oreBeryl = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setLightLevel(0.5F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreBeryl").setBlockName("oreBeryl");
		registerBlock(oreBeryl);

		oreCobalt = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreCobalt").setBlockName("oreCobalt");
		registerBlock(oreCobalt);

		oreIridium = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreIridium").setBlockName("oreIridium");
		registerBlock(oreIridium);

		oreSilicon = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreSilicon").setBlockName("oreSilicon");
		registerBlock(oreSilicon);

		oreJade = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreJade").setBlockName("oreJade");
		registerBlock(oreJade);

		oreTourmaline = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreTourmaline").setBlockName("oreTourmaline");
		registerBlock(oreTourmaline);
		
		blockPortalCore = new ElysiumBlockPortalCore(Material.glass).setHardness(5F).setStepSound(Block.soundTypeGlass).setBlockName("portalCore");
		registerBlock(blockPortalCore);

		blockFloatingConch = new ElysiumFloatingBlock("conch").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("floating_block_conch").setBlockName("floating_block_conch");
		registerBlock(blockFloatingConch);
		
		blockFloatingShell = new ElysiumFloatingBlock("shell").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("floating_block_shell").setBlockName("floating_block_shell");
		registerBlock(blockFloatingShell);

		Blocks.dragon_egg.setCreativeTab(tabElysium);

		

		blockTiberium = new ElysiumTiberiumBlock(Material.glass).setHardness(0.3F).setStepSound(Block.soundTypeGlass).setLightLevel(1.0F).setBlockTextureName("energy_crystal_rough5").setBlockName("energy_crystal");
		registerBlock(blockTiberium);
		
		blockPipe = new ElysiumPipeBlock(Material.rock).setHardness(0.3F).setStepSound(Block.soundTypeStone).setBlockTextureName("palestone").setBlockName("stone_pipe");
		registerBlock(blockPipe);

		
		/*
		blockPalestoneBrick = (new ElysiumBlockHeatable(idPalestoneBrickBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick");
		registerBlock(blockPalestoneBrick, "Palestone Brick");

		blockPalestoneBrickCracked = (new ElysiumBlockHeatable(idPalestoneBrickCrackedBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_cracked");
		registerBlock(blockPalestoneBrickCracked, "Cracked Palestone Brick");

		blockPalestoneBrickMossy = (new ElysiumBlockHeatable(idPalestoneBrickMossyBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_mossy");
		registerBlock(blockPalestoneBrickMossy, "Mossy Palestone Brick");

		blockPalestoneBrickChiseld = (new ElysiumBlockHeatable(idPalestoneChiseldBrickBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_chiseld");
		registerBlock(blockPalestoneBrickChiseld, "Chiseld Palestone Brick");

		blockPalestonePillar = (new ElysiumBlockPalestonePillar(idPalestonePillarBlock.getInt(), Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_pillar");
		registerBlock(blockPalestonePillar, "Palestone Pillar");

		*/

		blockSulphure = new ElysiumBlock(Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockSulphur").setBlockName("blockSulphur");
		registerBlock(blockSulphure);

		blockBeryl = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setLightLevel(0.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockBeryl").setBlockName("blockBeryl");
		registerBlock(blockBeryl);

		blockCobalt = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockCobalt").setBlockName("blockCobalt");
		registerBlock(blockCobalt);

		blockIridium = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockIridium").setBlockName("blockIridium");
		registerBlock(blockIridium);

		blockSilicon = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockSilicon").setBlockName("blockSilicon");
		registerBlock(blockSilicon);

		blockJade = new ElysiumBlock(Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockJade").setBlockName("blockJade");
		registerBlock(blockJade);

		blockTourmaline = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockTourmaline").setBlockName("blockTourmaline");
		registerBlock(blockTourmaline);

		
		
		//Items
		itemPrism = new ElysiumItemPrism().setTextureName("gracecrystal").setUnlocalizedName("prism");
		registerItem(itemPrism);
		
		itemWhistle = new ElysiumItemWhistle().setTextureName("enderflute").setUnlocalizedName("enderflute");
		registerItem(itemWhistle);

		itemSeedsPepper = new ElysiumItem().setTextureName("seeds_pepper").setUnlocalizedName("seeds_pepper");
		registerItem(itemSeedsPepper);

		itemOverKill = new ElysiumItemOverkill().setTextureName("overkill").setUnlocalizedName("overkill").setCreativeTab(null);
		registerItem(itemOverKill);

		itemAsphodelPetals = new ElysiumItem().setTextureName("asphodelpetal").setUnlocalizedName("asphodelpetal");
		registerItem(itemAsphodelPetals);

		itemDebug = new ElysiumItemDebug().setTextureName("debug").setUnlocalizedName("debug");
		registerItem(itemDebug);

		itemBeryl = new ElysiumItem().setTextureName("beryl").setUnlocalizedName("beryl");
		registerItem(itemBeryl);

		itemIngotCobalt = new ElysiumItem().setTextureName("ingotCobalt").setUnlocalizedName("ingotCobalt");
		registerItem(itemIngotCobalt);

		itemIngotIridium = new ElysiumItem().setTextureName("ingotIridium").setUnlocalizedName("ingotIridium");
		registerItem(itemIngotIridium);

		itemJade = new ElysiumItem().setTextureName("jade").setUnlocalizedName("jade");
		registerItem(itemJade);

		itemSiliconChunk = new ElysiumItem().setTextureName("siliconchunk").setUnlocalizedName("siliconchunk");
		registerItem(itemSiliconChunk);

		itemSulphur = new ElysiumItem().setTextureName("sulphur").setUnlocalizedName("elysium_sulphur");
		registerItem(itemSulphur);

		itemTourmaline = new ElysiumItem().setTextureName("tourmaline").setUnlocalizedName("tourmaline");
		registerItem(itemTourmaline);

		itemSturdyHide = new ElysiumItem().setTextureName("sturdyHide").setUnlocalizedName("sturdyHide");
		registerItem(itemSturdyHide);

		//Tool Registering

		Item.ToolMaterial FOSTIMBER_MAT = EnumHelper.addToolMaterial("FOSTIMBER", 0, 59, 2.0F, 0, 15);

		itemSwordFostimber = new ElysiumItemSword(FOSTIMBER_MAT).setTextureName("swordFostimber").setUnlocalizedName("swordFostimber");
		registerItem(itemSwordFostimber);

		itemPickaxeFostimber = new ElysiumItemPickaxe(FOSTIMBER_MAT).setTextureName("pickaxeFostimber").setUnlocalizedName("pickaxeFostimber");
		registerItem(itemPickaxeFostimber);

		itemAxeFostimber = new ElysiumItemAxe(FOSTIMBER_MAT).setTextureName("axeFostimber").setUnlocalizedName("axeFostimber");
		registerItem(itemAxeFostimber);

		itemShovelFostimber = new ElysiumItemShovel(FOSTIMBER_MAT).setTextureName("shovelFostimber").setUnlocalizedName("shovelFostimber");
		registerItem(itemShovelFostimber);

		itemHoeFostimber = new ElysiumItemHoe(FOSTIMBER_MAT).setTextureName("hoeFostimber").setUnlocalizedName("hoeFostimber");
		registerItem(itemHoeFostimber);

		Item.ToolMaterial STONE_MAT = EnumHelper.addToolMaterial("PALESTONE", 1, 131, 4.0F, 1, 5);

		itemSwordPalestone = new ElysiumItemSword(STONE_MAT).setTextureName("swordPalestone").setUnlocalizedName("swordPalestone");
		registerItem(itemSwordPalestone);

		itemPickaxePalestone = new ElysiumItemPickaxe(STONE_MAT).setTextureName("pickaxePalestone").setUnlocalizedName("pickaxePalestone");
		registerItem(itemPickaxePalestone);

		itemAxePalestone = new ElysiumItemAxe(STONE_MAT).setTextureName("axePalestone").setUnlocalizedName("axePalestone");
		registerItem(itemAxePalestone);

		itemSpadePalestone = new ElysiumItemShovel(STONE_MAT).setTextureName("shovelPalestone").setUnlocalizedName("shovelPalestone");
		registerItem(itemSpadePalestone);

		itemHoePalestone = new ElysiumItemHoe(STONE_MAT).setTextureName("hoePalestone").setUnlocalizedName("hoePalestone");
		registerItem(itemHoePalestone);

//		MinecraftForge.setToolClass(itemPickaxeFostimber, "pickaxe", 0);
//        MinecraftForge.setToolClass(itemAxeFostimber, "axe", 0);
//        MinecraftForge.setToolClass(itemShovelFostimber, "shovel", 0);
//        MinecraftForge.setToolClass(itemPickaxePalestone, "pickaxe", 1);
//        MinecraftForge.setToolClass(itemAxePalestone, "axe", 1);
//        MinecraftForge.setToolClass(itemSpadePalestone, "shovel", 1);

		oreSulphure.setHarvestLevel("pickaxe", 0);
		oreCobalt.setHarvestLevel("pickaxe", 1);
		oreSilicon.setHarvestLevel("pickaxe", 2);
		oreIridium.setHarvestLevel("pickaxe", 2);
		oreJade.setHarvestLevel("pickaxe", 2);
		oreBeryl.setHarvestLevel("pickaxe", 2);
		oreTourmaline.setHarvestLevel("pickaxe", 3);

		blockSulphure.setHarvestLevel("pickaxe", 0);
		blockCobalt.setHarvestLevel("pickaxe", 1);
		blockSilicon.setHarvestLevel("pickaxe", 2);
		blockIridium.setHarvestLevel("pickaxe", 2);
		blockJade.setHarvestLevel("pickaxe", 2);
		blockBeryl.setHarvestLevel("pickaxe", 2);
		blockTourmaline.setHarvestLevel("pickaxe", 3);

		blockGrass.setHarvestLevel("shovel", 0);
		blockDirt.setHarvestLevel("shovel", 0);
		blockLog.setHarvestLevel("axe", 0);
		blockPlanks.setHarvestLevel("axe", 0);

		//Crafting Registering

		GameRegistry.addRecipe(new ItemStack(itemPrism), new Object[] {"SSS","SDT","TTT", Character.valueOf('S'), Items.sugar, Character.valueOf('T'), Items.ghast_tear, Character.valueOf('D'), Items.diamond});
		GameRegistry.addRecipe(new ItemStack(itemPrism), new Object[] {"SSS","SDT","TTT", Character.valueOf('T'), Items.sugar, Character.valueOf('S'), Items.ghast_tear, Character.valueOf('D'), Items.diamond});
		GameRegistry.addRecipe(new ItemStack(itemPrism), new Object[] {"SST","SDT","STT", Character.valueOf('S'), Items.sugar, Character.valueOf('T'), Items.ghast_tear, Character.valueOf('D'), Items.diamond});
		GameRegistry.addRecipe(new ItemStack(itemPrism), new Object[] {"SST","SDT","STT", Character.valueOf('T'), Items.sugar, Character.valueOf('S'), Items.ghast_tear, Character.valueOf('D'), Items.diamond});


		GameRegistry.addRecipe(new ItemStack(itemPickaxeFostimber), new Object[] {"WW "," SW","S W", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemPickaxePalestone), new Object[] {"WW "," SW","S W", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemShovelFostimber), new Object[] {" WW"," SW","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemSpadePalestone), new Object[] {" WW"," SW","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemHoeFostimber), new Object[] {"WWW"," S ","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemHoePalestone), new Object[] {"WWW"," S ","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemAxeFostimber), new Object[] {"WW ","WS ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemAxePalestone), new Object[] {"WW ","WS ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemSwordFostimber), new Object[] {"  W"," W ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemSwordPalestone), new Object[] {"  W"," W ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemWhistle), new Object[] {" OO","O O", "EO ", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});

//		GameRegistry.addRecipe(new ItemStack(blockPalestonePillar), new Object[] {"X", "X", Character.valueOf('X'), blockPalestone});


		GameRegistry.addRecipe(new ItemStack(blockSulphure), new Object[] {"XX", "XX", Character.valueOf('X'), itemSulphur});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSulphur, 4), new Object[] {blockSulphure});
		GameRegistry.addRecipe(new ItemStack(blockBeryl), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemBeryl});
		GameRegistry.addShapelessRecipe(new ItemStack(itemBeryl, 9), new Object[] {blockBeryl});
		GameRegistry.addRecipe(new ItemStack(blockCobalt), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemIngotCobalt});
		GameRegistry.addShapelessRecipe(new ItemStack(itemIngotCobalt, 9), new Object[] {blockCobalt});
		GameRegistry.addRecipe(new ItemStack(blockIridium), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemIngotIridium});
		GameRegistry.addShapelessRecipe(new ItemStack(itemIngotIridium, 9), new Object[] {blockIridium});
		GameRegistry.addRecipe(new ItemStack(blockSilicon), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemSiliconChunk});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSiliconChunk, 9), new Object[] {blockSilicon});
		GameRegistry.addRecipe(new ItemStack(blockJade), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemJade});
		GameRegistry.addShapelessRecipe(new ItemStack(itemJade, 9), new Object[] {blockJade});
		GameRegistry.addRecipe(new ItemStack(blockTourmaline), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemTourmaline});
		GameRegistry.addShapelessRecipe(new ItemStack(itemTourmaline, 9), new Object[] {blockTourmaline});

		GameRegistry.addShapelessRecipe(new ItemStack(itemAsphodelPetals, 2), new Object[] {new ItemStack(blockFlower, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockPlanks, 4, 0), new Object[] {new ItemStack(blockLog, 1, 0)});

		//Ore registry
		OreDictionary.registerOre("dyePink", itemAsphodelPetals);
        OreDictionary.registerOre("logWood", new ItemStack(blockLog, 1, 0));
        OreDictionary.registerOre("plankWood", new ItemStack(blockPlanks, 1, 0));
        OreDictionary.registerOre("treeSapling", new ItemStack(blockSapling, 1, 0));
        OreDictionary.registerOre("treeLeaves", new ItemStack(blockLeaves, 1, 0));
        
        OreDictionary.registerOre("oreIridium", oreIridium);
        OreDictionary.registerOre("oreSulphure", oreSulphure);
        OreDictionary.registerOre("oreBeryl", oreBeryl);
        OreDictionary.registerOre("oreCobalt", oreCobalt);
        OreDictionary.registerOre("oreJade", oreJade);
        OreDictionary.registerOre("oreSilicon", oreSilicon);
        OreDictionary.registerOre("oreTourmaline", oreTourmaline);
        
        //OreDictionary recipes
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.stick, 6), new Object[] {"X", "X", "X", Character.valueOf('X'), "plankWood"}));


		//Smelting Registering

		GameRegistry.addSmelting(this.oreCobalt, new ItemStack(this.itemIngotCobalt), 0.7F);
		GameRegistry.addSmelting(this.oreIridium, new ItemStack(this.itemIngotIridium), 1.0F);

		//Handlers
		GameRegistry.registerFuelHandler(new ElysiumFuelHandler());
		
		
		//Entity Registering

		GameRegistry.registerTileEntity(ElysianTileEntityPortal.class, "ElysianTileEntityPortal");

		/** Register WorldProvider for Dimension **/
		DimensionManager.registerProviderType(dimensionID, ElysiumWorldProvider.class, false);
		DimensionManager.registerDimension(dimensionID, dimensionID);


		biomePlain = new ElysiumBiomeGenPlain(biomeIdPlains);
		biomeOcean = new ElysiumBiomeGenOcean(biomeIdOcean);
		
		GameRegistry.registerWorldGenerator(new WorldGenElysium(), 0);

		proxy.registerRenderers();
		
		
//		elysiumCave = new CaveTypeElysium("Elysium Cave", blockPalestone);
    }
	
//	public CaveType elysiumCave;
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		//APIs
		Plants.addGrassPlant(blockTallGrass, 0, 30);
		Plants.addGrassPlant(blockFlower, 0, 10);
		Plants.addGrassSeed(new ItemStack(itemSeedsPepper), 10);
	
		//Modded APIs

//		CavesAPI.registerCaveType(elysiumCave);
		
	}
	
    private void registerBlock(Block block)
    {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
    
    private void registerItem(Item item)
    {
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
}
