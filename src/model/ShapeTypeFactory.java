package model;

import model.interfaces.IShapeType;
import model.shapes.ShapeProperty;
import java.awt.*;

public class ShapeTypeFactory {

    public static Shape build(ShapeProperty shapeProperty) {
        Shape shape = null;

        ShapeType shapeType = shapeProperty.getShapeType();
        Integer shapeXcoord = shapeProperty.getShapeXcoord();
        Integer shapeYcoord = shapeProperty.getShapeYcoord();
        Integer width = shapeProperty.getWidth();
        Integer height = shapeProperty.getHeight();
        Point startPoint = shapeProperty.getStartPoint();
        Point endPoint = shapeProperty.getEndPoint();

        IShapeType shapeTypeStrategy = null;

        switch (shapeType) {
            case RECTANGLE:
                shapeTypeStrategy = new DrawRectangle(shapeXcoord, shapeYcoord, width, height);
                break;
            case ELLIPSE:
                shapeTypeStrategy = new DrawEllipse(shapeXcoord, shapeYcoord, width, height);
                break;
            case TRIANGLE:
                shapeTypeStrategy = new DrawRightTriangle(startPoint, endPoint);
                break;
        }
        return shape = shapeTypeStrategy.drawShape();
    }
}

