package com.gabriel.draw.service;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.service.MoverService;
import com.gabriel.drawfx.service.ScalerService;
import com.gabriel.drawfx.service.SelectService;

import javax.swing.*;
import java.awt.*;


public class DrawingAppService implements AppService {

    final private Drawing drawing;
    MoverService moverService;
    ScalerService scalerService;
    SelectService selectService;
    JPanel drawingView;
    public DrawingAppService(){
        drawing = new Drawing();
        moverService = new MoverService();
        scalerService = new ScalerService();
        selectService = new SelectService();
        drawing.setDrawMode(DrawMode.Idle);
        drawing.setShapeMode(ShapeMode.Ellipse);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public ShapeMode getShapeMode() {
        return drawing.getShapeMode();
    }

    @Override
    public void setShapeMode(ShapeMode shapeMode) {
        // Keep selection when switching to Select or Move modes, clear it when switching to a drawing mode
        if (shapeMode == ShapeMode.Line || shapeMode == ShapeMode.Rectangle || shapeMode == ShapeMode.Ellipse) {
            if (drawing.getSelectedShape() != null) {
                drawing.getSelectedShape().setSelected(false);
                drawing.setSelectedShape(null);
                repaint();
            }
        }
        drawing.setShapeMode(shapeMode);
    }

    @Override
    public DrawMode getDrawMode() {
        return drawing.getDrawMode();
    }

    @Override
    public void setDrawMode(DrawMode drawMode) {
        this.drawing.setDrawMode(drawMode);
    }

    @Override
    public Color getColor() {
        return drawing.getColor();
    }

    @Override
    public void setColor(Color color) {
        drawing.setColor(color);
    }

    @Override
    public Color getFill(){
        return drawing.getFill();
    }

    @Override
    public void setFill(Color color) {
        drawing.setFill(color);
    }

    @Override
    public void move(Shape shape, Point newLoc) {
        moverService.move(shape, newLoc);
        // repaint so the move is visible immediately
        repaint();
    }

    @Override
    public void scale(Shape shape, Point newEnd) {
        shape.setEnd(newEnd);
    }

    @Override
    public void create(Shape shape) {
        shape.setId(this.drawing.getShapes().size());

        if(drawing.getColor() != null) {
            shape.setColor(drawing.getColor());
        } else {
            shape.setColor(Color.RED);
        }

        if(drawing.getFill() != null) {
            shape.setFill(drawing.getFill());
        }

        this.drawing.getShapes().add(shape);
        // make newly created shape selected (small convenience)
        drawing.setSelectedShape(shape);
        repaint();
    }

    @Override
    public void delete(Shape shape) {
        drawing.getShapes().remove(shape);
        // if deleted shape was selected, clear selection
        if (drawing.getSelectedShape() == shape) {
            drawing.setSelectedShape(null);
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }

    @Override
    public Object getModel() {
        return drawing;
    }

    @Override
    public JPanel getView() {
        return drawingView;
    }

    @Override
    public void setView(JPanel panel) {
        this.drawingView = panel;
    }

    @Override
    public void repaint() {
        drawingView.repaint();
    }

    @Override
    public int getSearchRadius() {
        return 0;
    }

    @Override
    public void setSearchRadius(int radius) {

    }
    @Override
    public void search(Point p) {
    }

    @Override
    public void selectShape(Point p) {
        selectService.selectShape(drawing, p);
        repaint();
    }

    @Override
    public Shape getSelectedShape() {
        return drawing.getSelectedShape();
    }

    @Override
    public void renderMovePreview(Graphics g, Shape shape, Point previewLoc) {
        moverService.renderMovePreview(g, shape, previewLoc);
    }
}
