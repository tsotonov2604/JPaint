package model.modes;

import model.DrawShape;
import model.ShapeAwtColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.commands.DrawCommand;
import model.interfaces.IApplicationState;
import model.interfaces.IRun;
import model.shapes.ShapeProperty;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class DrawMode implements IRun {

    ShapeProperty shapeProperty;
    private Point startPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvas;
    private IApplicationState appState;

    public DrawMode(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas, IApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;

        this.startPoint = startPoint;
        this.endPoint = endPoint;

        ShapeType shapeType = appState.getActiveShapeType();
        ShapeShadingType shadingType = appState.getActiveShapeShadingType();
        Color primaryColor = ShapeAwtColor.getColor(appState.getActivePrimaryColor());
        Color secondaryColor = ShapeAwtColor.getColor(appState.getActiveSecondaryColor());

        shapeProperty = new ShapeProperty(startPoint, endPoint);
        shapeProperty
                .setShapeType(shapeType)
                .setShadingType(shadingType)
                .setPrimaryColor(primaryColor)
                .setSecondaryColor(secondaryColor);
    }

    public void run() {
        if (!startPoint.equals(endPoint)) {
            DrawShape drawShape = new DrawShape(paintCanvas, shapeProperty);
            DrawCommand drawShapeCommand = new DrawCommand(drawShape);
            drawShapeCommand.run();
        }
    }
}
