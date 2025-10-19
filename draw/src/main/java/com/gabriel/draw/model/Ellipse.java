package com.gabriel.draw.model;

import com.gabriel.draw.service.EllipseRenderer;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public class Ellipse extends Shape {
       public Ellipse(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setColor(Color.RED);
        this.setRendererService(new EllipseRenderer());
    }

    @Override
    public boolean contains(Point p, int tolerance) {
        Point a = this.getLocation();
        Point b = this.getEnd();

        // find the bounding box, this implementation also makes sure that
        //it does not matter how the ellipse was drawn
        double minX = Math.min(a.x, b.x);
        double maxX = Math.max(a.x, b.x);
        double minY = Math.min(a.y, b.y);
        double maxY = Math.max(a.y, b.y);

        //getting the center
        double cx = (minX + maxX) / 2.0;
        double cy = (minY + maxY) / 2.0;
        double rx = (maxX - minX) / 2.0;
        double ry = (maxY - minY) / 2.0;

        //for special case of just a point
        if (rx == 0 && ry == 0) {
            double dx = p.x - cx;
            double dy = p.y - cy;
            return dx*dx + dy*dy <= (double)tolerance * tolerance;
        }

        //create an imaginary bigger ellipse (with tolerance)
        rx += tolerance;
        ry += tolerance;

        //nx^2 + ny^2 should be less than 1 or equal to 1 if it is
        //in the imaginary ellipse
        double nx = (p.x - cx) / rx;
        double ny = (p.y - cy) / ry;
        return (nx*nx + ny*ny) <= 1.0;
    }
 }
