package model.commands;

import model.collection.ShapeRepository;
import model.shapes.ShapePaste;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class PasteCommand implements ICommand, IUndoable {
    ShapePaste shapePaste;

    public PasteCommand(ShapePaste shapePaste) {this.shapePaste =  shapePaste;}

    @Override
    public void run() {
        boolean result = !ShapeRepository.clipboardShapeCollection.getList().isEmpty();
        if (result) {
            shapePaste.paste();
            CommandHistory.add(this);
        }
    }

    @Override
    public void undo() {
        shapePaste.undo();
    }

    @Override
    public void redo() {
        shapePaste.redo();
    }
}
