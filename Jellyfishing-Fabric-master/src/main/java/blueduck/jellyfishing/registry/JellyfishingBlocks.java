package blueduck.jellyfishing.registry;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.blocks.*;
import blueduck.jellyfishing.blocks.publicMC.JDeadCoralBlock;
import blueduck.jellyfishing.blocks.publicMC.JDoorBlock;
import blueduck.jellyfishing.blocks.publicMC.JStairsBlock;
import blueduck.jellyfishing.blocks.publicMC.JTrapdoorBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings({"unused", "deprecation"})
public class JellyfishingBlocks {
    public static final Block JELLY_BLOCK = register("jelly_block", new HoneyBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MapColor.ORANGE).velocityMultiplier(0.4F).jumpVelocityMultiplier(0.5F).nonOpaque().sounds(BlockSoundGroup.HONEY)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_JELLY_BLOCK = register("blue_jelly_block", new SlimeBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MapColor.ORANGE).velocityMultiplier(0.4F).jumpVelocityMultiplier(2F).nonOpaque().sounds(BlockSoundGroup.HONEY)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CORAL_PLANT = register("coral_plant", new JDeadCoralBlock(FabricBlockSettings.of(Material.UNDERWATER_PLANT, MapColor.ORANGE).nonOpaque().sounds(BlockSoundGroup.SLIME).noCollision().luminance(blockState -> 12)), ItemGroup.BUILDING_BLOCKS);
    public static final Block TUBE_PLANT = register("tube_plant", new JDeadCoralBlock(FabricBlockSettings.of(Material.UNDERWATER_PLANT, MapColor.ORANGE).nonOpaque().sounds(BlockSoundGroup.SLIME).noCollision()), ItemGroup.BUILDING_BLOCKS);

