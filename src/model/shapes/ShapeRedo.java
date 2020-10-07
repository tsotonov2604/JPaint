package model.shapes;

import model.PaintObservable;
import model.commands.CommandHistory;
import view.interfaces.PaintCanvasBase;

public class ShapeRedo {
    PaintObservable paintObservable;
    private PaintCanvasBase paintCanvas;

    public ShapeRedo(PaintObservable paintObservable) {
        this.paintObservable = paintObservable;
    }

    public void operate() {
        CommandHistory.redo();
        paintObservable.notifyUpdate();
    }
}
