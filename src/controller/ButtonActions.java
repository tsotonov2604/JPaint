package controller;

import model.*;
import model.commands.*;
import model.shapes.*;
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
        uiModule.addEvent(EventName.COPY, () -> copyCommand());
        uiModule.addEvent(EventName.PASTE, () -> pasteCommand());
        uiModule.addEvent(EventName.DELETE,() -> deleteCommand());
        uiModule.addEvent(EventName.GROUP, () -> processGroup());
        uiModule.addEvent(EventName.UNGROUP, () -> processUngroup());
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

    public void copyCommand() {
        ShapeCopy shapeCopy = new ShapeCopy(paintCanvas);
        CopyCommand copyCommand = new CopyCommand(shapeCopy);
        copyCommand.run();
    }

    public void pasteCommand() {
        ShapePaste shapePaste = new ShapePaste(paintCanvas);
        PasteCommand pasteCommand = new PasteCommand(shapePaste);
        pasteCommand.run();
    }

    public void deleteCommand() {
        ShapeDelete shapeDelete = new ShapeDelete(paintCanvas);
        DeleteCommand deleteCommand = new DeleteCommand(shapeDelete);
        deleteCommand.run();

    }

    public void processGroup() {
        ShapeGroup shapeGroup = new ShapeGroup(paintCanvas);
        GroupCommand groupCommand = new GroupCommand(shapeGroup);
        groupCommand.run();
    }

    public void processUngroup() {
       ShapeUngroup shapeUngroup = new ShapeUngroup(paintCanvas);
       UngroupCommand ungroupCommand = new UngroupCommand(shapeUngroup);
       ungroupCommand.run();

  }

}
