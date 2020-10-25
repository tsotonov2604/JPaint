package model;

import model.shapes.ShapeBoundingBox;
import java.awt.*;

public class SelectBoundingBox {

    public static SelectBoundingBox instance;
    private Shape boundingBox;

    private SelectBoundingBox() {
    }

    public static SelectBoundingBox getInstance() {
        if (instance == null)
            instance = new SelectBoundingBox();
        return instance;
    }

    public Shape generateFromPoints(Point startPoint, Point endPoint) {
        ShapeBoundingBox shapeBoundingBox = new ShapeBoundingBox();
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

    public Shape getBoundingBox() {
        return boundingBox;
    }
}