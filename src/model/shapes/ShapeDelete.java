package model.shapes;

import model.collection.ShapeRepository;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;
import java.util.List;

public class ShapeDelete implements IUndoable {
    private PaintCanvasBase paintCanvas;
    private List<IShape> deletedShapes = new ArrayList<IShape>();

    public ShapeDelete(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;
        deletedShapes.addAll(ShapeRepository.selectedCollection.getList());
    }

    public void delete(){
        for(IShape shapeToBeDeleted: deletedShapes){
            shapeToBeDeleted.deleteShape();
        }
        paintCanvas.repaint();
    }

    @Override
    public void undo() {
        for (IShape deletedShape: deletedShapes){
            deletedShape.create();
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        delete();
    }
}
