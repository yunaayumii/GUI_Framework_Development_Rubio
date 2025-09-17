package com.gabriel.draw.view;

import com.gabriel.drawfx.ActionCommand;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DrawingToolBar extends JToolBar {

    private final JButton lineButton = new JButton("Line");
    private final JButton rectangleButton = new JButton("Rectangle");
    private final JButton ellipseButton = new JButton("Ellipse");
    private final JButton colorButton = new JButton("Color");
    private final JButton fillColorButton = new JButton("Fill");
    private final JButton undoButton = new JButton("Undo");
    private final JButton redoButton = new JButton("Redo");

    public DrawingToolBar(ActionListener actionListener){
        super();

        // Add drawing shape buttons
        lineButton.addActionListener(actionListener);
        lineButton.setActionCommand(ActionCommand.LINE);
        lineButton.setToolTipText("Draw Line");
        add(lineButton);

        rectangleButton.addActionListener(actionListener);
        rectangleButton.setActionCommand(ActionCommand.RECT);
        rectangleButton.setToolTipText("Draw Rectangle");
        add(rectangleButton);

        ellipseButton.addActionListener(actionListener);
        ellipseButton.setActionCommand(ActionCommand.ELLIPSE);
        ellipseButton.setToolTipText("Draw Ellipse");
        add(ellipseButton);

        addSeparator(); // Visual separator

        // Add color buttons
        colorButton.addActionListener(actionListener);
        colorButton.setActionCommand(ActionCommand.COLOR);
        colorButton.setToolTipText("Choose Line Color");
        add(colorButton);

        fillColorButton.addActionListener(actionListener);
        fillColorButton.setActionCommand(ActionCommand.FILL);
        fillColorButton.setToolTipText("Choose Fill Color");
        add(fillColorButton);

        addSeparator(); // Visual separator

        // Add undo/redo buttons
        undoButton.addActionListener(actionListener);
        undoButton.setActionCommand(ActionCommand.UNDO);
        undoButton.setToolTipText("Undo");
        add(undoButton);

        redoButton.addActionListener(actionListener);
        redoButton.setActionCommand(ActionCommand.REDO);
        redoButton.setToolTipText("Redo");
        add(redoButton);
    }
}
