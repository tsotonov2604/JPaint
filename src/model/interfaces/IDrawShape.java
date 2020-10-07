package model.interfaces;

import model.shapes.ShapeProperty;
import java.awt.*;

public interface IDrawShape {
    void designShape();

    void draw();

    Shape getShape();

    void setShape(Shape shape);

    ShapeProperty getShapeProperty();

    void updateShapeProperty(Shape newShape);

}

