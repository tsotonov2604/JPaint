package model;

import model.collection.ShapeRepository;
import model.interfaces.IApplicationState;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class MoveShape implements IUndoable {

    PaintObservable paintObservable;
    private Point startPoint;
    private Point endPoint = new Point();
    private Point transformOffset;
    private Graphics2D graphics2d;
    private PaintCanvasBase paintCanvas;
    private IApplicationState appState;

    public MoveShape(Point startPoint, Point transformOffset, PaintCanvasBase paintCanvas, IApplicationState appState, PaintObservable paintObservable) {
        this.graphics2d = paintCanvas.getGraphics2D();
        this.paintCanvas = paintCanvas;
        this.appState = appState;

        this.transformOffset = transformOffset;
        this.startPoint = startPoint;

        this.paintObservable = paintObservable;
        paintObservable.addObserver((IObserver) paintCanvas);
    }

    public void move() {
        executeMove(transformOffset.x, transformOffset.y);
    }

    public void executeMove(int x, int y) {
        AffineTransform transform = new AffineTransform();

        transform.translate(x, y);

        for (IShape selectedShape : ShapeRepository.selectedCollection.getList()) {
            selectedShape.moveShape(x, y);
        }
        paintObservable.notifyUpdate();
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }


    @Override
    public void undo() {
        int offsetXX = startPoint.x - endPoint.x;
        int offsetYY = startPoint.y - endPoint.y;

        executeMove(offsetXX, offsetYY);
    }


    @Override
    public void redo() {
        int translateXX = endPoint.x - startPoint.x;
        int translateYY = endPoint.y - startPoint.y;

        executeMove(translateXX, translateYY);
    }
}