package com.gabriel.draw.service;

import com.gabriel.draw.model.Line;
import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;


public class LineRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Line line = (Line) shape;
        if(xor) {
            g.setXORMode(Color.WHITE);
        }
        else {
            g.setPaintMode();

            Color drawColor;
            if (shape.getColor() != null) {
                drawColor = shape.getColor();
            } else {
                drawColor = Color.BLACK;
            }
            g.setColor(drawColor);
        }
        g.drawLine(line.getLocation().x, line.getLocation().y, line.getEnd().x, line.getEnd().y);

        // draw selection handles if shape is selected
        if (!xor && shape.isSelected()) {
            g.setColor(Color.BLUE);
            int handleSize = 6;
            drawHandle(g, line.getLocation(), handleSize);
            drawHandle(g, line.getEnd(), handleSize);
        }
    }
}