    public static final Block SCRAP_METAL = register("scrap_metal", new Block(FabricBlockSettings.of(Material.METAL, MapColor.BROWN).sounds(BlockSoundGroup.METAL).strength(2F, 2F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block SCRAP_METAL_STAIRS = register("scrap_metal_stairs", new JStairsBlock(SCRAP_METAL, FabricBlockSettings.copy(SCRAP_METAL)), ItemGroup.BUILDING_BLOCKS);
    public static final Block SCRAP_METAL_SLAB = register("scrap_metal_slab", new SlabBlock(FabricBlockSettings.copy(SCRAP_METAL)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_METAL = register("chrome_metal", new Block(FabricBlockSettings.of(Material.METAL, MapColor.BROWN).sounds(BlockSoundGroup.METAL).strength(2F, 2F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_METAL_STAIRS = register("chrome_metal_stairs", new JStairsBlock(CHROME_METAL, FabricBlockSettings.copy(CHROME_METAL)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_METAL_SLAB = register("chrome_metal_slab", new SlabBlock(FabricBlockSettings.copy(CHROME_METAL)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_BRICKS = register("chrome_bricks", new Block(FabricBlockSettings.of(Material.METAL, MapColor.BROWN).sounds(BlockSoundGroup.METAL).strength(2F, 2F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_BRICK_STAIRS = register("chrome_brick_stairs", new JStairsBlock(CHROME_METAL, FabricBlockSettings.copy(CHROME_METAL)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_BRICK_SLAB = register("chrome_brick_slab", new SlabBlock(FabricBlockSettings.copy(CHROME_METAL)), ItemGroup.BUILDING_BLOCKS);
    public static final Block VAULT_DOOR = register("vault_door", new JDoorBlock(FabricBlockSettings.of(Material.PISTON, MapColor.GRAY).sounds(BlockSoundGroup.METAL).strength(2F, 2F).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block VAULT_TRAPDOOR = register("vault_trapdoor", new JTrapdoorBlock(FabricBlockSettings.copy(VAULT_DOOR)), ItemGroup.BUILDING_BLOCKS);
    public static final Block SCRAP_METAL_WINDOW = register("scrap_metal_window", new JTrapdoorBlock(FabricBlockSettings.copy(VAULT_DOOR).nonOpaque()), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_DOOR = register("chrome_door", new JDoorBlock(FabricBlockSettings.of(Material.PISTON, MapColor.GRAY).sounds(BlockSoundGroup.METAL).strength(2F, 2F).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHROME_VENT = register("chrome_vent", new JTrapdoorBlock(FabricBlockSettings.copy(VAULT_DOOR)), ItemGroup.BUILDING_BLOCKS);

    public static final Block SEANUT_BRITTLE_BLOCK = register("seanut_brittle_block", new Block(FabricBlockSettings.of(Material.GLASS, MapColor.BROWN).sounds(BlockSoundGroup.STONE).strength(0.3F, 0.3F).breakByTool(FabricToolTags.PICKAXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINEAPPLE_BLOCK = register("pineapple_block", new Block(FabricBlockSettings.of(Material.DECORATION, MapColor.YELLOW).sounds(BlockSoundGroup.WET_GRASS).strength(0.9F, 0.9F).breakByTool(FabricToolTags.AXES)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINEAPPLE_PILLAR = register("pineapple_pillar", new PillarBlock(FabricBlockSettings.copy(PINEAPPLE_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHISELED_PINEAPPLE_BLOCK = register("chiseled_pineapple_block", new Block(FabricBlockSettings.copy(PINEAPPLE_BLOCK)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CORALSTONE = register("coralstone", new Block(FabricBlockSettings.of(Material.STONE, MapColor.PURPLE).sounds(BlockSoundGroup.STONE).strength(1.5F, 1F).breakByTool(FabricToolTags.PICKAXES).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block CORALSTONE_WALL = register("coralstone_wall", new WallBlock(FabricBlockSettings.copy(CORALSTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CORALSTONE_STAIRS = register("coralstone_stairs", new JStairsBlock(CORALSTONE, FabricBlockSettings.copy(CORALSTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CORALSTONE_SLAB = register("coralstone_slab", new SlabBlock(FabricBlockSettings.copy(CORALSTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CORALSTONE = register("polished_coralstone", new Block(FabricBlockSettings.of(Material.STONE, MapColor.PURPLE).sounds(BlockSoundGroup.STONE).strength(1.5F, 1F).breakByTool(FabricToolTags.PICKAXES).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CORALSTONE_STAIRS = register("polished_coralstone_stairs", new JStairsBlock(POLISHED_CORALSTONE, FabricBlockSettings.copy(POLISHED_CORALSTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CORALSTONE_SLAB = register("polished_coralstone_slab", new SlabBlock(FabricBlockSettings.copy(POLISHED_CORALSTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ALGAE_GRASS = register("algae_grass", new AlgaeGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.LIME).sounds(BlockSoundGroup.WET_GRASS).strength(1F, 1F).breakByTool(FabricToolTags.SHOVELS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ALGAE_BLOCK = register("algae_block", new Block(FabricBlockSettings.of(Material.WOOL, MapColor.LIME).sounds(BlockSoundGroup.WET_GRASS).strength(1F, 1F).breakByTool(FabricToolTags.SHOVELS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PATTY_TILES = register("patty_tiles", new Block(FabricBlockSettings.of(Material.WOOL, MapColor.TERRACOTTA_ORANGE).sounds(BlockSoundGroup.NETHER_STEM).strength(1F, 1F).breakByTool(FabricToolTags.SHOVELS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BAMBOO_WALL = register("bamboo_wall", new PillarBlock(FabricBlockSettings.copy(Blocks.BAMBOO)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINK_BAMBOO_WALL = register("pink_bamboo_wall", new PillarBlock(FabricBlockSettings.copy(Blocks.BAMBOO)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_BAMBOO_WALL = register("blue_bamboo_wall", new PillarBlock(FabricBlockSettings.copy(Blocks.BAMBOO)), ItemGroup.BUILDING_BLOCKS);
    public static final Block YELLOW_BAMBOO_WALL = register("yellow_bamboo_wall", new PillarBlock(FabricBlockSettings.copy(Blocks.BAMBOO)), ItemGroup.BUILDING_BLOCKS);

    public static final Block WHITE_CARPETED_TILES = register("white_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ORANGE_CARPETED_TILES = register("orange_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MAGENTA_CARPETED_TILES = register("magenta_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_MAGENTA).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_BLUE_CARPETED_TILES = register("light_blue_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_LIGHT_BLUE).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block YELLOW_CARPETED_TILES = register("yellow_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIME_CARPETED_TILES = register("lime_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_LIME).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINK_CARPETED_TILES = register("pink_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GRAY_CARPETED_TILES = register("gray_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_GRAY_CARPETED_TILES = register("light_gray_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYAN_CARPETED_TILES = register("cyan_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_CYAN).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PURPLE_CARPETED_TILES = register("purple_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_CARPETED_TILES = register("blue_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BROWN_CARPETED_TILES = register("brown_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GREEN_CARPETED_TILES = register("green_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_GREEN).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block RED_CARPETED_TILES = register("red_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_RED).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLACK_CARPETED_TILES = register("black_carpeted_tiles", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sounds(JellyfishingSounds.CARPETED_WOOD)), ItemGroup.BUILDING_BLOCKS);

    public static final Block SEANUT_BUSH = register("seanut_bush", new SeanutBush(FabricBlockSettings.of(Material.UNDERWATER_PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINEAPPLE_PLANT = register("pineapple_plant", new PineapplePlant(FabricBlockSettings.of(Material.UNDERWATER_PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)), ItemGroup.BUILDING_BLOCKS);

    public static final Block BUBBLE_BLOCK = register("bubble_block", new BubbleBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MapColor.GREEN).slipperiness(0.8F).sounds(BlockSoundGroup.SLIME).nonOpaque()), ItemGroup.BUILDING_BLOCKS);

    public static final Block GRILL = register("grill", new WaterloggableDirectionalBlock(FabricBlockSettings.of(Material.METAL, MapColor.BROWN).sounds(BlockSoundGroup.METAL).strength(2F, 2F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().nonOpaque()), ItemGroup.DECORATIONS);

    public static final Block POTTED_PINEAPPLE = register("potted_pineapple", new FlowerPotBlock(PINEAPPLE_PLANT, FabricBlockSettings.copy(Blocks.FLOWER_POT)), ItemGroup.BUILDING_BLOCKS);

    //public static final Block JELLYFISH_MACHINE = register("jellyfish_machine", new JellyfishMachineBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MapColor.BROWN).sounds(BlockSoundGroup.METAL)), ItemGroup.BUILDING_BLOCKS);


    public static void registerFlammables() {
        FlammableBlockRegistry.getDefaultInstance().add(BAMBOO_WALL, 10, 20);
        FlammableBlockRegistry.getDefaultInstance().add(PINK_BAMBOO_WALL, 10, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BLUE_BAMBOO_WALL, 10, 20);
        FlammableBlockRegistry.getDefaultInstance().add(YELLOW_BAMBOO_WALL, 10, 20);

        FlammableBlockRegistry.getDefaultInstance().add(WHITE_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ORANGE_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MAGENTA_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(LIGHT_BLUE_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(YELLOW_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(LIME_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(PINK_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(GRAY_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(LIGHT_GRAY_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CYAN_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(PURPLE_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BLUE_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BROWN_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(GREEN_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(RED_CARPETED_TILES, 8, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BLACK_CARPETED_TILES, 8, 20);
    }

    public static boolean isLoaded(String modid) {
        return FabricLoader.INSTANCE.isModLoaded(modid);
    }

    private static Block register(String id, Block block, ItemGroup group, boolean registerItem) {
        Block registered = Registry.register(Registry.BLOCK, new Identifier(Jellyfishing.MOD_ID, id), block);
        if (registerItem) {
            Registry.register(Registry.ITEM, new Identifier(Jellyfishing.MOD_ID, id), new BlockItem(registered, new FabricItemSettings().group(group)));
        }
        return registered;
    }
    private static Block register(String id, Block block, ItemGroup group) {
        return register(id, block, group, true);
    }
}