package com.rubio.draw.model;


import com.rubio.draw.service.LineRendererService;
import com.gabriel.drawfx.model.Shape;
import java.awt.*;

public class Line extends Shape {

    public Line(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setColor(Color.RED);
        this.setRendererService(new LineRendererService());
    }
}
