package com.gabriel.draw.command;

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
        // save current location for undo
        this.prevLoc = shape.getLocation();
    }

    @Override
    public void execute() {
        // capture current before moving in case shape was changed elsewhere
        prevLoc = shape.getLocation();
        appService.move(shape, newLoc);
    }

    @Override
    public void undo() {
        // move back to previous location via service (keeps size and end consistent)
        appService.move(shape, prevLoc);
    }

    @Override
    public void redo() {
        // move again to target location via service
        appService.move(shape, newLoc);
    }
}
