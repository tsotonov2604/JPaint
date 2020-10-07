package model.commands;

import model.shapes.ShapeUndo;
import model.interfaces.ICommand;

public class UndoCommand implements ICommand {
    ShapeUndo undo;

    public UndoCommand(ShapeUndo undo) {
        this.undo = undo;
    }

    @Override
    public void run() {
        undo.operate();
    }
}
