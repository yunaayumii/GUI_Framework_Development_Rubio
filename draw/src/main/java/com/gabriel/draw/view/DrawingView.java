package com.gabriel.draw.view;

import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.ShapeMode;

import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    AppService appService;

    public DrawingView(AppService appService){
        this.appService = appService;
        appService.setView(this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Drawing drawing = (Drawing) appService.getModel();
        for(Shape shape : drawing.getShapes()){
            shape.getRendererService().render(g, shape, false);

            // Draw selection box if shape is selected
            if (shape.isSelected()) {
                drawSelectionBox(g, shape);

                // Draw scale handles if in Scale mode
                if (appService.getShapeMode() == ShapeMode.Scale) {
                    drawScaleHandles(g, shape);
                }
            }

            appService.setView(this);
        }
    }

    // Draw a selection box around the selected shape
    private void drawSelectionBox(Graphics g, Shape shape) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();

        int minX = Math.min(start.x, end.x);
        int maxX = Math.max(start.x, end.x);
        int minY = Math.min(start.y, end.y);
        int maxY = Math.max(start.y, end.y);

        g.setColor(Color.BLUE);
        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
        g.drawRect(minX, minY, maxX - minX, maxY - minY);
        g2d.setStroke(oldStroke);
    }

    // Draw scale handles on the selected shape
    private void drawScaleHandles(Graphics g, Shape shape) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();

        int minX = Math.min(start.x, end.x);
        int maxX = Math.max(start.x, end.x);
        int minY = Math.min(start.y, end.y);
        int maxY = Math.max(start.y, end.y);

        int centerX = (minX + maxX) / 2;
        int centerY = (minY + maxY) / 2;

        int handleSize = 8;
        int halfSize = handleSize / 2;

        g.setColor(Color.BLUE);

        // Draw corner handles
        g.fillRect(minX - halfSize, minY - halfSize, handleSize, handleSize);
        g.fillRect(maxX - halfSize, minY - halfSize, handleSize, handleSize);
        g.fillRect(minX - halfSize, maxY - halfSize, handleSize, handleSize);
        g.fillRect(maxX - halfSize, maxY - halfSize, handleSize, handleSize);

        // Draw edge handles
        g.fillRect(centerX - halfSize, minY - halfSize, handleSize, handleSize);
        g.fillRect(centerX - halfSize, maxY - halfSize, handleSize, handleSize);
        g.fillRect(minX - halfSize, centerY - halfSize, handleSize, handleSize);
        g.fillRect(maxX - halfSize, centerY - halfSize, handleSize, handleSize);
    }
}
