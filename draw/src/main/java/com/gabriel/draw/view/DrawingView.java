package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingController;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.ShapeMode;

import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    AppService appService;

    public DrawingView(AppService appService){
        this.appService = appService;

        // Set layout for the drawing view
        this.setLayout(new BorderLayout());

        // Simple shape selection using JComboBox
        JPanel topPanel = new JPanel();
        String[] shapes = {"Line", "Ellipse", "Rectangle"};
        JComboBox<String> shapeBox = new JComboBox<>(shapes);

        // Set the default shape
        shapeBox.setSelectedIndex(0);

        // Update shape mode when selection changes
        shapeBox.addActionListener(e -> {
            String selected = (String) shapeBox.getSelectedItem();
            if ("Line".equals(selected)) {
                appService.setShapeMode(ShapeMode.Line);
            } else if ("Ellipse".equals(selected)) {
                appService.setShapeMode(ShapeMode.Ellipse);
            } else if ("Rectangle".equals(selected)) {
                appService.setShapeMode(ShapeMode.Rectangle);
            }
        });

        topPanel.add(new JLabel("Select shape: "));
        topPanel.add(shapeBox);

        // Add the combo box panel to the top of the view
        this.add(topPanel, BorderLayout.NORTH);

        // Initialize the controller for drawing
        DrawingController drawingController = new DrawingController(appService, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Drawing drawing = (Drawing) appService.getModel();
        for(Shape shape : drawing.getShapes()){
            shape.getRendererService().render(g, shape, false);
        }
    }
}
