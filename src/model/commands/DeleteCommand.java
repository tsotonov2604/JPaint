package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.ShapeDelete;

public class DeleteCommand implements ICommand, IUndoable {
    ShapeDelete shapeDelete;

    public DeleteCommand(ShapeDelete shapeDelete) {
        this.shapeDelete = shapeDelete;
    }

    @Override
    public void run() {
        shapeDelete.delete();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        shapeDelete.undo();
    }

    @Override
    public void redo() {
        shapeDelete.redo();
    }
}
