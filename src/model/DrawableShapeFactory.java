package model;

import model.interfaces.IDrawable;

import java.awt.*;

public class DrawableShapeFactory {

    public static IDrawable outLineAndFilledShape(Color primaryColor, Color secondaryColor, Shape shape, Graphics2D g2) {
        return new DrawOutlinedAndFilledIn(primaryColor, secondaryColor, shape, g2);
    }

    public static IDrawable filledShape(Color primaryColor, Shape shape, Graphics2D g2) {
        return new DrawFilledIn(primaryColor, shape, g2);
    }

    public static IDrawable outlineShape(Color primaryColor, Shape shape, Graphics2D g2) {
        return new DrawOutline(primaryColor, shape, g2);
    }
}