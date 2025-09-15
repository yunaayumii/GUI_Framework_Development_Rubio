package com.rubio.draw;

import com.rubio.draw.view.DrawingMenuBar;
import com.rubio.draw.service.DeawingCommandAppService;
import com.rubio.draw.service.DrawingAppService;
import com.rubio.draw.controller.DrawingController;
import com.rubio.draw.view.DrawingView;
import com.rubio.draw.view.DrawingFrame;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AppService drawingAppService = new DrawingAppService();
        AppService appService = new DeawingCommandAppService(drawingAppService);

        DrawingFrame drawingFrame = new DrawingFrame(appService);
        DrawingMenuBar drawingMenuBar = new DrawingMenuBar(appService);

        DrawingView drawingView = new DrawingView(appService);
        DrawingController drawingController = new DrawingController(appService, drawingView);
        drawingView.addMouseMotionListener(drawingController);
        drawingView.addMouseListener(drawingController);
        drawingFrame.add(drawingView);


        drawingMenuBar.setVisible(true);
        drawingFrame.setJMenuBar(drawingMenuBar);

        drawingFrame.setVisible(true);
        drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingFrame.setSize(500,500);
    }
}