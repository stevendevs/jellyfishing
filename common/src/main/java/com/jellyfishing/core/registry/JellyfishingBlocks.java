package com.jellyfishing.core.registry;

import com.jellyfishing.common.block.*;
import com.jellyfishing.core.Jellyfishing;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({ "unused"})
public class JellyfishingBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Jellyfishing.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> JELLY_BLOCK = create("jelly_block", () -> new HoneyBlock(Block.Properties.of(Material.CLAY, MaterialColor.COLOR_ORANGE).speedFactor(0.4F).jumpFactor(0.5F).noOcclusion().sound(SoundType.HONEY_BLOCK)), CreativeModeTabs.REDSTONE_BLOCKS);
    public static final RegistrySupplier<Block> BLUE_JELLY_BLOCK = create("blue_jelly_block", () -> new SlimeBlock(Block.Properties.of(Material.CLAY, MaterialColor.COLOR_ORANGE).speedFactor(0.4F).jumpFactor(2F).noOcclusion().sound(SoundType.HONEY_BLOCK)), CreativeModeTabs.REDSTONE_BLOCKS);

    public static final RegistrySupplier<Block> CORAL_PLANT = create("coral_plant", () -> new Water(Block.Properties.of(Material.WATER_PLANT, MaterialColor.COLOR_ORANGE).noOcclusion().sound(SoundType.SLIME_BLOCK).noCollission().lightLevel(blockState -> 12)), CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistrySupplier<Block> TUBE_PLANT = create("tube_plant", () -> new Water(Block.Properties.of(Material.WATER_PLANT, MaterialColor.COLOR_ORANGE).noOcclusion().sound(SoundType.SLIME_BLOCK).noCollission()), CreativeModeTabs.NATURAL_BLOCKS);

    public static final RegistrySupplier<Block> SCRAP_METAL = create("scrap_metal", () -> new Block(Block.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).sound(SoundType.METAL).strength(2F, 2F).requiresCorrectToolForDrops()), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> SCRAP_METAL_STAIRS = create("scrap_metal_stairs", () -> new StairBlock(SCRAP_METAL.get().defaultBlockState(), Block.Properties.copy(SCRAP_METAL.get())), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> SCRAP_METAL_SLAB = create("scrap_metal_slab", () -> new SlabBlock(Block.Properties.copy(SCRAP_METAL.get())), CreativeModeTabs.BUILDING_BLOCKS);
//    public static final RegistrySupplier<Block> SCRAP_METAL_VERTICAL_SLAB = conditionallyRegisterBlock("scrap_metal_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(SCRAP_METAL.get())), () -> isLoaded("quark"));
//    public static final RegistrySupplier<Item> SCRAP_METAL_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("scrap_metal_vertical_slab", () -> new BlockItem(SCRAP_METAL_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistrySupplier<Block> CHROME_METAL = create("chrome_metal", () -> new Block(Block.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).sound(SoundType.METAL).strength(2F, 2F).requiresCorrectToolForDrops()), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> CHROME_METAL_STAIRS = create("chrome_metal_stairs", () -> new StairBlock(CHROME_METAL.get().defaultBlockState(), Block.Properties.copy(CHROME_METAL.get())), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> CHROME_METAL_SLAB = create("chrome_metal_slab", () -> new SlabBlock(Block.Properties.copy(CHROME_METAL.get())), CreativeModeTabs.BUILDING_BLOCKS);
//    public static final RegistrySupplier<Block> CHROME_METAL_VERTICAL_SLAB = conditionallyRegisterBlock("chrome_metal_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(CHROME_METAL.get())), () -> isLoaded("quark"));
//    public static final RegistrySupplier<Item> CHROME_METAL_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("chrome_metal_vertical_slab", () -> new BlockItem(CHROME_METAL_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistrySupplier<Block> CHROME_BRICKS = create("chrome_bricks", () -> new Block(Block.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).sound(SoundType.METAL).strength(2F, 2F).requiresCorrectToolForDrops()), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> CHROME_BRICK_STAIRS = create("chrome_brick_stairs", () -> new StairBlock(CHROME_METAL.get().defaultBlockState(), Block.Properties.copy(CHROME_METAL.get())), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> CHROME_BRICK_SLAB = create("chrome_brick_slab", () -> new SlabBlock(Block.Properties.copy(CHROME_METAL.get())), CreativeModeTabs.BUILDING_BLOCKS);
//    public static final RegistrySupplier<Block> CHROME_BRICK_VERTICAL_SLAB = conditionallyRegisterBlock("chrome_brick_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(CHROME_BRICKS.get())), () -> isLoaded("quark"));
//    public static final RegistrySupplier<Item> CHROME_BRICK_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("chrome_brick_vertical_slab", () -> new BlockItem(CHROME_BRICK_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistrySupplier<Block> VAULT_DOOR = create("vault_door", () -> new DoorBlock(Block.Properties.of(Material.PISTON, MaterialColor.COLOR_GRAY).sound(SoundType.METAL).strength(2F, 2F).noOcclusion().requiresCorrectToolForDrops(), SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN), CreativeModeTabs.REDSTONE_BLOCKS);
    public static final RegistrySupplier<Block> VAULT_TRAPDOOR = create("vault_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(VAULT_DOOR.get()), SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN), CreativeModeTabs.REDSTONE_BLOCKS);

    public static final RegistrySupplier<Block> SCRAP_METAL_WINDOW = create("scrap_metal_window", () -> new TrapDoorBlock(Block.Properties.copy(VAULT_DOOR.get()).noOcclusion(), SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN), CreativeModeTabs.REDSTONE_BLOCKS);
    public static final RegistrySupplier<Block> CHROME_DOOR = create("chrome_door", () -> new DoorBlock(Block.Properties.of(Material.PISTON, MaterialColor.COLOR_GRAY).sound(SoundType.METAL).strength(2F, 2F).noOcclusion().requiresCorrectToolForDrops(), SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN), CreativeModeTabs.REDSTONE_BLOCKS);
    public static final RegistrySupplier<Block> CHROME_VENT = create("chrome_vent", () -> new TrapDoorBlock(Block.Properties.copy(VAULT_DOOR.get()), SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN), CreativeModeTabs.REDSTONE_BLOCKS);

    public static final RegistrySupplier<Block> SEANUT_BRITTLE_BLOCK = create("seanut_brittle_block", () -> new Block(Block.Properties.of(Material.GLASS, MaterialColor.COLOR_BROWN).sound(SoundType.STONE).strength(0.3F, 0.3F)), CreativeModeTabs.BUILDING_BLOCKS);

    public static final RegistrySupplier<Block> PINEAPPLE_BLOCK = create("pineapple_block", () -> new Block(Block.Properties.of(Material.PLANT, MaterialColor.COLOR_YELLOW).sound(SoundType.WET_GRASS).strength(0.9F, 0.9F)), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> PINEAPPLE_PILLAR = create("pineapple_pillar", () -> new RotatedPillarBlock(Block.Properties.copy(PINEAPPLE_BLOCK.get())), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> CHISELED_PINEAPPLE_BLOCK = create("chiseled_pineapple_block", () -> new Block(Block.Properties.copy(PINEAPPLE_BLOCK.get())), CreativeModeTabs.BUILDING_BLOCKS);

    public static final RegistrySupplier<Block> CORALSTONE = create("coralstone", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).sound(SoundType.STONE).strength(1.5F, 1F).requiresCorrectToolForDrops()), CreativeModeTabs.BUILDING_BLOCKS, CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistrySupplier<Block> CORALSTONE_WALL = create("coralstone_wall", () -> new WallBlock(Block.Properties.copy(CORALSTONE.get())), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> CORALSTONE_STAIRS = create("coralstone_stairs", () -> new StairBlock(CORALSTONE.get().defaultBlockState(), Block.Properties.copy(CORALSTONE.get())), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> CORALSTONE_SLAB = create("coralstone_slab", () -> new SlabBlock(Block.Properties.copy(CORALSTONE.get())), CreativeModeTabs.BUILDING_BLOCKS);
//    public static final RegistrySupplier<Block> CORALSTONE_VERTICAL_SLAB = conditionallyRegisterBlock("coralstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(CORALSTONE.get())), () -> isLoaded("quark"));
//    public static final RegistrySupplier<Item> CORALSTONE_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("coralstone_vertical_slab", () -> new BlockItem(CORALSTONE_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistrySupplier<Block> POLISHED_CORALSTONE = create("polished_coralstone", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).sound(SoundType.STONE).strength(1.5F, 1F).requiresCorrectToolForDrops()), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> POLISHED_CORALSTONE_STAIRS = create("polished_coralstone_stairs", () -> new StairBlock(POLISHED_CORALSTONE.get().defaultBlockState(), Block.Properties.copy(POLISHED_CORALSTONE.get())), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> POLISHED_CORALSTONE_SLAB = create("polished_coralstone_slab", () -> new SlabBlock(Block.Properties.copy(POLISHED_CORALSTONE.get())), CreativeModeTabs.BUILDING_BLOCKS);
//    public static final RegistrySupplier<Block> POLISHED_CORALSTONE_VERTICAL_SLAB = conditionallyRegisterBlock("polished_coralstone_vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(CORALSTONE.get())), () -> isLoaded("quark"));
//    public static final RegistrySupplier<Item> POLISHED_CORALSTONE_VERTICAL_SLAB_ITEM = conditionallyRegisterItem("polished_coralstone_vertical_slab", () -> new BlockItem(POLISHED_CORALSTONE_VERTICAL_SLAB.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)), () -> isLoaded("quark"));

    public static final RegistrySupplier<Block> ALGAE_GRASS = create("algae_grass", () -> new AlgaeGrassBlock(Block.Properties.of(Material.GRASS, MaterialColor.COLOR_LIGHT_GREEN).sound(SoundType.WET_GRASS).strength(1F, 1F)), CreativeModeTabs.BUILDING_BLOCKS, CreativeModeTabs.NATURAL_BLOCKS);
    public static final RegistrySupplier<Block> ALGAE_BLOCK = create("algae_block", () -> new Block(Block.Properties.of(Material.WOOL, MaterialColor.COLOR_LIGHT_GREEN).sound(SoundType.WET_GRASS).strength(1F, 1F)), CreativeModeTabs.BUILDING_BLOCKS, CreativeModeTabs.NATURAL_BLOCKS);

    public static final RegistrySupplier<Block> PATTY_TILES = create("patty_tiles", () -> new Block(Block.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_ORANGE).sound(SoundType.STEM).strength(1F, 1F)), CreativeModeTabs.BUILDING_BLOCKS);

    public static final RegistrySupplier<Block> BAMBOO_WALL = create("bamboo_wall", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW).sound(SoundType.BAMBOO_WOOD)), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> PINK_BAMBOO_WALL = create("pink_bamboo_wall", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK).sound(SoundType.BAMBOO_WOOD)), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> BLUE_BAMBOO_WALL = create("blue_bamboo_wall", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_BLUE).sound(SoundType.BAMBOO_WOOD)), CreativeModeTabs.BUILDING_BLOCKS);
    public static final RegistrySupplier<Block> YELLOW_BAMBOO_WALL = create("yellow_bamboo_wall", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW).sound(SoundType.BAMBOO_WOOD)), CreativeModeTabs.BUILDING_BLOCKS);


    public static final RegistrySupplier<Block> WHITE_CARPETED_TILES = create("white_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> ORANGE_CARPETED_TILES = create("orange_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> MAGENTA_CARPETED_TILES = create("magenta_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_MAGENTA).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> LIGHT_BLUE_CARPETED_TILES = create("light_blue_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> YELLOW_CARPETED_TILES = create("yellow_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> LIME_CARPETED_TILES = create("lime_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> PINK_CARPETED_TILES = create("pink_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> GRAY_CARPETED_TILES = create("gray_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> LIGHT_GRAY_CARPETED_TILES = create("light_gray_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> CYAN_CARPETED_TILES = create("cyan_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_CYAN).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> PURPLE_CARPETED_TILES = create("purple_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> BLUE_CARPETED_TILES = create("blue_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> BROWN_CARPETED_TILES = create("brown_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> GREEN_CARPETED_TILES = create("green_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> RED_CARPETED_TILES = create("red_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);
    public static final RegistrySupplier<Block> BLACK_CARPETED_TILES = create("black_carpeted_tiles", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sound(SoundType.WOOL)), CreativeModeTabs.COLORED_BLOCKS);



    public static final RegistrySupplier<Block> SEANUT_BUSH = create("seanut_bush", () -> new SeanutBush(Block.Properties.of(Material.WATER_PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));
    public static final RegistrySupplier<Block> PINEAPPLE_PLANT = create("pineapple_plant", () -> new PineapplePlant(Block.Properties.of(Material.WATER_PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));

    public static final RegistrySupplier<Block> BUBBLE_BLOCK = create("bubble_block", () -> new BubbleBlock(Block.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));

    public static final RegistrySupplier<Block> GRILL = create("grill", () -> new GrillBlock(Block.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).sound(SoundType.METAL).strength(2F, 2F).requiresCorrectToolForDrops().noOcclusion()) { //Tool Iron PICKAXE
    }, CreativeModeTabs.FUNCTIONAL_BLOCKS);

    public static final RegistrySupplier<Block> POTTED_PINEAPPLE = create("potted_pineapple", () -> new FlowerPotBlock(PINEAPPLE_PLANT.get(), Block.Properties.copy(Blocks.FLOWER_POT)));


    //public static final RegistrySupplier<Block> JELLYFISH_MACHINE = create("jellyfish_machine", () -> new JellyfishMachineBlock(Block.Properties.create(Material.CLAY, MaterialColor.BROWN).sound(SoundType.METAL)), CreativeModeTabs.BUILDING_BLOCKS);
    //public static final RegistrySupplier<Item> JELLYFISH_MACHINE_ITEM = ITEMS.register("jellyfish_machine", () -> new BlockItemBase(JELLYFISH_MACHINE.get()), CreativeModeTabs.BUILDING_BLOCKS);

//    public static RegistrySupplier<Item> conditionallyRegisterItem(String registryName, Supplier<Item> item, Supplier<Boolean> condition) {
//        if (condition.get())
//            return JellyfishingItems.ITEMS.register(registryName, item);
//        return null;
//    }
//    public static RegistrySupplier<Block> conditionallyRegisterBlock(String registryName, Supplier<Block> block, Supplier<Boolean> condition) {
//        if (condition.get())
//            return create(registryName, block);
//        return null;
//    }

    private static <T extends Block> RegistrySupplier<T> create(String key, Supplier<T> block, CreativeModeTab tab) {
        return create(key, block, entry -> new BlockItem(entry.get(), new Item.Properties().arch$tab(tab)));
    }

    private static <T extends Block> RegistrySupplier<T> create(String key, Supplier<T> block, CreativeModeTab tab, CreativeModeTab tab2) {
        return create(key, block, entry -> new BlockItem(entry.get(), new Item.Properties().arch$tab(tab).arch$tab(tab2)));
    }

    private static <T extends Block> RegistrySupplier<T> create(String key, Supplier<T> block, Function<Supplier<T>, Item> item) {
        RegistrySupplier<T> entry = create(key, block);
        JellyfishingItems.ITEMS.register(key, () -> item.apply(entry));
        return entry;
    }

    private static <T extends Block> RegistrySupplier<T> create(String key, Supplier<T> block) {
        return BLOCKS.register(key, block);
    }
}