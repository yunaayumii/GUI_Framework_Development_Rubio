package com.gabriel.drawfx.model;

import com.gabriel.drawfx.service.RendererService;
import lombok.Data;

import java.awt.*;
@Data
public abstract class Shape {
    int id;
    private Point location;
    private Point end;
    private Color color;
    private Color fill;
    private RendererService rendererService;
    private boolean selected = false;  // track selection state of the shape

    public Shape(Point location){
        this.setLocation(location);
        this.setEnd(location);
  }

    // add a "contains" function for Shapes, and will override it later on for all the shapes.
    public abstract boolean contains(Point p, int tolerance);

}
