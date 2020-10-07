package model.commands;

import model.shapes.ShapeRedo;
import model.interfaces.ICommand;

public class RedoCommand implements ICommand {
    ShapeRedo redo;

    public RedoCommand(ShapeRedo redo) {
        this.redo = redo;
    }

    @Override
    public void run() {
        redo.operate();
    }
}
