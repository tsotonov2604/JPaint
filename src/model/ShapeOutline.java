package model;

import model.shapes.ShapeSelectionOutline;
import java.awt.*;

public class ShapeOutline {

    public static ShapeOutline instance;
    private Shape boundingBox;

    private ShapeOutline() {
    }

    public static ShapeOutline getInstance() {
        if (instance == null)
            instance = new ShapeOutline();
        return instance;
    }

    public Shape generateFromPoints(Point startPoint, Point endPoint) {
        ShapeSelectionOutline shapeBoundingBox = new ShapeSelectionOutline();
        this.boundingBox = shapeBoundingBox.generateSelection(startPoint, endPoint);

        return this.boundingBox;
    }

    public Shape getOutline() {
        return boundingBox;
    }

    public void drawOutline(Graphics2D graphics2d) {
        graphics2d.setStroke(new BasicStroke(5.0f));
        graphics2d.setPaint(Color.green);
        graphics2d.draw(boundingBox);
        graphics2d.setColor(Color.black);
        graphics2d.setFont(new Font("Default", Font.BOLD, 16));

        float alpha = 0.5f;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        graphics2d.setComposite(ac);

        graphics2d.drawString("Outline", boundingBox.getBounds().x, boundingBox.getBounds().y);
    }
}