package com.jellyfishing.common.features;

import com.jellyfishing.core.registry.JellyfishingBlocks;
import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.filler.SimpleFiller;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.DilateLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.RotateLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terraform.shapes.impl.validator.SafelistValidator;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Arrays;
import java.util.List;

public class ArchFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public ArchFeatureConfiguration(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        int i = 0;
        BlockState VIOLITE = JellyfishingBlocks.CORALSTONE.get().defaultBlockState();
        List<BlockState> WHITELIST = Arrays.asList(VIOLITE, Blocks.AIR.defaultBlockState(), JellyfishingBlocks.ALGAE_GRASS.get().defaultBlockState());
        WorldGenLevel worldGenLevel = context.level();
        BlockPos blockPos = context.origin();
        RandomSource randomSource = context.random();

        int randomX = randomSource.nextInt(8) - randomSource.nextInt(8);
        int randomZ = randomSource.nextInt(8) - randomSource.nextInt(8);

        int j = worldGenLevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockPos.getX() + randomX, blockPos.getZ() + randomZ);
        BlockPos blockPos2 = new BlockPos(blockPos.getX() + randomX, j, blockPos.getZ() + randomZ);

        if (worldGenLevel.getBlockState(blockPos2).is(Blocks.WATER)) {
            for (int l = 0; l <= 1; ++l) {
                if (worldGenLevel.getBlockState(blockPos2).is(Blocks.WATER) && worldGenLevel.getBlockState(blockPos2.above()).is(Blocks.WATER)) {
                    double length = (randomSource.nextDouble() * 15) + 20;
                    double radius = (randomSource.nextDouble() * 6) + 4;
                    double arc = 80 + Mth.lerp(randomSource.nextDouble(), -10, 10);
                    double arc2 = 80 + Mth.lerp(randomSource.nextDouble(), -10, 10);
                    double scale = Mth.lerp(randomSource.nextDouble(), 0.7, 1.3);
                    double downY = randomSource.nextDouble() * (length / 5) + 1;

                    Shape shape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0));

                    double nextArchPos = -new BendLayer(arc, radius, length).modifyMin(shape).getX();

                    boolean suitable = false;
                    int tries = 0;

                    double fullRotation = randomSource.nextDouble() * 360;
                    BlockPos nextArch = blockPos.offset(-Math.cos(Math.toRadians(fullRotation)) * nextArchPos * scale, blockPos.getY(), Math.sin(Math.toRadians(fullRotation)) * nextArchPos * scale);

                    while (!suitable && tries <= 360) {
                        fullRotation += 1;
                        nextArch = blockPos.offset(-Math.cos(Math.toRadians(fullRotation)) * nextArchPos * scale, 0, Math.sin(Math.toRadians(fullRotation)) * nextArchPos * scale);

                        for (BlockPos boxPos : BlockPos.betweenClosed(nextArch.offset(0, -3, 0), nextArch.offset(0, 3, 0))) {
                            if (!suitable) {
                                suitable = worldGenLevel.getBlockState(boxPos).is(VIOLITE.getBlock());
                            } else {
                                break;
                            }
                        }
                        tries++;
                    }

                    if (suitable) {

                        nextArchPos = (-Math.abs(blockPos2.distManhattan(nextArch))) + (length / 3);

                        /* Shape */
                        shape = shape.applyLayer(new AddLayer(
                                /* Shape */
                                Shapes.ellipticalPyramid(radius, radius, length)
                                        /* Bend */
                                        .applyLayer(new BendLayer(arc, radius, length))
                                        /* Scale */
                                        .applyLayer(new DilateLayer(Position.of(scale, scale, scale)))));

                        /* Shape */
                        shape = shape.applyLayer(new AddLayer(
                                /* Shape */
                                Shapes.ellipticalPyramid(radius, radius, length)
                                        /* Bend */
                                        .applyLayer(new BendLayer(arc2, radius, length))
                                        /* Rotate */
                                        .applyLayer(new RotateLayer(Quaternion.of(0, 180, 0, true)))
                                        /* Scale */
                                        .applyLayer(new DilateLayer(Position.of(scale, scale, scale)))
                                        /* Movement */
                                        .applyLayer(new TranslateLayer(Position.of(nextArchPos, 0, 0)))));

                        /* Rotation */
                        shape = shape.applyLayer(new RotateLayer(Quaternion.of(0, fullRotation, 0, true)));

                        /* Movement */
                        shape = shape.applyLayer(new TranslateLayer(Position.of(blockPos2)));

                        /* Movement */
                        shape = shape.applyLayer(new TranslateLayer(Position.of(0, -downY, 0)));

                        shape.validate(new SafelistValidator(worldGenLevel, WHITELIST), (validShape) -> {
                            validShape.fill(new SimpleFiller(worldGenLevel, VIOLITE));
                        });
                        ++i;
                    }
                }
            }
        }
        return i > 0;
    }
}

