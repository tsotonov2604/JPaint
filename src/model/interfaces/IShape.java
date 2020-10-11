package model.interfaces;

import java.awt.*;
import java.util.List;

public interface IShape {
    void paintShapeOnCanvas();

    void setGraphics2d(Graphics2D graphics2d);

    void highlightShape();

    boolean detectCollision(Shape otherShape);


    void create();

    void moveShape(int transformOffsetX, int transformOffsetY);

    IShape copyShape();

    IShape pasteShape();

    void deleteShape();

    List<IShape> getNodeList();


}

