package com.gabriel.draw.model;

import com.gabriel.draw.service.RectangleRendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setColor(Color.RED);
        this.setRendererService(new RectangleRendererService());
    }

    @Override
    public boolean contains(Point p, int tolerance) {
        Point a = this.getLocation();
        Point b = this.getEnd();
        //add tolerance to the left side
        int minX = Math.min(a.x, b.x) - tolerance;
        //add tolerance to the right side
        int maxX = Math.max(a.x, b.x) + tolerance;
        //add tolerance to the bottom side
        int minY = Math.min(a.y, b.y) - tolerance;
        //add tolerance to the top side of the rectangle
        int maxY = Math.max(a.y, b.y) + tolerance;

        //checks if point is inside the bigger rectangle
        //formed by the tolerance
        return (p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY);
    }
}
