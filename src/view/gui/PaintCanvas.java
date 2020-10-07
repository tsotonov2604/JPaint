package view.gui;

import model.collection.ShapeRepository;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.List;

public class PaintCanvas extends PaintCanvasBase implements IObserver {

    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        List<IShape> shapeCollectionList = ShapeRepository.shapeCollection.getList();
        List<IShape> selectedCollectionList = ShapeRepository.selectedCollection.getList();


        for (IShape shapeItem : shapeCollectionList) {
            shapeItem.setGraphics2d(g2);

            shapeItem.paintShapeOnCanvas();

            //highlight selection
            if (selectedCollectionList.contains(shapeItem)) {
                shapeItem.highlightShape();
            }
        }

    }

    @Override
    public void update() {
        this.repaint();
    }
}
