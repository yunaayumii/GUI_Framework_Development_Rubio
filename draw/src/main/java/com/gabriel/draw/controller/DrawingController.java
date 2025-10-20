package com.gabriel.draw.controller;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.ShapeMode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController  implements MouseListener, MouseMotionListener {
    private Point end;
    private Point previousEnd; // Track previous end point for XOR erasing
    final private DrawingView drawingView;

    Shape currentShape;
    AppService appService;
     public DrawingController(AppService appService, DrawingView drawingView){
       this.appService = appService;
         this.drawingView = drawingView;
         drawingView.addMouseListener(this);
         drawingView.addMouseMotionListener(this);
     }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point start;
        if(appService.getDrawMode() == DrawMode.Idle) {
            start = e.getPoint();

            // controller logic for when select mode is selected
            if (appService.getShapeMode() == ShapeMode.Select) {
                appService.selectShape(start);
                return;
            }

            previousEnd = start; // Initialize previous end to start point
            switch (appService.getShapeMode()){
                case Line:  currentShape = new Line(start, start);
                    break;
                case Rectangle:
                    currentShape = new Rectangle(start, start);
                    break;
                case  Ellipse:
                    currentShape = new Ellipse(start, start);
                    break;
            }
            // Set shape color to currently selected color
            currentShape.setColor(appService.getColor());
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
            appService.setDrawMode(DrawMode.MousePressed);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(appService.getDrawMode() == DrawMode.MousePressed){
            end = e.getPoint();
            appService.create(currentShape); // Only push to stack here
            appService.setDrawMode(DrawMode.Idle);
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
            // First, erase the previous preview by drawing it again in XOR mode
            if (previousEnd != null) {
                currentShape.setEnd(previousEnd);
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
            }

            // Then draw the new preview
            end = e.getPoint();
            currentShape.setEnd(end);
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);

            // Store current end as previous for next iteration
            previousEnd = end;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
