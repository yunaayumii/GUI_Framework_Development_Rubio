package com.rubio.draw.command;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.service.AppService;

import java.awt.*;

public class setFillCommand implements Command {
    AppService appService;
    Color color;
    Color prevColor;

    public setFillCommand(AppService appService, Color color) {
        this.appService = appService;
        this.color = color;
    }

    @Override
    public void execute() {
        prevColor = appService.getFill();
        appService.setFill(color);
    }

    @Override
    public void undo() {
        appService.setFill(prevColor);
    }

    @Override
    public void redo() {
        appService.setFill(color);
    }
}
