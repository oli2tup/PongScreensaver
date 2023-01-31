package pongscreensaver.pongscreensaver.Test;

import pongscreensaver.pongscreensaver.Ball;
import pongscreensaver.pongscreensaver.Bound;
import pongscreensaver.pongscreensaver.Trail;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TrailTest {
    Ball ball;
    Trail trail;

    @Before
    public void initialise() {
        ball = new Ball(100, 200, 10, new Bound(0, 1080, 1920, 0));
        trail = new Trail(5);
    }

    @Test
    public void testAddPoint() {
        assertEquals(trail.getTrail().size(), 0);
        trail.addPoint(10, 20, Color.hsb(0, 1, 1));
        assertEquals(trail.getTrail().size(), 1);
    }

    @Test
    public void testUpdate() {
        trail.update(ball);
        assertEquals(trail.getBallX(), ball.x, 0.001);
        assertEquals(trail.getBallY(), ball.y, 0.001);
    }

    @Test
    public void testReset() {
        assertNotEquals(trail.getNumberOfElements(), 1);
        trail.trailReset(ball);
        trail.update(ball);
        assertEquals(trail.getBallX(), ball.getCentreX(), 0.001);
        assertEquals(trail.getBallY(), ball.getCentreY(), 0.001);
        assertEquals(trail.getNumberOfElements(), 1);
    }


}
