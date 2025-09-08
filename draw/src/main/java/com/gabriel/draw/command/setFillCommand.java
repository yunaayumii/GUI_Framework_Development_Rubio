package com.gabriel.draw.command;

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
        prevColor = appService.getColor();
        appService.setColor(color);
    }

    @Override
    public void undo() {
        appService.setColor(prevColor);
    }

    @Override
    public void redo() {
        appService.setColor(color);
    }
}
