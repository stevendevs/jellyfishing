package com.jellyfishing.common.features;

import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.RotateLayer;
import net.minecraft.util.Mth;

import java.util.function.Predicate;

public class BendLayer implements Layer {

    private final double arc;
    private final double width;
    private final double height;

    public BendLayer(double arc, double width, double height) {
        this.arc = arc;
        this.width = width;
        this.height = height;
    }

    public static BendLayer of(double arc, double width, double height) {
        return new BendLayer(arc, width, height);
    }

    @Override
    public Position modifyMax(Shape shape) {
        Position pos = shape.max();
        pos.setY(Mth.lerp(reverseLerp(0, 360, arc), pos.getY(), width * 2));
        return pos;
    }

    @Override
    public Position modifyMin(Shape shape) {
        Position pos = shape.min();
        pos.setX(pos.getX() - Math.sin(Math.toRadians(Math.min(arc, 145))) * height);
        double moss = modifyMax(shape).getY();
        pos.setY(-Mth.lerp(reverseLerp(0, 360, arc), moss, height - moss));
        return pos;
    }

    @Override
    public Predicate<Position> modifyEquation(Shape shape) {
        return (pos) -> new RotateLayer(getRotation(pos)).modifyEquation(shape).test(pos);
    }

    private double getDist(Position pos) {
        return Math.sqrt(pos.getX() * pos.getX() + pos.getY() + pos.getY() + pos.getZ() + pos.getZ());
    }

    private Quaternion getRotation(Position pos) {
        return Quaternion.of(0, 0, arc * (getDist(pos) / height), true);
    }

    private double reverseLerp(double start, double end, double place) {
        return ((place - start) / (end - start));
    }

}