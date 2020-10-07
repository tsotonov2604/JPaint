package model.modes;

import model.MoveShape;
import model.PaintObservable;
import model.commands.MoveCommand;
import model.interfaces.IApplicationState;
import model.interfaces.IRun;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class MoveMode implements IRun {
    MoveShape moveShape;
    PaintObservable paintObservable = new PaintObservable();
    private Point startPoint;
    private Point endPoint;
    private Point transformPos;
    private PaintCanvasBase paintCanvas;
    private IApplicationState appState;


    public MoveMode(Point startPoint, Point transformPos, PaintCanvasBase paintCanvas, IApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;

        this.transformPos = transformPos;
        this.startPoint = startPoint;
    }

    public void run() {
        moveShape = new MoveShape(startPoint, transformPos, paintCanvas, appState, paintObservable);
        moveShape.move();
    }

    public void movement(Point endPoint) {
        moveShape.setEndPoint(endPoint);
        MoveCommand moveShapeCommand = new MoveCommand(moveShape);
        moveShapeCommand.run();
    }
}