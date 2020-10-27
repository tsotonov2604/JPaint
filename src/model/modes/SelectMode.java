package model.modes;

import model.ShapeSelect;
import model.commands.SelectCommand;
import model.interfaces.IApplicationState;
import model.interfaces.IRun;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class SelectMode implements IRun {

    private Point startPoint;
    private Point endPoint;

    private PaintCanvasBase paintCanvas;
    private IApplicationState appState;

    public SelectMode(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas, IApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;

        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void run() {
        ShapeSelect selectShape = new ShapeSelect(startPoint, endPoint, paintCanvas, appState);
        SelectCommand selectShapeCommand = new SelectCommand(selectShape);
        selectShapeCommand.run();
    }
}
