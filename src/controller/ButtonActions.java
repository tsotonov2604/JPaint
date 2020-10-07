package controller;

import model.*;
import model.commands.*;
import model.shapes.ShapeRedo;
import model.shapes.ShapeUndo;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class ButtonActions implements IJPaintController {
    private final IUiModule uiModule;
    PaintObservable paintObservable;
    private PaintCanvasBase paintCanvas;

    public ButtonActions(IUiModule uiModule, PaintCanvasBase paintCanvas, PaintObservable paintObservable) {
        this.uiModule = uiModule;
        this.paintCanvas = paintCanvas;
        this.paintObservable = paintObservable;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    //EVENT HANDLERS
    private void setupEvents() {
        uiModule.addEvent(EventName.UNDO, () -> undoCommand());
        uiModule.addEvent(EventName.REDO, () -> redoCommand());
    }

    public void undoCommand() {
        ShapeUndo undo = new ShapeUndo(paintObservable);
        UndoCommand undoCommand = new UndoCommand(undo);
        undoCommand.run();
    }

    public void redoCommand() {
        ShapeRedo redo = new ShapeRedo(paintObservable);
        RedoCommand redoCommand = new RedoCommand(redo);
        redoCommand.run();
    }

}
