package com.gabriel.draw.view;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.command.CommandService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class DrawingToolBar extends JToolBar {

    private final JButton lineButton = new JButton();
    private final JButton rectangleButton = new JButton();
    private final JButton ellipseButton = new JButton();
    private final JButton selectButton = new JButton();  // New Select button
    private final JButton moveButton = new JButton();    // Move button
    private final JButton colorButton = new JButton();
    private final JButton fillColorButton = new JButton();
    private final JButton undoButton = new JButton();
    private final JButton redoButton = new JButton();

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

        // add Select button
        selectButton.addActionListener(actionListener);
        selectButton.setActionCommand(ActionCommand.SELECT);
        selectButton.setToolTipText("Select Shape");
        add(selectButton);

        // add Move button (simple text, optional icon later)
        moveButton.addActionListener(actionListener);
        moveButton.setActionCommand(ActionCommand.MOVE);
        moveButton.setToolTipText("Move Selected Shape");
        add(moveButton);

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

        // Load and set icons for buttons
        String lineImageName = "line";
        String imgLocation = "images/" + lineImageName + ".png";
        URL lineImageURL = DrawingToolBar.class.getResource(imgLocation);
        lineButton.setIcon(new ImageIcon(lineImageURL, "line"));

        String rectImageName = "rectangle";
        imgLocation = "images/" + rectImageName + ".png";
        URL rectImageURL = DrawingToolBar.class.getResource(imgLocation);
        rectangleButton.setIcon(new ImageIcon(rectImageURL, "rectangle"));

        String ellipseImageName = "ellipse";
        imgLocation = "images/" + ellipseImageName + ".png";
        URL ellipseImageURL = DrawingToolBar.class.getResource(imgLocation);
        ellipseButton.setIcon(new ImageIcon(ellipseImageURL, "ellipse"));

        String undoImageName = "undo";
        imgLocation = "images/" + undoImageName + ".png";
        URL undoImageURL = DrawingToolBar.class.getResource(imgLocation);
        undoButton.setIcon(new ImageIcon(undoImageURL, "undo"));

        String redoImageName = "redo";
        imgLocation = "images/" + redoImageName + ".png";
        URL redoImageURL = DrawingToolBar.class.getResource(imgLocation);
        redoButton.setIcon(new ImageIcon(redoImageURL, "redo"));

        String selectImageName = "select";
        imgLocation = "images/" + selectImageName + ".png";
        URL selectImageURL = DrawingToolBar.class.getResource(imgLocation);
        selectButton.setIcon(new ImageIcon(selectImageURL, "select"));

        String moveImageName = "move";
        imgLocation = "images/" + moveImageName + ".png";
        URL moveImageURL = DrawingToolBar.class.getResource(imgLocation);
        moveButton.setIcon(new ImageIcon(moveImageURL, "select"));

        String colorImageName = "color";
        imgLocation = "images/" + colorImageName + ".png";
        URL colorImageURL = DrawingToolBar.class.getResource(imgLocation);
        colorButton.setIcon(new ImageIcon(colorImageURL, "color"));

        String fillImageName = "fill";
        imgLocation = "images/" + fillImageName + ".png";
        URL fillImageURL = DrawingToolBar.class.getResource(imgLocation);
        fillColorButton.setIcon(new ImageIcon(fillImageURL, "fill"));
        // Initialize button states
        updateButtonStates();
    }

    public void updateButtonStates() {
        undoButton.setEnabled(CommandService.canUndo());
        redoButton.setEnabled(CommandService.canRedo());
    }
}
