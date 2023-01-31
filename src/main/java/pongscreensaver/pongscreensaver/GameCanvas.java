package pongscreensaver.pongscreensaver;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class GameCanvas {
    public int width;
    public int height;

    public Color background;

    public Canvas canvas;
    GraphicsContext gc;

    public GameCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.background = Color.BLACK;
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
    }

    public void draw(List<Entity> drawables){
        gc.setFill(background);
        gc.fillRect(0,0,width,height);
        for (Entity d : drawables) {
            d.draw(gc);
        }
    }
}
