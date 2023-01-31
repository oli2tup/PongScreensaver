package pongscreensaver.pongscreensaver;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Window {
    Stage stage;
    Scene initialScene;
    String windowName;
    int width;
    int height;

    public Window (int width, int height, String windowName, Stage stage){
        this.width = width;
        this.height = height;
        this.windowName = windowName;
        this.stage = stage;
    }

    public void openWindow(){
       Scene s = createInitalScene();

        stage.setTitle(windowName);
        setInitialScene(s);
        stage.show();
    }

    private Scene createInitalScene() {
        Pane pane = new StackPane();
        Button startButton = new Button("Start Screen Saver");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFullScreen();
            }
        });

        pane.getChildren().add(startButton);
        StackPane.setAlignment(startButton, Pos.CENTER);
        initialScene = new Scene(pane, width, height);
        return initialScene;
    }

    public void setFullScreen(){
        Pong pong = new Pong(this);
    }

    private void setInitialScene(Scene s){
        stage.setScene(s);
        setStagePosition();
    }

    private void setStagePosition() {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        stage.setX((screen.getWidth()-width)/2);
        stage.setY((screen.getHeight()-height)/2);
    }

    public void exitFullScreen(){
        Scene s = createInitalScene();
        setInitialScene(s);
        stage.setFullScreen(false);

    }

    public String getWindowName() {
        return windowName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
