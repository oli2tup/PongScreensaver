package pongscreensaver.pongscreensaver;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Ball extends Entity {
    public double radius;
    public double upperBound, lowerBound, rightBound, leftBound;
    private Trail trail;
    public static final int SPEED = 10;
    Random random = new Random();
    public Color color;
    public int hueValue;


    public Ball(double x, double y, double radius, Bound bound) {
        super(x, y, 0, 0, bound);
        this.radius = radius;
        this.upperBound = bound.upperBound;
        this.lowerBound = bound.lowerBound;
        this.rightBound = bound.rightBound;
        this.leftBound = bound.leftBound;
        trail = new Trail(10);

        hueValue = random.nextInt(360);
        this.color = Color.hsb(hueValue, 1, 1);
        reset();
        trail.addPoint(getCentreX(), getCentreY(), this.color);
    }

    public void draw(GraphicsContext g2d) {
        trail.draw(g2d);
        
        if (this.glowRadius > 0) {
            double glowSegmentOpacity = this.glowResolution / glowRadius;

            Color glowSegmentColor = new Color(
                    this.color.getRed(),
                    this.color.getGreen(),
                    this.color.getBlue(),
                    glowSegmentOpacity);

            for (double glowSegmentDistance = this.glowRadius; glowSegmentDistance > 0; glowSegmentDistance-=this.glowResolution) {
                double glowSegmentX = this.x - glowSegmentDistance;
                double glowSegmentY = this.y - glowSegmentDistance;

                double glowSegmentRadius = this.radius + glowSegmentDistance;

                g2d.setFill(glowSegmentColor);
                g2d.fillOval(glowSegmentX, glowSegmentY, glowSegmentRadius * 2, glowSegmentRadius * 2);

            }
        }

        g2d.setFill(color);
        g2d.fillOval(this.x, this.y, this.radius * 2, this.radius * 2);
    }

    public void setHueValue(int hueValue) {
        hueValue += 50;
        this.hueValue = hueValue % 360;
        this.color = Color.hsb(this.hueValue, 1, 1);
    }

    public void bouncedFromPaddle(Paddle paddle) {
        setHueValue(this.hueValue);
        trail.addPoint(getCentreX(), getCentreY(), this.color);


    }

    public void update() {
        this.x += velX;
        this.y += velY;

        if (this.x >= rightBound - radius * 2 || this.x <= leftBound) {
            velX *= -1;
            trail.addPoint(getCentreX(), getCentreY(),this.color);
        }
        if (this.y >= lowerBound - radius * 2 || this.y <= upperBound) {
            velY *= -1;
            trail.addPoint(getCentreX(), getCentreY(),this.color);
        }


        trail.update(this);
    }

    public double distFromCentreTo(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x - this.radius, 2) + Math.pow(y - this.y - this.radius, 2));
    }

    public double getCentreX() {
        return this.x + radius;
    }

    public double getCentreY() {
        return this.y + radius;
    }

    public void reset() {
        x = random.nextInt((int) (rightBound - 320)) + 160;
        y = random.nextInt((int) (lowerBound - 200)) + 100;
        trail.trailReset(this);

        createRandomVelocity();
    }

    private void createRandomVelocity() {
        int angle = random.nextInt(360);
        double radAngle = Math.toRadians(angle);

        double xComponent = SPEED * Math.sin(radAngle);
        double yComponent = SPEED * Math.cos(radAngle);

        velX = xComponent;
        velY = yComponent;
    }

}
