package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private boolean top, right, left, bottom;

    private final double width = 1200, height = 720;

    private final Pane root = new Pane();
    private final Pane entityRoot = new Pane();

    private ArrayList<Node> platform = new ArrayList<>();

    private Node player = new Player();


    public void createMap() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        createMap();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        top = true;
                        break;
                    case D:
                        right = true;
                        break;
                    case A:
                        left = true;
                        break;
                    case S:
                        bottom = true;
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        top = false;
                        break;
                    case D:
                        right = false;
                        break;
                    case A:
                        left = false;
                        break;
                    case S:
                        bottom = false;
                        break;
                }
            }
        });
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
