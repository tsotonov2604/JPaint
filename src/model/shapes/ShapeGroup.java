package model.shapes;

import model.SelectBoundingBox;
import model.collection.ShapeRepository;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup implements IShape, IUndoable {

    List<IShape> groupedShapes = new ArrayList<>();
    private PaintCanvasBase paintCanvas;
    private Graphics2D graphics2d;
    private Shape boundingBox;

    public ShapeGroup(ShapeGroup shapeGroup){
        this.paintCanvas = shapeGroup.paintCanvas;
        this.graphics2d = shapeGroup.paintCanvas.getGraphics2D();

        for (IShape groupedShapes : shapeGroup.getList()) {
            this.add(groupedShapes.copyShape());
        }
        this.createBoundingBox();

    }

    public ShapeGroup(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;
        this.graphics2d = paintCanvas.getGraphics2D();
    }

    public void add(IShape iShape) {
        if (!groupedShapes.contains(iShape)) {
            groupedShapes.add(iShape);

            this.createBoundingBox();
        }
    }

    public void remove(IShape iShape) {
        groupedShapes.add(iShape);
        this.createBoundingBox();
    }

    public List<IShape> getList() {
        return groupedShapes;
    }

    public void group() {
        SelectBoundingBox selectBoundingBox = SelectBoundingBox.getInstance();

        List<IShape> selectedCollectionList = ShapeRepository.selectedCollection.getList();

        if(selectedCollectionList != null){
            for (IShape iShape : selectedCollectionList){
                this.add(iShape);
            }
            ShapeRepository.selectedCollection.removeAll(this.groupedShapes);
            ShapeRepository.selectedCollection.add(this);

            //Adds the group to the shape list
            create();

            //Draws outline
            drawSelectionBox();
        }
    }

    public void drawSelectionBox() {
        graphics2d.setStroke(new BasicStroke(8.0f));
        graphics2d.setPaint(Color.YELLOW);
        graphics2d.draw(this.getBoundingBox());
        graphics2d.setColor(Color.black);
        graphics2d.setFont(new Font("default", Font.BOLD, 14));
        graphics2d.drawString(" (Group Selection Box)", this.getBoundingBox().getBounds().x, this.getBoundingBox().getBounds().y);
    }

    public void unGroup() {
        for (IShape iShape: groupedShapes){
            ShapeRepository.shapeCollection.add(iShape);
        }
        ShapeRepository.shapeCollection.remove(this);
        ShapeRepository.selectedCollection.remove(this);
    }

    private void createBoundingBox() {
        List<Shape> groupShapeBounds = new ArrayList<>();

        for (IShape group : groupedShapes) {
            Shape rect = group.getBoundingBox();
            if (!groupShapeBounds.contains(rect)) {
                groupShapeBounds.add(group.getBoundingBox());
            }
        }

        ShapeBoundingBox shapeBoundingBox = new ShapeBoundingBox(groupShapeBounds);
        this.boundingBox = shapeBoundingBox.getBoundingBox();
    }


    @Override
    public void paintShapeOnCanvas() {
        for (IShape iShape : groupedShapes) {
            iShape.setGraphics2d(graphics2d);
            iShape.paintShapeOnCanvas();
        }
    }

    @Override
    public void setGraphics2d(Graphics2D graphics2d) {
        this.graphics2d = graphics2d;
    }

    @Override
    public void highlightShape() {
        for (IShape iShape : groupedShapes) {
            iShape.highlightShape();
        }
    }

    @Override
    public boolean detectCollision(Shape otherShape) {
        createBoundingBox();
        boolean collisionDetected = ShapeCollision.detect(boundingBox, otherShape);

        return collisionDetected;
    }

    @Override
    public void create() {
        ShapeRepository.shapeCollection.add(this);

        for (IShape iShape : this.groupedShapes) {
            ShapeRepository.shapeCollection.remove(iShape);
        }

        this.createBoundingBox();
    }

    @Override
    public void moveShape(int transformOffsetX, int transformOffsetY) {
        AffineTransform transform = new AffineTransform();

        transform.translate(transformOffsetX, transformOffsetY);

        boundingBox = transform.createTransformedShape(boundingBox);

        for (IShape iShape : groupedShapes) {
            iShape.moveShape(transformOffsetX, transformOffsetY);
        }
    }

    @Override
    public IShape copyShape() {
        ShapeGroup shapeGroup = new ShapeGroup(this);
        return shapeGroup;
    }

    @Override
    public IShape pasteShape() {
        ShapeGroup shapeGroup = new ShapeGroup(paintCanvas);
        for(IShape iShape : groupedShapes){
            shapeGroup.add(iShape.pasteShape());
        }
        shapeGroup.create();
        return shapeGroup;
    }

    @Override
    public void deleteShape() {
        unGroup();
        for (IShape iShape : groupedShapes) {
            iShape.deleteShape();
        }
    }

    @Override
    public List<IShape> getNodeList() {
        List<IShape> newList = new ArrayList<>();

        for (IShape iShape : groupedShapes) {
            newList.addAll(iShape.getNodeList());
        }
        return newList;
    }

    @Override
    public Shape getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void undo() {
        unGroup();
    }

    @Override
    public void redo() {
        create();
    }
}
