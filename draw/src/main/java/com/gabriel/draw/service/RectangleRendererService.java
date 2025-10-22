package com.gabriel.draw.service;

import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.RendererService;

import java.awt.*;

public class RectangleRendererService implements RendererService {
    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Rectangle line = (Rectangle) shape;

        int x = shape.getLocation().x;
        int y = shape.getLocation().y;
        int width = shape.getEnd().x - shape.getLocation().x;
        int height = shape.getEnd().y - shape.getLocation().y;
        if (width < 0) {
            x = shape.getEnd().x;
            width = -width;
        }
        if (height < 0) {
            y = shape.getEnd().y;
            height = -height;
        }

        if (xor) {
            g.setXORMode(Color.WHITE);
        } else {
            g.setPaintMode();

            if (shape.getFill() != null) {
                g.setColor(shape.getFill());
                g.fillRect(x, y, width, height);
            }

            Color drawColor;
            if (shape.getColor() != null) {
                drawColor = shape.getColor();
            } else {
                drawColor = Color.BLACK;
            }
            g.setColor(drawColor);
        }
        g.drawRect(x, y, width, height);

        // draw selection handles if shape is selected (also in xor mode during move preview)
        if (shape.isSelected()) {
            g.setColor(Color.BLUE);
            int handleSize = 6;

            // draw dotted border connecting the handles
            drawSelectionBorder(g, x, y, width, height);

            // draw handles at all 4 corners
            drawHandle(g, new Point(x, y), handleSize);                    // Top-left
            drawHandle(g, new Point(x + width, y), handleSize);            // Top-right
            drawHandle(g, new Point(x, y + height), handleSize);           // Bottom-left
            drawHandle(g, new Point(x + width, y + height), handleSize);   // Bottom-right
        }
    }
}
