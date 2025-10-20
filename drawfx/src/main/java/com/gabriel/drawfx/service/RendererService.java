package com.gabriel.drawfx.service;

import com.gabriel.drawfx.model.Shape;
import java.awt.*;

public interface RendererService {
    void render(Graphics g, Shape shape, boolean xor);

    //helper
    default void drawHandle(Graphics g, Point p, int size) {
        g.fillRect(p.x - size / 2, p.y - size / 2, size, size);
    }
}
