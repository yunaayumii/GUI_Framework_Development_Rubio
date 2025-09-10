package com.rubio.draw.service;

import com.rubio.draw.model.Ellipse;
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
            g.setXORMode(shape.getColor());
        } else {
            if (shape.getFill() != null) {
                g.setColor(shape.getFill());
                g.fillOval(x, y, width, height);
            }
        }
        g.drawOval(x,y, width, height);
    }
}