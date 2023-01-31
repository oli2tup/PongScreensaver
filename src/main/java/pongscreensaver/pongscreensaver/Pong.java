package pongscreensaver.pongscreensaver;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Pong {
    Scene scene;
    Window window;
    GameCanvas gCanvas;
    Ball ball;
    Paddle paddle, paddleRight;
    List<Entity> drawables;
    private static int FRAME_RATE = 60;
    private static final int FRAMES_BEFORE_RESET = FRAME_RATE * 30;
    int frameReset;


    public Pong(Window window){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        int width = (int) primaryScreenBounds.getWidth();
        int height = (int) primaryScreenBounds.getHeight();
        this.window = window;
        Pane pane = new Pane();
        scene = new Scene(pane);
        scene.setCursor(Cursor.NONE);
        window.stage.setScene(scene);
        window.stage.setFullScreen(true);
        gCanvas = new GameCanvas(width, height);
        pane.getChildren().add(gCanvas.canvas);
        drawables = new ArrayList<>();


        paddle = new Paddle(100, 150,300,50, new Bound(0, height), 100);
        paddleRight = new Paddle(width - 150, 150, 300, 50, new Bound(0, height), 230);
        ball = new Ball(paddle.getX()+ paddle.getWidth()+200, paddle.getY()+ paddle.getHeight()+200, 20, new Bound(0, height, width,0));



        drawables.add(ball);
        drawables.add(paddle);
        this.frameReset = FRAMES_BEFORE_RESET;
        drawables.add(paddleRight);

        double defaultGlowRadius = 50;
        double defaultGlowResolution = 2;
        ball.setGlowRadius(defaultGlowRadius, defaultGlowResolution);
        paddle.setGlowRadius(defaultGlowRadius, defaultGlowResolution);
        paddleRight.setGlowRadius(defaultGlowRadius, defaultGlowResolution);




        // Stops mouse movement event immediately closing screensaver
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                setEventHandlers();
            }
        }, 1000);

        startUpdateDrawLoop();
    }




    private void  startUpdateDrawLoop() {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                update();
                draw();
            }
        };
        Timer t = new Timer();

        t.schedule(tt, 0, 1000/ FRAME_RATE);
    }


    
    public void update() {
        if (frameReset <= 0){
            ball.reset();
            frameReset = FRAMES_BEFORE_RESET;
        }
        paddle.update(ball);
        paddleRight.update(ball);
        ball.update();
        frameReset--;
    }

    public void draw(){
        gCanvas.draw(drawables); //draw paddle here
    }

    private void setEventHandlers() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                window.exitFullScreen();
            }
        });
        // Commented the code out so that we don't have to be careful about touching the mouse
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.exitFullScreen();
            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.exitFullScreen();
            }
        });
    }

}
