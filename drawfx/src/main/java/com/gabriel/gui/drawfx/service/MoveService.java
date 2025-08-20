package com.gabriel.gui.drawfx.service;

import com.gabriel.gui.drawfx.model.Shape;

public class MoveService {
    void  move(Shape shape, int dx, int dy){
        shape.getLocation().setX(shape.getLocation().getX() +dx);
        shape.getLocation().setY(shape.getLocation().getY() +dy);
    }
}
