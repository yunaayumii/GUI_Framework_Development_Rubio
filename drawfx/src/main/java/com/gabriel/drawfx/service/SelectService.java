package com.gabriel.drawfx.service;

import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public final class SelectService {

    public void selectShape(Drawing drawing, Point p) {
        // deselect any currently selected shape
        if (drawing.getSelectedShape() != null) {
            drawing.getSelectedShape().setSelected(false);
        }

        // search through all the shapes by layer, top to bottom
        for (int i = drawing.getShapes().size() - 1; i >= 0; i--) {
            Shape shape = drawing.getShapes().get(i);
            // Can adjust tolerance here
            if (shape.contains(p, 5)) {  // 5 pixel tolerance
                shape.setSelected(true);
                drawing.setSelectedShape(shape);
                return;
            }
        }

        // no shape found, clear selection
        drawing.setSelectedShape(null);
    }
}

