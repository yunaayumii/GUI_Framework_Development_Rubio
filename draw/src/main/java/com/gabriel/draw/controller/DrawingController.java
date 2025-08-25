package com.gabriel.draw.controller;

import com.gabriel.draw.model.Line;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController  implements MouseListener, MouseMotionListener {
    private Point end;
    final private DrawingView drawingView;

    Shape currentShape;
    AppService appService;
     public DrawingController(AppService appService, DrawingView drawingView){
       this.appService = appService;
         this.drawingView = drawingView;
         drawingView.addMouseListener(this);
         drawingView.addMouseMotionListener(this);
         appService.setDrawMode(DrawMode.Idle);
         appService.setShapeMode(ShapeMode.Line);
     }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point start;
        if(appService.getDrawMode() == DrawMode.Idle) {
            start = e.getPoint();
            if(appService.getShapeMode() == ShapeMode.Line) {
                currentShape = new Line(start, start);
            } else if(appService.getShapeMode() == ShapeMode.Ellipse) {
                currentShape = new com.gabriel.draw.model.Ellipse(start, start);
            } else if(appService.getShapeMode() == ShapeMode.Rectangle) {
                currentShape = new com.gabriel.draw.model.Rectangle(start, start);
            }
            if (currentShape != null) {
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, false);
                appService.setDrawMode(DrawMode.MousePressed);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         if(appService.getDrawMode() == DrawMode.MousePressed){
             end = e.getPoint();
             if (currentShape != null) {
                 appService.scale(currentShape, end);
                 appService.create(currentShape);
                 appService.setDrawMode(DrawMode.Idle);
             }
         }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(appService.getDrawMode() == DrawMode.MousePressed) {
            if (currentShape != null) {
                // First, erase the previous shape by drawing it again with XOR
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);

                // Update shape to new position
                end = e.getPoint();
                appService.scale(currentShape, end);

                // Draw the shape at the new position
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
