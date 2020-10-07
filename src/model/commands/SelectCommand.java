package model.commands;

import model.SelectShape;
import model.collection.ShapeRepository;
import model.interfaces.ICommand;


public class SelectCommand implements ICommand {
    SelectShape selectShape;

    public SelectCommand(SelectShape selectShape) {
        this.selectShape = selectShape;
    }

    @Override
    public void run() {
        selectShape.runSelection();

        boolean result = !ShapeRepository.selectedCollection.getList().isEmpty();

    }

}
