package com.gabriel.draw.command;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.service.AppService;

public class setDrawModeCommand implements Command {
    AppService appService;
    DrawMode drawMode;
    DrawMode prevDrawMode;

    public setDrawModeCommand(AppService appService, DrawMode drawMode) {
        this.appService = appService;
        this.drawMode = drawMode;
    }
    @Override
    public void execute() {
        prevDrawMode = appService.getDrawMode();
        appService.setDrawMode(drawMode);
    }

    @Override
    public void undo() {
        appService.setDrawMode(prevDrawMode);
    }

    @Override
    public void redo() {
        appService.setDrawMode(drawMode);
    }
}
