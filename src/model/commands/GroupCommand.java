package model.commands;

import model.collection.ShapeRepository;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.ShapeGroup;

public class GroupCommand implements ICommand, IUndoable {
    ShapeGroup shapeGroup;

    public GroupCommand(ShapeGroup shapeGroup){
        this.shapeGroup = shapeGroup;
    }

    @Override
    public void run() {
        boolean result = !ShapeRepository.selectedCollection.getList().isEmpty();
        if (result) {
            shapeGroup.group();
            CommandHistory.add(this);
        }
    }

    @Override
    public void undo() {
        shapeGroup.undo();
    }

    @Override
    public void redo() {
        shapeGroup.redo();
    }
}
