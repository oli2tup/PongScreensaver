package pongscreensaver.pongscreensaver.Test;

import pongscreensaver.pongscreensaver.Ball;
import pongscreensaver.pongscreensaver.Bound;
import pongscreensaver.pongscreensaver.Paddle;
import org.junit.Before;
import org.junit.Test;

import static com.sun.tools.javac.resources.CompilerProperties.Fragments.Bound;
import static org.junit.Assert.*;

public class PaddleTest {
    Paddle paddle;
    Bound paddleBound;
    Bound ballBound;
    @Before
    public void setup() {
        paddleBound = new Bound(0, 1920);
        ballBound = new Bound(0, 1080, 1920, 0);
        paddle = new Paddle(100, 100, 500, 80, paddleBound, 100);
    }

    @Test
    public void testConstructor() {
        assertEquals(paddle.getX(), 100, 0.001);
        assertEquals(paddle.getY(), 100, 0.001);
        assertEquals(paddle.getWidth(), 80, 0.001);
        assertEquals(paddle.getHeight(), 500, 0.001);
    }

    @Test
    public void testUpdate() {
        double ballRadius = 10;
        double ballVel = 4;
        Ball ball = new Ball(paddle.getX() + paddle.getWidth() + 2 * ballVel, //x of the ball should be so that it's 2 units away from the right side of the paddle
                paddle.getY() + paddle.getHeight() / 2 - ballRadius, // putting the ball at the height in the middle of the paddle
                ballRadius,
                ballBound);
        ball.velX = -ballVel;
        ball.velY = ballVel;

        paddle.update(ball); // ball moved closer to the paddle
        ball.update();
        paddle.update(ball); // ball is touching the paddle
        ball.update();
        paddle.update(ball); // ball has bounced from the paddle and its x velocity should have changed
        ball.update();

        assertEquals(ball.velX, ballVel, 0.001);
        assertEquals(ball.velY, ballVel, 0.001);


        // Testing through simulating the game
        int numberOfSimulatedFrames = 10_000_000;
        for(int frame=0; frame<numberOfSimulatedFrames; frame++){
            ball.update();
            paddle.update(ball);
            assertTrue(paddle.getY() + paddle.getHeight() <= paddleBound.lowerBound); // Check if the paddle is not going below the screen
            assertTrue(paddle.getY() >= paddleBound.upperBound); // Check if the paddle is not going above the screen
        }

    }

    @Test
    public void testIsBallTouchingTop() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() + paddle.getWidth() / 2 - ballRadius,
                paddle.getY() + 2 - 2 * ballRadius, ballRadius,
                new Bound(0, 1080, 1920, 0));

        assertTrue(paddle.isBallTouchingTop(ball));
        assertFalse(paddle.isBallTouchingBottom(ball));
        assertFalse(paddle.isBallTouchingLeft(ball));
        assertFalse(paddle.isBallTouchingRight(ball));
        ball.y -= 4;
        assertFalse(paddle.isBallTouchingTop(ball));
        assertFalse(paddle.isBallTouchingBottom(ball));
        assertFalse(paddle.isBallTouchingLeft(ball));
        assertFalse(paddle.isBallTouchingRight(ball));
    }

    @Test
    public void testIsBallTouchingBottom() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() + paddle.getWidth() / 2 - ballRadius,
                paddle.getY() + paddle.getHeight() - 2,
                ballRadius,
                new Bound(0, 1080, 1920, 0));

        assertFalse(paddle.isBallTouchingTop(ball));
        assertTrue(paddle.isBallTouchingBottom(ball));
        assertFalse(paddle.isBallTouchingLeft(ball));
        assertFalse(paddle.isBallTouchingRight(ball));
        ball.y += 4;
        assertFalse(paddle.isBallTouchingTop(ball));
        assertFalse(paddle.isBallTouchingBottom(ball));
        assertFalse(paddle.isBallTouchingLeft(ball));
        assertFalse(paddle.isBallTouchingRight(ball));
    }

    @Test
    public void testIsBallTouchingLeft() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() - ballRadius * 2 + 2,
                paddle.getY() + paddle.getHeight() / 2 - ballRadius,
                ballRadius,
                new Bound(0, 1080, 1920, 0));

        assertFalse(paddle.isBallTouchingTop(ball));
        assertFalse(paddle.isBallTouchingBottom(ball));
        assertTrue(paddle.isBallTouchingLeft(ball));
        assertFalse(paddle.isBallTouchingRight(ball));
        ball.x -= 4;
        assertFalse(paddle.isBallTouchingTop(ball));
        assertFalse(paddle.isBallTouchingBottom(ball));
        assertFalse(paddle.isBallTouchingLeft(ball));
        assertFalse(paddle.isBallTouchingRight(ball));
    }

    @Test
    public void testIsBallTouchingRight() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() + paddle.getWidth() - 2,
                paddle.getY() + paddle.getHeight() / 2 - ballRadius,
                ballRadius,
                new Bound(0, 1080, 1920, 0));

        assertFalse(paddle.isBallTouchingTop(ball));
        assertFalse(paddle.isBallTouchingBottom(ball));
        assertFalse(paddle.isBallTouchingLeft(ball));
        assertTrue(paddle.isBallTouchingRight(ball));
        ball.x += 4;
        assertFalse(paddle.isBallTouchingTop(ball));
        assertFalse(paddle.isBallTouchingBottom(ball));
        assertFalse(paddle.isBallTouchingLeft(ball));
        assertFalse(paddle.isBallTouchingRight(ball));
    }

    @Test
    public void testIsBallTouchingTopLeftCorner() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() - ballRadius * 2 + 4,
                paddle.getY() - ballRadius * 2 + 4,
                ballRadius,
                new Bound(0, 1080, 1920, 0));
        assertTrue(paddle.isBallTouchingTopLeftCorner(ball));
        ball.x -= 4;
        ball.y -= 4;
        assertFalse(paddle.isBallTouchingTopLeftCorner(ball));
    }

    @Test
    public void testIsBallTouchingTopRightCorner() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() + paddle.getWidth() - 4,
                paddle.getY() - ballRadius * 2 + 4,
                ballRadius,
                new Bound(0, 1080, 1920, 0));
        assertTrue(paddle.isBallTouchingTopRightCorner(ball));
        ball.x += 4;
        ball.y -= 4;
        assertFalse(paddle.isBallTouchingTopRightCorner(ball));
    }

    @Test
    public void testIsBallTouchingBottomLeftCorner() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() - ballRadius * 2 + 4,
                paddle.getY() + paddle.getHeight() - 4,
                ballRadius,
                new Bound(0, 1080, 1920, 0));
        assertTrue(paddle.isBallTouchingBottomLeftCorner(ball));
        ball.x -= 4;
        ball.y += 4;
        assertFalse(paddle.isBallTouchingBottomLeftCorner(ball));
    }

    @Test
    public void testIsBallTouchingBottomRightCorner() {
        double ballRadius = 10;
        Ball ball = new Ball(paddle.getX() + paddle.getWidth() - 4,
                paddle.getY() + paddle.getHeight() - 4,
                ballRadius,
                new Bound(0, 1080, 1920, 0));
        assertTrue(paddle.isBallTouchingBottomRightCorner(ball));
        ball.x += 4;
        ball.y += 4;
        assertFalse(paddle.isBallTouchingBottomRightCorner(ball));
    }

    @Test
    public void testSetPaddleToBallColour(){

    }

}
