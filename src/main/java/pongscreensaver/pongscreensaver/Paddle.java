package pongscreensaver.pongscreensaver;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Paddle extends Entity {
    double width;
    double height;
    boolean isBallTouching;
    public Color color;
    public int hueValue;
    Random random = new Random();

    public Paddle(double x, double y, double height, double width, Bound bound, int hueValue) {
        super(x, y, 0, 5, bound);
        this.height = height;
        this.width = width;
        this.isBallTouching = false;
        this.hueValue = random.nextInt(360);
        this.color = Color.hsb(hueValue, 1, 1);
    }

    public void draw(GraphicsContext g2d) {
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

                double glowSegmentWidth = this.width + glowSegmentDistance*2;
                double glowSegmentHeight = this.height + glowSegmentDistance*2;



                g2d.setFill(glowSegmentColor);
                g2d.fillRoundRect(glowSegmentX, glowSegmentY, glowSegmentWidth, glowSegmentHeight, glowSegmentDistance*2, glowSegmentDistance*2  );

            }
        }
        g2d.setFill(color);
        g2d.fillRect(this.x, this.y, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }



    public boolean isBallTouchingTop(Ball ball) {
        return ball.x + ball.radius > this.x
                && ball.x + ball.radius < this.x + this.width
                && ball.y + 2 * ball.radius > this.y
                && ball.y < this.y;
    }

    public boolean isBallTouchingBottom(Ball ball) {
        return ball.x + ball.radius >= this.x
                && ball.x + ball.radius <= this.x + this.width
                && ball.y <= this.y + this.height
                && ball.y + ball.radius * 2 >= this.y + this.height;
    }

    public boolean isBallTouchingLeft(Ball ball) {
        return ball.y + ball.radius >= this.y
                && ball.y + ball.radius <= this.y + this.height
                && ball.x + ball.radius * 2 >= this.x
                && ball.x <= this.x;
    }

    public boolean isBallTouchingRight(Ball ball) {
        return ball.y + ball.radius >= this.y
                && ball.y + ball.radius <= this.y + this.height
                && ball.x <= this.x + this.width
                && ball.x + ball.radius * 2 >= this.x + this.width;
    }

    public boolean isBallTouchingTopLeftCorner(Ball ball) {
        return !isBallTouchingTop(ball)
                && !isBallTouchingLeft(ball)
                && ball.distFromCentreTo(this.x, this.y) <= ball.radius;
    }

    public boolean isBallTouchingTopRightCorner(Ball ball) {
        return !isBallTouchingTop(ball)
                && !isBallTouchingRight(ball)
                && ball.distFromCentreTo(this.x + this.width, this.y) <= ball.radius;
    }

    public boolean isBallTouchingBottomLeftCorner(Ball ball) {
        return !isBallTouchingBottom(ball)
                && !isBallTouchingLeft(ball)
                && ball.distFromCentreTo(this.x, this.y + this.height) <= ball.radius;
    }

    public boolean isBallTouchingBottomRightCorner(Ball ball) {
        return !isBallTouchingBottom(ball)
                && !isBallTouchingRight(ball)
                && ball.distFromCentreTo(this.x + this.width, this.y + this.height) <= ball.radius;
    }

    private boolean isBallTouchingCorner(Ball ball) {
        return isBallTouchingBottomLeftCorner(ball) || isBallTouchingBottomRightCorner(ball)
                || isBallTouchingTopLeftCorner(ball) || isBallTouchingTopRightCorner(ball);
    }

    private boolean isPaddleTouchingWall() {
        return y < bound.upperBound || y + height > bound.lowerBound;
    }

    public void update(Ball ball) {

        this.y = ball.getCentreY() - (this.height/2);
        if (y < bound.upperBound)
            this.y = 0;

        if (y + height > bound.lowerBound)
            this.y = bound.lowerBound - this.height;

        if (isBallTouching) {
            isBallTouching = (isBallTouchingLeft(ball) || isBallTouchingRight(ball)
                    || isBallTouchingTop(ball) || isBallTouchingBottom(ball)
                    || isBallTouchingCorner(ball));
        }
        if (isBallTouching) return;
        if (isBallTouchingLeft(ball) || isBallTouchingRight(ball)) {

            ball.velX *= -1;
            isBallTouching = true;
            setPaddleToBallColour(ball);
            ball.bouncedFromPaddle(this);


        } else if (isBallTouchingTop(ball)) {
            ball.y = this.y - ball.radius * 2;
            ball.velY *= -1;
            isBallTouching = true;
            setPaddleToBallColour(ball);
            ball.bouncedFromPaddle(this);

        } else if (isBallTouchingBottom(ball)) {
            ball.y = this.y + height + 1;
            ball.velY *= -1;
            isBallTouching = true;
            setPaddleToBallColour(ball);
            ball.bouncedFromPaddle(this);

        } else if (isBallTouchingCorner(ball)) {
            ball.velX *= -1;
            ball.velY *= -1;
            isBallTouching = true;
            setPaddleToBallColour(ball);
            ball.bouncedFromPaddle(this);
        }




    }

    private void setPaddleToBallColour(Ball ball) {
        if (isBallTouching) {
            this.color = ball.color;
        }
    }


}
