package com.rubio.draw.model;

import com.rubio.draw.service.RectangleRendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setColor(Color.RED);
        this.setRendererService(new RectangleRendererService());
    }
}
