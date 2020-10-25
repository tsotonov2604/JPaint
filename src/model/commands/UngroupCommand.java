package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.ShapeUngroup;

public class UngroupCommand implements ICommand, IUndoable {
    ShapeUngroup shapeUngroup;

    public UngroupCommand(ShapeUngroup shapeUngroup){this.shapeUngroup = shapeUngroup;}

    @Override
    public void run() {
        shapeUngroup.ungroup();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        shapeUngroup.undo();
    }

    @Override
    public void redo() {
        shapeUngroup.redo();
    }
}
