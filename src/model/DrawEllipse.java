package model;

import model.interfaces.IShapeType;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DrawEllipse implements IShapeType {

    Integer xCoord;
    Integer yCoords;
    Integer width;
    Integer height;

    public DrawEllipse(Integer shapeXcoord, Integer shapeYcoord, Integer width, Integer height) {
        this.xCoord = shapeXcoord;
        this.yCoords = shapeYcoord;
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape drawShape() {
        return new Ellipse2D.Double(xCoord, yCoords, width, height);
    }
}