package game_OOP;

import game_OOP.entity.Sprite;
import game_OOP.map.MapOne;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GeneralScene extends Scene {
    public AnchorPane root = new AnchorPane();
    protected Canvas canvas;
    public GraphicsContext gc;

    public Pane pane = new Pane();
    public Label labelTime = new Label();
    public Label labelPoint = new Label();

    protected Set<KeyCode> pressedKey = new HashSet<>();
    protected Set<KeyCode> resealedKey = new HashSet<>();
    protected Set<KeyCode> bombKey = new HashSet<>();

    protected static final double WIDTH = 1200;
    protected static final double HEIGHT = 664;

    private final int countTime = 302;
    private final int countPoint = 0;
    public int count = 0;
    public Timer timer = new Timer();

    public boolean top, left, right, bot;

    public void setTime() {
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = countTime;
            @Override
            public void run() {
                Platform.runLater(() -> labelTime.setText("Time: " + count));
                count--;

                if(count < 0) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public void setPoint() {
        labelPoint.setText("Point: " + countPoint);
    }

    public void createAttr() {
        pane.setTranslateX(0);
        pane.setTranslateY(0);
        pane.setPrefHeight(40);
        pane.setPrefWidth(WIDTH);
        pane.setStyle("-fx-background-color: black");

        labelTime.setTranslateX(Sprite.size*4);
        labelTime.setTranslateY(10);
        labelTime.setTextFill(Color.WHITE);
        labelTime.setFont(new Font("Arial", 15));
        setTime();

        labelPoint.setTranslateX(Sprite.size*19);
        labelPoint.setTranslateY(10);
        labelPoint.setTextFill(Color.WHITE);
        labelPoint.setFont(new Font("Arial", 15));
        setPoint();

        pane.getChildren().addAll(labelTime, labelPoint);
    }

    public GeneralScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);

        canvas = new Canvas(Sprite.size * MapOne.map[0].length(), HEIGHT);
        gc = canvas.getGraphicsContext2D();

        createAttr();

        this.setRoot(root);

        root.getChildren().addAll(pane, canvas);
    }

    public abstract void draw();
}
