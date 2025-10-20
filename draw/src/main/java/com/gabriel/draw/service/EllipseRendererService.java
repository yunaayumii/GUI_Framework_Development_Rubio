package com.gabriel.draw.service;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.RendererService;

import java.awt.*;


public class EllipseRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Ellipse ellipse = (Ellipse) shape;

        int x = Math.min(ellipse.getLocation().x, ellipse.getEnd().x);
        int y = Math.min(ellipse.getLocation().y, ellipse.getEnd().y);
        int width = Math.abs(ellipse.getLocation().x - ellipse.getEnd().x);
        int height = Math.abs(ellipse.getLocation().y - ellipse.getEnd().y);

        if (xor) {
            g.setXORMode(Color.WHITE);
        } else {
            g.setPaintMode();

            // Draw fill if present
            if (shape.getFill() != null) {
                g.setColor(shape.getFill());
                g.fillOval(x, y, width, height);
            }

            // Draw outline
            Color drawColor;
            if (shape.getColor() != null) {
                drawColor = shape.getColor();
            } else {
                drawColor = Color.BLACK;
            }
            g.setColor(drawColor);
        }

        // Draw the ellipse
        g.drawOval(x, y, width, height);

        // draw selection handles if shape is selected
        if (!xor && shape.isSelected()) {
            g.setColor(Color.BLUE);
            int handleSize = 6;

            // draw dotted border around bounding box
            drawSelectionBorder(g, x, y, width, height);

            // draw handles at all 4 corners of bounding box
            drawHandle(g, new Point(x, y), handleSize);                    // Top-left
            drawHandle(g, new Point(x + width, y), handleSize);            // Top-right
            drawHandle(g, new Point(x, y + height), handleSize);           // Bottom-left
            drawHandle(g, new Point(x + width, y + height), handleSize);   // Bottom-right
        }
    }
}
