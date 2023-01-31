package pongscreensaver.pongscreensaver;

import javafx.scene.paint.Color;

public class TrailElement {
    public double x, y;
    private Color colour;

    public TrailElement(double x, double y, Color colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public Color getColour() {
        return this.colour;
    }
}
