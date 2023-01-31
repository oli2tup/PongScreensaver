package pongscreensaver.pongscreensaver.Test;

import pongscreensaver.pongscreensaver.TrailElement;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TrailElementTest {
    @Test
    public void testConstructor() {
        TrailElement trail = new TrailElement(100, 200, Color.hsb(0,1,1));
        assertEquals(trail.x, 100, 0.00001);
        assertEquals(trail.y, 200, 0.00001);
    }
}
