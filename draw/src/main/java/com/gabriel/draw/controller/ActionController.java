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
        if(e.getSource() == ActionCommand.UNDO) {
            appService.undo();
        }
        else if(e.getSource() == ActionCommand.REDO) {
            appService.redo();
        }
        else if(e.getSource() == ActionCommand.LINE) {
            appService.setShapeMode(ShapeMode.Line);
        }
        else if(e.getSource() ==  ActionCommand.RECT) {
            appService.setShapeMode(ShapeMode.Rectangle);
        }
        else if(e.getSource() == ActionCommand.ELLIPSE) {
            appService.setShapeMode(ShapeMode.Ellipse);
        }
        else if(e.getSource() == ActionCommand.COLOR) {
            Color selectedColor = JColorChooser.showDialog(null, "Choose a fill color", Color.WHITE);
            appService.setColor( selectedColor);
        }
    }
}
