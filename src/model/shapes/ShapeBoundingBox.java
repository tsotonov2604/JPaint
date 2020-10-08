package model.shapes;

import model.ShapeType;
import model.ShapeTypeFactory;
import model.interfaces.IBoundingBox;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeBoundingBox implements IBoundingBox {
    List<Shape> list;
    Shape boundingBox;

    public ShapeBoundingBox(List<Shape> list) {
        this.list = list;
        updateSelection();
    }

    public ShapeBoundingBox() {
    }

    public Shape generateFromList(List<Shape> shapeList) {
        List<Integer> listStartXCoord = new ArrayList<>();
        List<Integer> listStartYCoord = new ArrayList<>();

        List<Integer> listEndXCoord = new ArrayList<>();
        List<Integer> listEndYCoord = new ArrayList<>();

        for (Shape shape : shapeList) {
            Rectangle shapeBoundingRec = shape.getBounds();
            listStartXCoord.add(shapeBoundingRec.x);
            listStartYCoord.add(shapeBoundingRec.y);

            listEndXCoord.add(shapeBoundingRec.x + shapeBoundingRec.width);
            listEndYCoord.add(shapeBoundingRec.y + shapeBoundingRec.height);
        }

        int xMin = Collections.min(listStartXCoord);
        int yMin = Collections.min(listStartYCoord);
        int xMax = Collections.max(listEndXCoord);
        int yMax = Collections.max(listEndYCoord);


        Point startPoint = new Point(xMin, yMin);
        Point endPoint = new Point(xMax, yMax);

        return generateSelection(startPoint, endPoint);
    }

    public void updateSelection() {
        this.boundingBox = generateFromList(list);
    }

    public Shape generateSelection(Point startPoint, Point endPoint) {
        ShapeProperty shapeProperty = new ShapeProperty(startPoint, endPoint);
        shapeProperty.setShapeType(ShapeType.RECTANGLE);

        Shape shape = ShapeTypeFactory.build(shapeProperty);

        return shape;
    }

}
