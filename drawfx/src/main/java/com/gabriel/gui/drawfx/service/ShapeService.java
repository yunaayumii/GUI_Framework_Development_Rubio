package com.gabriel.gui.drawfx.service;

import com.gabriel.gui.drawfx.model.Shape;

import java.util.List;


public interface ShapeService {
    Shape create(Shape shape);
    Shape update(Shape shape);
    void delete(Shape shape);
    List<Shape> getAll();
    Shape get(Shape shape);
}

