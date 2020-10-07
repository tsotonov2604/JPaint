package model;

import model.collection.ShapeRepository;
import model.interfaces.*;
import model.shapes.ShapeCollision;
import model.shapes.ShapeProperty;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class DrawShape implements IShape, IDrawShape, IUndoable {

    Shape shape = null;
    IShapeType shapeTypeStrategy = null;
    private PaintCanvasBase paintCanvas;
    private ShapeProperty shapeProperty;
    private Graphics2D graphics2d;


    public DrawShape(IDrawShape ds, PaintCanvasBase paintCanvas) {
        ShapeProperty dsShapeProp = ds.getShapeProperty();

        this.shapeProperty = new ShapeProperty(dsShapeProp.getStartPoint(), dsShapeProp.getEndPoint());

        shapeProperty
                .setShapeType(dsShapeProp.getShapeType())
                .setShadingType(dsShapeProp.getShadingType())
                .setPrimaryColor(dsShapeProp.getPrimaryColor())
                .setSecondaryColor(dsShapeProp.getSecondaryColor());


        this.paintCanvas = paintCanvas;
        this.graphics2d = paintCanvas.getGraphics2D();
        designShape();
    }


    public DrawShape(PaintCanvasBase paintCanvas, ShapeProperty shapeProperty) {
        this.paintCanvas = paintCanvas;
        this.graphics2d = paintCanvas.getGraphics2D();
        this.shapeProperty = shapeProperty;

        designShape();
    }


    public void designShape() {

        shape = ShapeTypeFactory.build(shapeProperty);
    }


    public void paintShapeOnCanvas() {

        ShapeShadingType shapeShadingType = shapeProperty.getShadingType();
        Color primaryColor = shapeProperty.getPrimaryColor();
        Color secondaryColor = shapeProperty.getSecondaryColor();

        IDrawable drawableShape = null;

        //Draw the shapes on the paintCanvas
        switch (shapeShadingType) {
            case OUTLINE_AND_FILLED_IN:
                drawableShape = DrawableShapeFactory.outLineAndFilledShape(primaryColor, secondaryColor, shape, graphics2d);
                break;
            case FILLED_IN:
                drawableShape = DrawableShapeFactory.filledShape(primaryColor, shape, graphics2d);
                break;
            case OUTLINE:
                drawableShape = DrawableShapeFactory.outlineShape(primaryColor, shape, graphics2d);
                break;
        }

        drawableShape.paintShape();
    }


    public void draw() {
        create();
        paintCanvas.repaint();
    }


    @Override
    public void create() {
        ShapeRepository.selectedCollection.clear();

        paintShapeOnCanvas();
        ShapeRepository.shapeCollection.add(this);
    }


    @Override
    public void moveShape(int transformOffsetX, int transformOffsetY) {

        IShape moveShape = new DrawShape(this, paintCanvas);

        AffineTransform transform = new AffineTransform();

        //** move the copied IShaped a few points on the x and y axis
        transform.translate(transformOffsetX, transformOffsetY);

        Shape offsetShape = transform.createTransformedShape(this.getShape());

        this.setShape(offsetShape);

    }

    public boolean detectCollision(Shape otherShape) {
        return ShapeCollision.detect(shape, otherShape);
    }

    public void highlightShape() {
        float[] dash = {7.0f, 7.0f};
        BasicStroke stroke = new BasicStroke(4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10f, dash, 0.0f);


        DrawOutline drawableShape = new DrawOutline(Color.black, shape, graphics2d);
        drawableShape.setStroke(stroke);
        drawableShape.paintShape();
    }

    public List<IShape> getNodeList() {
        List<IShape> newList = new ArrayList<>();
        newList.add(this);
        return newList;
    }

    public void setGraphics2d(Graphics2D graphics2d) {
        this.graphics2d = graphics2d;
    }

    public ShapeProperty getShapeProperty() {
        return shapeProperty;
    }

    public Shape getBoundingBox() {
        return shape.getBounds();
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;

         //** Use the new shape to update the shape properties
        updateShapeProperty(shape);
    }


    public void updateShapeProperty(Shape shp) {
        Rectangle rect = shp.getBounds();
        Point startPoint1 = new Point(rect.x, rect.y);
        Point endPoint1 = new Point((rect.width + rect.x), (rect.height + rect.y));

        this.shapeProperty.setStartPoint(startPoint1);
        this.shapeProperty.setEndPoint(endPoint1);
    }

    @Override
    public void deleteShape() {
        ShapeRepository.shapeCollection.remove(this);
    }

    @Override
    public void undo() {
        deleteShape();
    }

    @Override
    public void redo() {
        create();
    }


    @Override
    public String toString() {
        return "DrawShape{" +
                "\n\t, paintCanvas=" + paintCanvas +
                "\n\t, shapeProperty=" + shapeProperty.toString() +
                "\n\t, shape=" + shape +
                "\n\t, shape bounds =" + shape.getBounds() +
                "\n}\n";
    }
}
