package pongscreensaver.pongscreensaver;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    public double x;
    public double y;
    public double velX, velY;
    protected double glowRadius;
    protected double glowResolution; // The higher the number, the lower the resolution of the glow is
    Bound bound;

    public Entity(double x, double y, double velX, double velY, Bound bound) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.bound = bound;
    }

    public Entity() {
        this(0, 0, 0, 0, null);
    }

    public void setGlowRadius(double glowRadius, double glowResolution) {
        this.glowRadius = glowRadius;
        this.glowResolution = glowResolution;
    }
    public abstract void draw(GraphicsContext g2d);


}
