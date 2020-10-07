package controller.events;

import model.interfaces.IApplicationState;
import model.interfaces.IRun;
import model.modes.DrawMode;
import model.modes.MoveMode;
import model.modes.SelectMode;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintCanvasMouseAdapter extends MouseAdapter {

    Shape drawDemoShape;
    private PaintCanvasBase paintCanvas;
    private Point startPoint, endPoint;
    private IApplicationState appState;
    private int xAxis = 0;
    private int yAxis = 0;
    private MoveMode moveMode = null;
    private boolean mouseDragged = false;

    public PaintCanvasMouseAdapter(PaintCanvasBase paintCanvas, IApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        endPoint = e.getPoint();

        xAxis = e.getX();
        yAxis = e.getY();

        mouseDragged = false;

    }

    public void mouseDragged(MouseEvent e) {

        mouseDragged = true;

        int currentX = e.getX();
        int currentY = e.getY();

        switch (appState.getActiveStartAndEndPointMode()) {
            case MOVE:

                int translateXX = currentX - xAxis;
                int translateYY = currentY - yAxis;
                Point transformPos = new Point(translateXX, translateYY);

                xAxis = currentX;
                yAxis = currentY;

                moveMode = new MoveMode(startPoint, transformPos, paintCanvas, appState);
                moveMode.run();

                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = e.getPoint();

        IRun mode = null;

        switch (appState.getActiveStartAndEndPointMode()) {
            case DRAW:
                mode = new DrawMode(startPoint, endPoint, paintCanvas, appState);
                break;
            case SELECT:
                mode = new SelectMode(startPoint, endPoint, paintCanvas, appState);
                break;
            case MOVE:
                if (mouseDragged && moveMode != null) {
                    moveMode.movement(endPoint);
                }

                break;
        }

        if (mode != null) {
            mode.run();
        }
    }
}
