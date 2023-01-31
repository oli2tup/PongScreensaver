package pongscreensaver.pongscreensaver.Test;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import pongscreensaver.pongscreensaver.GameCanvas;

import static org.junit.Assert.assertEquals;

public class CanvasTest {
    GameCanvas canvas;

    @Before
    public void setup() {
        canvas = new GameCanvas(1920, 1080);
    }

    @Test
    public void testConstructor() {
        setup();
        assertEquals(canvas.width,1920);
        assertEquals(canvas.height,1080);
        assertEquals(canvas.background, Color.BLACK);

    }
}
