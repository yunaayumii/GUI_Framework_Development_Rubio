package com.gabriel.drawfx.service;

import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public final class MoverService {
    public void  move(Shape shape, Point newLoc){
        // compute delta from current location to new location
        Point curr = shape.getLocation();
        if (curr == null) {
            // if shape has no location yet, just set it
            shape.setLocation(newLoc);
            return;
        }
        int dx = newLoc.x - curr.x;
        int dy = newLoc.y - curr.y;

        // shift both location and end so geometry translates without resizing
        shape.setLocation(newLoc);
        Point end = shape.getEnd();
        if (end != null) {
            // add the calculated difference to the end location
            shape.setEnd(new Point(end.x + dx, end.y + dy));
        }
     }

    // render a move preview using xor mode
    // temporarily moves shape to preview location, renders in xor, then restores original location
    public void renderMovePreview(Graphics g, Shape shape, Point previewLoc) {
        // save original location and end
        Point origLoc = new Point(shape.getLocation());
        Point origEnd = shape.getEnd() == null ? null : new Point(shape.getEnd());

        // calculate how much we're moving
        int dx = previewLoc.x - origLoc.x;
        int dy = previewLoc.y - origLoc.y;

        // temporarily move shape to preview location
        shape.setLocation(previewLoc);
        if (origEnd != null) {
            shape.setEnd(new Point(origEnd.x + dx, origEnd.y + dy));
        }

        // render in xor mode so it can erase itself when redrawn
        shape.getRendererService().render(g, shape, true);

        // restore original location
        shape.setLocation(origLoc);
        shape.setEnd(origEnd);
    }
}
