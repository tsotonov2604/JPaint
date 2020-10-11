package model.shapes;
import model.collection.ShapeRepository;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.util.List;

public class ShapeCopy {
    List<IShape> selectedList = ShapeRepository.selectedCollection.getList();
    List<IShape> clipBoardList = ShapeRepository.clipboardShapeCollection.getList();
    private PaintCanvasBase paintCanvas;

    public ShapeCopy(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    public void copy() {
        clipBoardList.clear();

        for (IShape selectedShape : selectedList) {
            IShape copiedShape = selectedShape.copyShape();
            clipBoardList.add(copiedShape);
        }
    }
}
