package com.rubio.draw.command;

import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;

public class moveCommand implements Command {
    AppService appService;
    Shape shape;
    Point newLoc;
    Point prevLoc;

    public moveCommand(AppService appService, Shape shape, Point newLoc) {
        this.appService = appService;
        this.shape = shape;
        this.newLoc = newLoc;
        this.prevLoc = shape.getLocation();
    }

    @Override
    public void execute() {
        prevLoc = shape.getLocation();
        shape.setLocation(newLoc);
    }

    @Override
    public void undo() {
        shape.setLocation(prevLoc);
    }

    @Override
    public void redo() {
        shape.setLocation(newLoc);
    }
}
