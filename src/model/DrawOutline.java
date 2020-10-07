package model;

import model.interfaces.IDrawable;
import java.awt.*;

public class DrawOutline implements IDrawable {
    BasicStroke stroke = new BasicStroke(5.0f);
    Shape shape;
    Color color;
    private Graphics2D graphics2d;

    public DrawOutline(Color color, Shape shape, Graphics2D graphics2d) {
        this.shape = shape;
        this.graphics2d = graphics2d;
        this.color = color;
    }

    @Override
    public void paintShape() {
        // Outline
        graphics2d.setStroke(stroke);
        graphics2d.setPaint(color);
        graphics2d.draw(shape);
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    @Override
    public String toString() {
        return "Outline{" +
                "graphics2d=" + graphics2d +
                ", shape=" + shape.toString() +
                ", color=" + color.toString() +
                '}';
    }


}
