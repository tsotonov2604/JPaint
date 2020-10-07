package model.shapes;

import model.PaintObservable;
import model.commands.CommandHistory;
import view.interfaces.PaintCanvasBase;

public class ShapeUndo {
    PaintObservable paintObservable;
    private PaintCanvasBase paintCanvas;

    public ShapeUndo(PaintObservable paintObservable) {
        this.paintObservable = paintObservable;
    }

    public void operate() {
        CommandHistory.undo();
        paintObservable.notifyUpdate();
    }
}
