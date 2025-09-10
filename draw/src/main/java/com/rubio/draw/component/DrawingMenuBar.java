package com.rubio.draw.component;

import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.sql.SQLOutput;

public class DrawingMenuBar extends JMenuBar implements ActionListener {
    private AppService appService;

    private final JMenuItem lineMenuItem = new JMenuItem("Line");
    private final JMenuItem rectangleMenuItem = new JMenuItem("Rectangle");
    private final JMenuItem ellipseMenuItem = new JMenuItem("Ellipse");
    private final JMenu colorMenu = new JMenu("Color");
    private final JMenuItem chooseColorItem = new JMenuItem("Choose Color");
    private final JMenuItem fillColorItem = new JMenuItem("Fill Color");
    private final JMenuItem undoMenuItem = new JMenuItem("Undo");
    private final JMenuItem redoMenuItem = new JMenuItem("Redo");


    public DrawingMenuBar(AppService appService ){
        super();
        this.appService = appService;
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        add(editMenu);
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        undoMenuItem.addActionListener(this);
        editMenu.add(undoMenuItem);
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));;
        redoMenuItem.addActionListener(this);
        editMenu.add(redoMenuItem);

        JMenu drawMenu = new JMenu("Draw");
        drawMenu.setMnemonic(KeyEvent.VK_D);
        add(drawMenu); //make it top level
        drawMenu.add(lineMenuItem);
        lineMenuItem.addActionListener(this);
        drawMenu.add(rectangleMenuItem);
        rectangleMenuItem.addActionListener(this);
        drawMenu.add(ellipseMenuItem);
        ellipseMenuItem.addActionListener(this);

        JMenu colorMenu = new JMenu("Color");
        colorMenu.setMnemonic(KeyEvent.VK_C);
        add(colorMenu);
        colorMenu.add(chooseColorItem);
        chooseColorItem.addActionListener(this);
        colorMenu.add(fillColorItem);
        fillColorItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == undoMenuItem) {
            appService.undo();
        }
        else if(e.getSource() == redoMenuItem) {
            appService.redo();
        }
        else if(e.getSource() == lineMenuItem){
            appService.setShapeMode( ShapeMode.Line);
        }
        else if(e.getSource() == rectangleMenuItem){
            appService.setShapeMode( ShapeMode.Rectangle);
        }
        else if(e.getSource() == ellipseMenuItem){
            appService.setShapeMode( ShapeMode.Ellipse);
        }
        else if(e.getSource() == chooseColorItem){
            Color outlineColor = JColorChooser.showDialog(this, "Choose a color", appService.getColor());
            if (outlineColor != null) {
                appService.setColor(outlineColor);
            }
        }
        else if(e.getSource() == fillColorItem){
            Color selectedColor = JColorChooser.showDialog(this, "Choose a fill color", appService.getFill());
            if (selectedColor != null) {
                appService.setFill(selectedColor);
            }
        }
    }
}
