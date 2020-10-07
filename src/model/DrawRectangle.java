package model;

import model.interfaces.IShapeType;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawRectangle implements IShapeType {

    Integer xCoord;
    Integer yCoord;
    Integer width;
    Integer height;

    public DrawRectangle(Integer shapeXcoord, Integer shapeYcoord, Integer width, Integer height) {
        this.xCoord = shapeXcoord;
        this.yCoord = shapeYcoord;
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape drawShape() {
        return new Rectangle2D.Double(xCoord, yCoord, width, height);
    }
}