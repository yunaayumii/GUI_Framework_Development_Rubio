package com.gabriel.draw.service;

import com.gabriel.drawfx.model.Shape;
import com.gabriel.service.AppService;

import java.util.ArrayList;
import java.util.List;

public class DrawingShapeService implements AppService {
    List<Shape> shapes = new ArrayList<>();

    public Shape create(Shape shape) {
        shapes.add(shape);
        return shape;
    }


    @Override
    public void addShape(com.gabriel.model.Shape shape) {

    }

    @Override
    public void updateShape(com.gabriel.model.Shape shape) {

    }

    @Override
    public void deleteShape(com.gabriel.model.Shape shape) {

    }

    @Override
    public void selectShape(com.gabriel.model.Shape shape) {

    }

    @Override
    public com.gabriel.model.Shape getSelectedShape() {
        return null;
    }

    @Override
    public void open() {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveAs() {

    }

    @Override
    public void close() {

    }

    @Override
    public void exit() {

    }
}
