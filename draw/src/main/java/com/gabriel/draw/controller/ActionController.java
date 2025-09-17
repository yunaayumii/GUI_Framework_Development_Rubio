package com.gabriel.draw.controller;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener {
    AppService appService;
    public ActionController(AppService appService) {
        this.appService = appService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == ActionCommand.UNDO) {
            appService.undo();
        }
        else if(e.getActionCommand() == ActionCommand.REDO) {
            appService.redo();
        }
        else if(e.getActionCommand() == ActionCommand.LINE) {
            appService.setShapeMode(ShapeMode.Line);
        }
        else if(e.getActionCommand()  ==  ActionCommand.RECT) {
            appService.setShapeMode(ShapeMode.Rectangle);
        }
        else if(e.getActionCommand() == ActionCommand.ELLIPSE) {
            appService.setShapeMode(ShapeMode.Ellipse);
        }
        else if(e.getActionCommand() == ActionCommand.COLOR) {
            Color selectedColor = JColorChooser.showDialog(null, "Choose a fill color", Color.WHITE);
            appService.setColor( selectedColor);
        }
    }
}
