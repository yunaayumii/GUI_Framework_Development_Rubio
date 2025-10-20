package com.gabriel.draw.view;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.command.CommandService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class DrawingMenuBar extends JMenuBar {
    private AppService appService;

    private final JMenuItem lineMenuItem = new JMenuItem("Line");
    private final JMenuItem rectangleMenuItem = new JMenuItem("Rectangle");
    private final JMenuItem ellipseMenuItem = new JMenuItem("Ellipse");
    private final JMenuItem selectMenuItem = new JMenuItem("Select");  // new Select menu item
    private final JMenuItem chooseColorItem = new JMenuItem("Choose Color");
    private final JMenuItem fillColorItem = new JMenuItem("Fill Color");
    private final JMenuItem undoMenuItem = new JMenuItem("Undo");
    private final JMenuItem redoMenuItem = new JMenuItem("Redo");


    public DrawingMenuBar(ActionListener actionListener) {
        super();
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        add(editMenu);
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        undoMenuItem.addActionListener(actionListener);
        undoMenuItem.setActionCommand(ActionCommand.UNDO);
        editMenu.add(undoMenuItem); // undo
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));;
        redoMenuItem.addActionListener(actionListener);
        redoMenuItem.setActionCommand(ActionCommand.REDO);
        editMenu.add(redoMenuItem); // redo

        JMenu drawMenu = new JMenu("Draw");
        drawMenu.setMnemonic(KeyEvent.VK_D);
        add(drawMenu);
        drawMenu.add(lineMenuItem); // line
        lineMenuItem.addActionListener(actionListener);
        lineMenuItem.setActionCommand(ActionCommand.LINE);
        drawMenu.add(rectangleMenuItem); // rectangle
        rectangleMenuItem.addActionListener(actionListener);
        rectangleMenuItem.setActionCommand(ActionCommand.RECT);
        drawMenu.add(ellipseMenuItem); // ellipse
        ellipseMenuItem.addActionListener(actionListener);
        ellipseMenuItem.setActionCommand(ActionCommand.ELLIPSE);

        drawMenu.addSeparator(); // Add separator before Select
        drawMenu.add(selectMenuItem); // select
        selectMenuItem.addActionListener(actionListener);
        selectMenuItem.setActionCommand(ActionCommand.SELECT);

        JMenu colorMenu = new JMenu("Color");
        colorMenu.setMnemonic(KeyEvent.VK_C);
        add(colorMenu);
        colorMenu.add(chooseColorItem); // choose color
        chooseColorItem.addActionListener(actionListener);
        chooseColorItem.setActionCommand(ActionCommand.COLOR);
        colorMenu.add(fillColorItem); // fill color
        fillColorItem.addActionListener(actionListener);
        fillColorItem.setActionCommand(ActionCommand.FILL);

        // Initialize menu item states
        updateMenuStates();
    }

    public void updateMenuStates() {
        undoMenuItem.setEnabled(CommandService.canUndo());
        redoMenuItem.setEnabled(CommandService.canRedo());
    }
}
