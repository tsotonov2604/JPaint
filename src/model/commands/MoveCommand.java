package model.commands;

import model.MoveShape;
import model.collection.ShapeRepository;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import java.awt.*;

public class MoveCommand implements ICommand, IUndoable {
    MoveShape moveShape;

    public MoveCommand(MoveShape moveShape) {
        this.moveShape = moveShape;
    }

    @Override
    public void run() {
        boolean result = !ShapeRepository.selectedCollection.getList().isEmpty();
        if (result) {
            moveShape.move();

            CommandHistory.add(this);
        }
    }

    @Override
    public void undo() {
        moveShape.undo();
    }

    @Override
    public void redo() {
        moveShape.redo();
    }
}
