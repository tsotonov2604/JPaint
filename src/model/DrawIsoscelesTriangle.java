package model;

import model.interfaces.IShapeType;
import java.awt.*;
import java.awt.geom.Path2D;

public class DrawIsoscelesTriangle implements IShapeType {
    Shape shape;
    Integer shapeXcoord;
    Integer shapeYcoord;
    Integer width;
    Integer height;

    public DrawIsoscelesTriangle(Integer shapeXcoord, Integer shapeYcoord, Integer width, Integer height) {
        this.shapeXcoord = shapeXcoord;
        this.shapeYcoord = shapeYcoord;
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape drawShape() {
        Path2D triangle = new Path2D.Double();

        //Isosceles triangle: two sides of equal length
        triangle.moveTo(shapeXcoord + (width / 2), shapeYcoord);
        triangle.lineTo(shapeXcoord, shapeYcoord + (height));
        triangle.lineTo(shapeXcoord + width, shapeYcoord + (height));
        triangle.closePath();

        return triangle;
    }
}