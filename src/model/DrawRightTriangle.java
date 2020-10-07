package model;

import model.interfaces.IShapeType;
import java.awt.*;
import java.awt.geom.Path2D;

public class DrawRightTriangle implements IShapeType {
    Shape shape;
    private Point startPoint, endPoint;

    public DrawRightTriangle(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public Shape drawShape() {

        Path2D triangle = new Path2D.Double();

        triangle.moveTo(startPoint.x, startPoint.y);

        triangle.lineTo(startPoint.x, endPoint.y);

        triangle.lineTo(endPoint.x, endPoint.y);

        triangle.closePath();

        return triangle;
    }

    @Override
    public String toString() {
        return "DrawRightTriangle{" +
                "shape=" + shape +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}