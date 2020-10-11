package model.commands;
import model.shapes.ShapeCopy;
import model.interfaces.ICommand;

public class CopyCommand implements ICommand {
        ShapeCopy shapeCopy;

        public CopyCommand(ShapeCopy shapeCopy) {
            this.shapeCopy = shapeCopy;
        }

        @Override
        public void run() {
            shapeCopy.copy();
        }
}

