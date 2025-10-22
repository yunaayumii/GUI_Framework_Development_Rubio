package com.gabriel.draw.controller;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.ShapeMode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController  implements MouseListener, MouseMotionListener {
    private Point end;
    private Point previousEnd; // track previous end point for xor erasing
    final private DrawingView drawingView;

    Shape currentShape;
    AppService appService;

    // for move operation tracking
    private Point anchorOffset; // offset from shape location to where user clicked
    private Point previousMovePreview; // track previous preview location for xor erase

    // for scale operation tracking
    private SelectionMode scaleMode; // which handle is being dragged
    private Point previousScalePreview; // track previous preview point for xor erase

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

            // when move is selected
            if (appService.getShapeMode() == ShapeMode.Move) {
                Shape selectedShape = appService.getSelectedShape();
                if (selectedShape != null) {
                    // calculate offset so shape doesn't jump to cursor position
                    // anchorOffset = where we clicked - shape's current location
                    Point loc = selectedShape.getLocation();
                    anchorOffset = new Point(start.x - loc.x, start.y - loc.y);
                    previousMovePreview = null; // no preview yet
                    appService.setDrawMode(DrawMode.MousePressed);
                }
                return;
            }

            // when scale is selected
            if (appService.getShapeMode() == ShapeMode.Scale) {
                Shape selectedShape = appService.getSelectedShape();
                if (selectedShape != null) {
                    // determine which scale handle (if any) was clicked
                    scaleMode = appService.getScaleHandleAt(selectedShape, start);
                    if (scaleMode != SelectionMode.None) {
                        previousScalePreview = null; // no preview yet
                        appService.setDrawMode(DrawMode.MousePressed);
                    }
                }
                return;
            }

            previousEnd = start; // initialize previous end to start point
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
            // set shape color to currently selected color
            currentShape.setColor(appService.getColor());
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape,false );
            appService.setDrawMode(DrawMode.MousePressed);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(appService.getDrawMode() == DrawMode.MousePressed){
            end = e.getPoint();

            // if in move mode, commit the move
            if (appService.getShapeMode() == ShapeMode.Move) {
                Shape selectedShape = appService.getSelectedShape();
                if (selectedShape != null) {
                    // calculate final location using the anchor offset
                    Point finalLoc = new Point(end.x - anchorOffset.x, end.y - anchorOffset.y);
                    appService.move(selectedShape, finalLoc);

                    // cleanup
                    anchorOffset = null;
                    previousMovePreview = null;
                }
            } else if (appService.getShapeMode() == ShapeMode.Scale) {
                // if in scale mode, commit the scale
                Shape selectedShape = appService.getSelectedShape();
                if (selectedShape != null && scaleMode != SelectionMode.None) {
                    appService.scaleShape(selectedShape, end, scaleMode);

                    // cleanup
                    scaleMode = SelectionMode.None;
                    previousScalePreview = null;
                }
            } else {
                appService.create(currentShape); // only push to stack here
            }

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
            // handle move mode with xor preview
            if (appService.getShapeMode() == ShapeMode.Move) {
                Shape selectedShape = appService.getSelectedShape();
                if (selectedShape != null && anchorOffset != null) {
                    // erase previous xor preview if exists
                    if (previousMovePreview != null) {
                        appService.renderMovePreview(drawingView.getGraphics(), selectedShape, previousMovePreview);
                    }

                    // calculate new preview location maintaining the anchor offset
                    Point newPreviewLoc = new Point(e.getPoint().x - anchorOffset.x,
                                                     e.getPoint().y - anchorOffset.y);

                    // draw new xor preview
                    appService.renderMovePreview(drawingView.getGraphics(), selectedShape, newPreviewLoc);

                    // remember this preview location for next erase
                    previousMovePreview = newPreviewLoc;
                }
                return;
            }

            // handle scale mode with xor preview
            if (appService.getShapeMode() == ShapeMode.Scale) {
                Shape selectedShape = appService.getSelectedShape();
                if (selectedShape != null && scaleMode != SelectionMode.None) {
                    // erase previous xor preview if exists
                    if (previousScalePreview != null) {
                        appService.renderScalePreview(drawingView.getGraphics(), selectedShape,
                                                      previousScalePreview, scaleMode);
                    }

                    // get new preview point
                    Point newPreviewPoint = e.getPoint();

                    // draw new xor preview
                    appService.renderScalePreview(drawingView.getGraphics(), selectedShape,
                                                  newPreviewPoint, scaleMode);

                    // remember this preview point for next erase
                    previousScalePreview = newPreviewPoint;
                }
                return;
            }

            // first, erase the previous preview by drawing it again in xor mode
            if (previousEnd != null) {
                currentShape.setEnd(previousEnd);
                currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
            }

            // then draw the new preview
            end = e.getPoint();
            currentShape.setEnd(end);
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);

            // store current end as previous for next iteration
            previousEnd = end;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
