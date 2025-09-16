package com.gabriel.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;

public class scaleCommand implements Command {
    AppService appService;
    Shape shape;
    Point newEnd;
    Point prevEnd;

    public scaleCommand(AppService appService, Shape shape, Point newEnd) {
        this.appService = appService;
        this.shape = shape;
        this.newEnd = newEnd;
        this.prevEnd = shape.getEnd();
    }

    @Override
    public void execute() {
        prevEnd = shape.getEnd();
        shape.setEnd(newEnd);
    }

    @Override
    public void undo() {
        shape.setEnd(prevEnd);
    }

    @Override
    public void redo() {
        shape.setEnd(newEnd);
    }
}
