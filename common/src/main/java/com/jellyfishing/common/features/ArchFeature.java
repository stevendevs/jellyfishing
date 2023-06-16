package com.jellyfishing.common.features;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//public class ArchFeature {
//
//    private List<BlockPos> controlPoints;
//    private List<BlockPos> archPoints;
//    private int radius;
//    private double tension;
//
//    public ArchFeature(List<BlockPos> controlPoints, int radius, double tension) {
//        this.controlPoints = controlPoints;
//        this.radius = radius;
//        this.tension = tension;
//        archPoints = new ArrayList<>();
//        calculateArchPoints();
//    }
//
//    private void calculateArchPoints() {
//        int numberOfControlPoints = controlPoints.size();
//
//        for (int i = 0; i < numberOfControlPoints - 3; i++) {
//            BlockPos p0 = controlPoints.get(i);
//            BlockPos p1 = controlPoints.get(i + 1);
//            BlockPos p2 = controlPoints.get(i + 2);
//            BlockPos p3 = controlPoints.get(i + 3);
//
//            for (double t = 0; t <= 1; t += 0.01) {
//                double x = 0.5 * ((2 * p1.getX()) +
//                        (-p0.getX() + p2.getX()) * t +
//                        (2 * p0.getX() - 5 * p1.getX() + 4 * p2.getX() - p3.getX()) * t * t +
//                        (-p0.getX() + 3 * p1.getX() - 3 * p2.getX() + p3.getX()) * t * t * t);
//                double y = 0.5 * ((2 * p1.getY()) +
//                        (-p0.getY() + p2.getY()) * t +
//                        (2 * p0.getY() - 5 * p1.getY() + 4 * p2.getY() - p3.getY()) * t * t +
//                        (-p0.getY() + 3 * p1.getY() - 3 * p2.getY() + p3.getY()) * t * t * t);
//                double z = 0.5 * ((2 * p1.getZ()) +
//                        (-p0.getZ() + p2.getZ()) * t +
//                        (2 * p0.getZ() - 5 * p1.getZ() + 4 * p2.getZ() - p3.getZ()) * t * t +
//                        (-p0.getZ() + 3 * p1.getZ() - 3 * p2.getZ() + p3.getZ()) * t * t * t);
//
//                BlockPos blockPos = new BlockPos((int) x, (int) y, (int) z);
//                archPoints.add(blockPos);
//            }
//        }
//    }
//
//    public List<BlockPos> getArchPoints() {
//        return archPoints;
//    }
//
//    public static void generateArch(LevelAccessor world, BlockPos center, int maxRadius) {
//        Random random = new Random();
//        int randomRadius = random.nextInt(maxRadius);
//
//        int i;
//        float t;
//        double x, y, z;
//        for (t = 0; t <= 1; t += 0.05){
//            x = (double)(randomRadius * (((-t + 2) * t - 1) * t * 0.5f + ((2 - t) * t + 1) * t * 0.5f));
//            y = (double)(randomRadius * (((2 * t - 3) * t * t + 1) * 0.5f + ((3 - 2 * t) * t * t) * 0.5f));
//            z = (double)(randomRadius * (((-t + 2) * t - 1) * t * 0.5f + ((2 * t - 1) * t + 1) * t * 0.5f));
//            world.setBlock(center.offset((int) x, (int) y, (int) z), /*blockstate*/, /*updateNeighbors*/);
//        }
//    }
//}

public class ArchFeature {
    private static final double SMOOTHNESS = 0.5;
    private final BlockPos center;

    public ArchFeature(BlockPos center) {
        this.center = center;
    }

    public BlockPos getPoint(double t) {
        double x = (1.0 / 2.0 * ((2.0 * center.getX()) + (t * (center.getZ() - center.getX())) + (t * (center.getY() - center.getZ()))))
                - (1.0 / 2.0 * ((2.0 * center.getY()) + (t * (center.getX() - center.getY())) + (t * (center.getZ() - center.getX()))));
        double y = (1.0 / 2.0 * ((2.0 * center.getY()) + (t * (center.getX() - center.getY())) + (t * (center.getZ() - center.getX()))))
                - (1.0 / 2.0 * ((2.0 * center.getZ()) + (t * (center.getY() - center.getZ())) + (t * (center.getX() - center.getY()))));
        double z = (1.0 / 2.0 * ((2.0 * center.getZ()) + (t * (center.getY() - center.getZ())) + (t * (center.getX() - center.getY()))))
                - (1.0 / 2.0 * ((2.0 * center.getX()) + (t * (center.getZ() - center.getX())) + (t * (center.getY() - center.getZ()))));
        return new BlockPos(x, y, z);
    }
}