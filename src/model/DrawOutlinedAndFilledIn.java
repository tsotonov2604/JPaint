package model;

import model.interfaces.IDrawable;
import java.awt.*;

public class DrawOutlinedAndFilledIn implements IDrawable {
    BasicStroke stroke = new BasicStroke(5.0f);
    Shape shape;
    Color primaryColor;
    Color secondaryColor;
    private Graphics2D graphics2d;

    public DrawOutlinedAndFilledIn(Color primaryColor, Color secondaryColor, Shape shape, Graphics2D graphics2d) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shape = shape;
        this.graphics2d = graphics2d;
    }


    @Override
    public void paintShape() {
        // Outline
        graphics2d.setStroke(stroke);
        graphics2d.setPaint(secondaryColor);
        graphics2d.draw(shape);

        // Fill
        graphics2d.setPaint(primaryColor);
        graphics2d.fill(shape);
    }

    public void setStoke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    @Override
    public String toString() {
        return "OutlineAndFilledIn{" +
                "graphics2d=" + graphics2d +
                ", shape=" + shape.toString() +
                ", primaryColor=" + primaryColor.toString() +
                ", secondaryColor=" + secondaryColor.toString() +
                '}';
    }
}