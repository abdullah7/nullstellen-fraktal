package fraktal;

import java.awt.*;

public interface Renderable {
    Color calcPixel(Point2F coordinate);
}
