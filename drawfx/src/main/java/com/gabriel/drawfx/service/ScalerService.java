package com.gabriel.drawfx.service;

import com.gabriel.drawfx.SelectionMode;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public final class ScalerService {

    private static final int HANDLE_SIZE = 8; // size of the scaling handles

    public void scale(Shape shape, Point newPoint, SelectionMode mode){
        if (mode == SelectionMode.None) {
            return; // no scaling if no handle is selected
        }

        Point start = shape.getLocation();
        Point end = shape.getEnd();

        // Get current bounds
        int minX = Math.min(start.x, end.x);
        int maxX = Math.max(start.x, end.x);
        int minY = Math.min(start.y, end.y);
        int maxY = Math.max(start.y, end.y);

        // Calculate new bounds based on which handle is being dragged
        switch (mode) {
            case UpperLeft:
                minX = newPoint.x;
                minY = newPoint.y;
                break;
            case UpperRight:
                maxX = newPoint.x;
                minY = newPoint.y;
                break;
            case LowerLeft:
                minX = newPoint.x;
                maxY = newPoint.y;
                break;
            case LowerRight:
                maxX = newPoint.x;
                maxY = newPoint.y;
                break;
            case MiddleTop:
                minY = newPoint.y;
                break;
            case MiddleBottom:
                maxY = newPoint.y;
                break;
            case MiddleLeft:
                minX = newPoint.x;
                break;
            case MiddleRight:
                maxX = newPoint.x;
                break;
        }

        // Update shape with new bounds
        shape.setLocation(new Point(minX, minY));
        shape.setEnd(new Point(maxX, maxY));
    }

    // Determine which scaling handle (if any) the point is near
    public SelectionMode getSelectionMode(Shape shape, Point p, int tolerance) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();

        int minX = Math.min(start.x, end.x);
        int maxX = Math.max(start.x, end.x);
        int minY = Math.min(start.y, end.y);
        int maxY = Math.max(start.y, end.y);

        int centerX = (minX + maxX) / 2;
        int centerY = (minY + maxY) / 2;

        // Check corner handles first (they take priority)
        if (isNearPoint(p, minX, minY, tolerance)) {
            return SelectionMode.UpperLeft;
        }
        if (isNearPoint(p, maxX, minY, tolerance)) {
            return SelectionMode.UpperRight;
        }
        if (isNearPoint(p, minX, maxY, tolerance)) {
            return SelectionMode.LowerLeft;
        }
        if (isNearPoint(p, maxX, maxY, tolerance)) {
            return SelectionMode.LowerRight;
        }

        // Check edge handles
        if (isNearPoint(p, centerX, minY, tolerance)) {
            return SelectionMode.MiddleTop;
        }
        if (isNearPoint(p, centerX, maxY, tolerance)) {
            return SelectionMode.MiddleBottom;
        }
        if (isNearPoint(p, minX, centerY, tolerance)) {
            return SelectionMode.MiddleLeft;
        }
        if (isNearPoint(p, maxX, centerY, tolerance)) {
            return SelectionMode.MiddleRight;
        }

        return SelectionMode.None;
    }

    // Helper method to check if a point is near a specific location
    private boolean isNearPoint(Point p, int x, int y, int tolerance) {
        int dx = p.x - x;
        int dy = p.y - y;
        return dx * dx + dy * dy <= tolerance * tolerance;
    }

    // Render a scale preview using XOR mode
    public void renderScalePreview(Graphics g, Shape shape, Point newPoint, SelectionMode mode) {
        // Save original location and end
        Point origLoc = new Point(shape.getLocation());
        Point origEnd = new Point(shape.getEnd());

        // Temporarily scale the shape
        scale(shape, newPoint, mode);

        // Render in XOR mode so it can erase itself when redrawn
        shape.getRendererService().render(g, shape, true);

        // Restore original bounds
        shape.setLocation(origLoc);
        shape.setEnd(origEnd);
    }

    // Draw scaling handles on a selected shape
    public void drawScaleHandles(Graphics g, Shape shape) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();

        int minX = Math.min(start.x, end.x);
        int maxX = Math.max(start.x, end.x);
        int minY = Math.min(start.y, end.y);
        int maxY = Math.max(start.y, end.y);

        int centerX = (minX + maxX) / 2;
        int centerY = (minY + maxY) / 2;

        g.setColor(Color.BLUE);
        int halfSize = HANDLE_SIZE / 2;

        // Draw corner handles
        g.fillRect(minX - halfSize, minY - halfSize, HANDLE_SIZE, HANDLE_SIZE);
        g.fillRect(maxX - halfSize, minY - halfSize, HANDLE_SIZE, HANDLE_SIZE);
        g.fillRect(minX - halfSize, maxY - halfSize, HANDLE_SIZE, HANDLE_SIZE);
        g.fillRect(maxX - halfSize, maxY - halfSize, HANDLE_SIZE, HANDLE_SIZE);

        // Draw edge handles
        g.fillRect(centerX - halfSize, minY - halfSize, HANDLE_SIZE, HANDLE_SIZE);
        g.fillRect(centerX - halfSize, maxY - halfSize, HANDLE_SIZE, HANDLE_SIZE);
        g.fillRect(minX - halfSize, centerY - halfSize, HANDLE_SIZE, HANDLE_SIZE);
        g.fillRect(maxX - halfSize, centerY - halfSize, HANDLE_SIZE, HANDLE_SIZE);
    }
}
