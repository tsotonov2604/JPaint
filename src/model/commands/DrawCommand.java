package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;

public class DrawCommand implements ICommand, IUndoable {

    IDrawShape drawShape;

    public DrawCommand(IDrawShape shapeCreator) {
        this.drawShape = shapeCreator;
    }

    @Override
    public void run() {
        drawShape.draw();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        ((IUndoable) drawShape).undo();
    }

    @Override
    public void redo() {
        ((IUndoable) drawShape).redo();
    }
}
