package pongscreensaver.pongscreensaver.Test;

import org.junit.Before;
import org.testng.annotations.Test;
import pongscreensaver.pongscreensaver.Window;

import static org.junit.Assert.assertEquals;

public class WindowTest {
    Window window;

    @Before
    public void setup(){
        this.window = new Window(320,240,"Test", null);
    }
    @Test
    public void windowCreation(){
        setup();
        assertEquals("Test",window.getWindowName());
        assertEquals(320,window.getWidth());
        assertEquals(240,window.getHeight());
    }

    @Test
    public void fullScreen(){

    }

}