package model.shapes;

import model.SelectBoundingBox;
import model.collection.ShapeRepository;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShapeUngroup implements IUndoable {
    PaintCanvasBase paintCanvas;
    List<ShapeGroup> groupShapeCollectionList;

    public ShapeUngroup(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;

        List<IShape> shapeCollectionList = ShapeRepository.shapeCollection.getList();

        SelectBoundingBox selectBoundingBox = SelectBoundingBox.getInstance();
        Shape selectBoundingBoxShape = selectBoundingBox.getBoundingBox();

        groupShapeCollectionList = shapeCollectionList.stream()
                .filter(ishape -> ishape instanceof ShapeGroup)
                .map(p -> (ShapeGroup) p)
                .filter(gs -> gs.detectCollision(selectBoundingBoxShape))
                .collect(Collectors.toList());
    }

    public void ungroup() {
        for(ShapeGroup shapeGroup: groupShapeCollectionList){
            shapeGroup.unGroup();
        }
        paintCanvas.repaint();
    }

    @Override
    public void undo() {
        for (ShapeGroup shapeGroup: groupShapeCollectionList){
            shapeGroup.create();
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        ungroup();
    }
}
