package model;

import model.interfaces.IDrawable;
import java.awt.*;

public class DrawFilledIn implements IDrawable {
    Color color;
    Shape shape;
    private Graphics2D graphics2d;

    public DrawFilledIn(Color color, Shape shape, Graphics2D graphics2d) {
        this.color = color;
        this.shape = shape;
        this.graphics2d = graphics2d;
    }

    @Override
    public void paintShape() {
        graphics2d.setPaint(color);
        graphics2d.fill(shape);
    }

    @Override
    public String toString() {
        return "DrawFilledIn{" +
                "graphics2d=" + graphics2d +
                ", color=" + color.toString() +
                ", shape=" + shape.toString() +
                '}';
    }
}
