package com.gabriel.drawfx.service;

import com.gabriel.drawfx.model.Shape;
import java.awt.*;

public interface RendererService {
    void render(Graphics g, Shape shape, boolean xor);

    //helper handle
    default void drawHandle(Graphics g, Point p, int size) {
        g.fillRect(p.x - size / 2, p.y - size / 2, size, size);
    }

    //helper rectangle selection border
    default void drawSelectionBorder(Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();

        // Create a dotted line pattern: 5 pixels on, 3 pixels off
        float[] dashPattern = {5.0f, 3.0f};
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0.0f));

        // Draw the dotted rectangle
        g2d.drawRect(x, y, width, height);

        // Restore the original stroke
        g2d.setStroke(oldStroke);
    }
}
