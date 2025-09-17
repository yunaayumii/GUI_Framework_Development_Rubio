package com.gabriel.draw.service;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.RendererService;

import java.awt.*;

public class EllipseRenderer implements RendererService {
    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Ellipse ellipse = (Ellipse) shape;

        int x = shape.getLocation().x;
        int y = shape.getLocation().y;
        int width = shape.getEnd().x-shape.getLocation().x;
        int height = shape.getEnd().y-shape.getLocation().y;

        if(width < 0) {
            x = shape.getEnd().x;
            width = -width;
        }
        if(height < 0) {
            y = shape.getEnd().y ;
            height = -height;
        }

        if (xor) {
            g.setXORMode(Color.WHITE); // Use WHITE for XOR mode instead of potentially null color
        } else {
            g.setPaintMode();

            if (shape.getFill() != null) {
                g.setColor(shape.getFill());
                g.fillOval(x, y, width, height);
            }

            Color drawColor;

            if (shape.getColor() != null) {
                drawColor = shape.getColor();
            } else {
                drawColor = Color.BLACK;
            }

            g.setColor(drawColor);
        }
        g.drawOval(x,y, width, height);
    }
}