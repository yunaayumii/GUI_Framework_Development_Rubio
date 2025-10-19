package com.gabriel.draw.model;


import com.gabriel.draw.service.LineRendererService;
import com.gabriel.drawfx.model.Shape;
import java.awt.*;

public class Line extends Shape {

    public Line(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setColor(Color.RED);
        this.setRendererService(new LineRendererService());
    }

    @Override
    public boolean contains(Point p, int tolerance) {
        Point a = this.getLocation();
        Point b = this.getEnd();
        double ax = a.x;
        double ay = a.y;
        double bx = b.x;
        double by = b.y;
        double px = p.x;
        double py = p.y;

        //line segment AB
        double dx = bx - ax;
        double dy = by - ay;
        //line segment AB squared
        double len2 = dx*dx + dy*dy;

        // If segment is a point, check distance to that point
        if (len2 == 0) {
            double dist2 = (px - ax)*(px - ax) + (py - ay)*(py - ay);
            return dist2 <= (double)tolerance * tolerance;
        }

        //projection factor calculate
        double t = ((px - ax)*dx + (py - ay)*dy) / len2;

        //make sure point p does not compare its distance to a point not on the line.
        if (t < 0) t = 0;
        if (t > 1) t = 1;

        //calculation of the projection point (the closest point of line to the point p)
        double projx = ax + t*dx;
        double projy = ay + t*dy;

        //calculate if within or out the tolerance level
        double dist2 = (px - projx)*(px - projx) + (py - projy)*(py - projy);
        return dist2 <= (double)tolerance * tolerance;
    }
}
