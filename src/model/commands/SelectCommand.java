package model.commands;

import model.ShapeSelect;
import model.collection.ShapeRepository;
import model.interfaces.ICommand;


public class SelectCommand implements ICommand {
    ShapeSelect selectShape;

    public SelectCommand(ShapeSelect selectShape) {
        this.selectShape = selectShape;
    }

    @Override
    public void run() {
        selectShape.runSelection();

        boolean result = !ShapeRepository.selectedCollection.getList().isEmpty();

    }

}
