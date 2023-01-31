package pongscreensaver.pongscreensaver.Test;

import org.junit.Before;
import org.testng.annotations.Test;
import pongscreensaver.pongscreensaver.Ball;
import pongscreensaver.pongscreensaver.Bound;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BallTest{
    Ball ball;

    @Before
    public void setup() {
        ball = new Ball(100, 100, 10, new Bound(0, 1080, 1920, 0));
    }

    @Test
    public void testConstructor() {
        assertEquals(ball.x, 100, 0.001);
        assertEquals(ball.y, 100, 0.001);
        assertEquals(ball.radius, 10, 0.001);
        assertEquals(ball.upperBound, 0, 0.001);
        assertEquals(ball.lowerBound, 1080, 0.001);
        assertEquals(ball.rightBound, 1920, 0.001);
        assertEquals(ball.leftBound, 0, 0.001);
    }

    @Test
    public void testUpdate() {
        ball = new Ball(100, 100, 10, new Bound(0, 1080, 1920, 0));
        assertEquals(ball.velX, 0, 0.001);
        assertEquals(ball.velY, 0, 0.001);

        ball.velX = 1;
        ball.velY = 1;

        double startX = ball.rightBound - 2 - ball.radius * 2;
        double startY = ball.lowerBound - 2 - ball.radius * 2;

        ball.x = startX;
        ball.y = startY;

        ball.update();
        assertEquals(ball.x, startX + 1, 0.001);
        assertEquals(ball.y, startY + 1, 0.001);

        ball.update();
        assertEquals(ball.x, startX + 2, 0.001);
        assertEquals(ball.y, startY + 2, 0.001);

        assertEquals(ball.velX, -1, 0.001);
        assertEquals(ball.velY, -1, 0.001);

        ball.update();
        assertEquals(ball.x, startX + 1, 0.001);
        assertEquals(ball.y, startY + 1, 0.001);
    }

    @Test
    public void testDistFrom() {
        assertEquals(ball.distFromCentreTo(ball.x + ball.radius, ball.y + ball.radius), 0, 0.001);

        assertEquals(ball.distFromCentreTo(ball.x + 10 + ball.radius, ball.y + ball.radius), 10, 0.001);
        assertEquals(ball.distFromCentreTo(ball.x + ball.radius, ball.y + 10 + ball.radius), 10, 0.001);
        assertEquals(ball.distFromCentreTo(ball.x - 10 + ball.radius, ball.y + ball.radius), 10, 0.001);
        assertEquals(ball.distFromCentreTo(ball.x + ball.radius, ball.y - 10 + ball.radius), 10, 0.001);

        assertEquals(ball.distFromCentreTo(ball.x + 10 + ball.radius, ball.y + 10 + ball.radius), Math.sqrt(200), 0.001);
        assertEquals(ball.distFromCentreTo(ball.x - 10 + ball.radius, ball.y + 10 + ball.radius), Math.sqrt(200), 0.001);
        assertEquals(ball.distFromCentreTo(ball.x - 10 + ball.radius, ball.y - 10 + ball.radius), Math.sqrt(200), 0.001);
    }

    @Test
    public void testRandomPosition() {
        double oldX = ball.getCentreX();
        double oldY = ball.getCentreY();
        ball.reset();
        assertNotEquals(ball.getCentreX(), oldX);
        assertNotEquals(ball.getCentreY(), oldY);

    }

    @Test
    public void testVelocityComponents() {
        ball.reset();
        assertEquals(Math.pow(ball.velX, 2) + Math.pow(ball.velY, 2), Math.pow(Ball.SPEED, 2), 0.001);
    }

    @Test
    public void testSetHueValue(){
        ball.reset();
        int hueValue = ball.hueValue;
        ball.bouncedFromPaddle(null);
        assertEquals(ball.hueValue, hueValue+50);
    }

}
