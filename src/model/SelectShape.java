package model;

import model.collection.ShapeRepository;
import model.interfaces.IApplicationState;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectShape implements IUndoable {

    private Point startPoint, endPoint;
    private Graphics2D graphics2d;
    private PaintCanvasBase paintCanvas;
    private IApplicationState appState;

    public SelectShape(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas, IApplicationState appState) {
        this.graphics2d = paintCanvas.getGraphics2D();
        this.paintCanvas = paintCanvas;
        this.appState = appState;

        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    public void runSelection() {
        ShapeRepository.selectedCollection.clear();

        List<IShape> shapeList = ShapeRepository.shapeCollection.getList();
        ArrayList<IShape> tempList = new ArrayList<>(shapeList);

        ShapeOutline selectBoundingBox = ShapeOutline.getInstance();
        selectBoundingBox.generateFromPoints(startPoint, endPoint);


        //rectangle outline
        selectBoundingBox.drawOutline(graphics2d);

        Shape selectBoundingBoxShape = selectBoundingBox.getOutline();

        for (IShape shape : shapeList) {
            if (shape.detectCollision(selectBoundingBoxShape)) {
                ShapeRepository.selectedCollection.add(shape);
            }
        }

        boolean result = !ShapeRepository.shapeCollection.getList().isEmpty();

        paintCanvas.repaint();
    }

    public void undo() {
        ShapeRepository.selectedCollection.clear();
        paintCanvas.repaint();
    }

    public void redo() {
        runSelection();
        paintCanvas.repaint();
    }
}
