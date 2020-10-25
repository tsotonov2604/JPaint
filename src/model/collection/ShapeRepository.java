package model.collection;

import model.shapes.ShapeList;

public class ShapeRepository {
    public static final ShapeList selectedCollection = new ShapeList("Selected shapes");
    public static final ShapeList shapeCollection = new ShapeList("Shapes on canvas");
    public static final ShapeList clipboardShapeCollection = new ShapeList("Clipboard shapes");
    public static final ShapeList deleteShapeCollection = new ShapeList("Deleted shapes");
    public static final ShapeList groupShapeCollection = new ShapeList("Grouped shapes");

}
