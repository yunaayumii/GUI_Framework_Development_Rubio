package com.rubio.draw.command;

import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.service.AppService;


public class setShapeModeCommand implements Command {
    AppService appService;
    ShapeMode shapeMode;
    ShapeMode prevShapeMode;

    public setShapeModeCommand(AppService appService, ShapeMode shapeMode) {
        this.appService = appService;
        this.shapeMode = shapeMode;
    }
    @Override
    public void execute() {
        prevShapeMode = appService.getShapeMode();
        appService.setShapeMode(shapeMode);
    }

    @Override
    public void undo() {
        appService.setShapeMode(prevShapeMode);
    }

    @Override
    public void redo() {
        appService.setShapeMode(shapeMode);
    }
}
