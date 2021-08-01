package game;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private boolean right, left, top, bottom;

    private double speed = 2;
    private double timeLine = 100;

    private final double width = 1600, height = 720;

    private final Pane root = new Pane();
    private final Pane entityRoot = new Pane();

    private Group player;
    private Timeline t = new Timeline();

    public void createMap() {
        Rectangle bg = new Rectangle(width, height);

        for(double i=0; i<20; i++) {
            if(i%2==0) {
                Rectangle r = new Rectangle(i*64, 200, 64, 64);
                r.setFill(Color.GRAY);
                entityRoot.getChildren().addAll(r);
            }
            else {
                Rectangle r = new Rectangle(i*64, 100, 64, 64);
                r.setFill(Color.GREEN);
                entityRoot.getChildren().addAll(r);
            }
        }

        player = new Group(Sprite.player_sprite_02);
        player.setTranslateX(0);
        player.setTranslateY(0);

        player.translateXProperty().addListener((observableValue, number, t1) -> {
            int offset = t1.intValue();

            if(offset > width/2 && offset < ((20*64) - width/2)) {
                entityRoot.setLayoutX(-(offset-width/2));
            }
        });

        entityRoot.getChildren().addAll(player);
        root.getChildren().addAll(bg, entityRoot);
    }

    public void render() {
        Sprite.cropImagePlayer();
        createMap();
    }

    public void movingEntity() {
        if(right) {
            moveRight(speed);
        }
        //
        else if(top) {
            moveTop(speed);
        }
        //
        else if(left) {
            moveLeft(speed);
        }
        //
        else if(bottom) {
            moveBottom(speed);
        }
        else {
            player.getChildren().setAll(Sprite.player_sprite_02);
        }
    }

    public void moveLeft(double value) {
        player.setTranslateX(player.getTranslateX() - value);

       t.setCycleCount(Timeline.INDEFINITE);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_03);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*2),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_13);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*3),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_23);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*4),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_33);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*5),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_43);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*6),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_53);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*7),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_63);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*8),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_73);
                }
        ));
        t.play();
    }

    public void moveRight(double value) {
        player.setTranslateX(player.getTranslateX() + value);
        t.setCycleCount(Timeline.INDEFINITE);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_01);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*2),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_11);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*3),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_21);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*4),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_31);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*5),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_41);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*6),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_51);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*7),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_61);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*8),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_71);
                }
        ));
        t.play();
    }

    public void moveTop(double value) {
        player.setTranslateY(player.getTranslateY() - value);

        t.setCycleCount(Timeline.INDEFINITE);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_00);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*2),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_10);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*3),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_20);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*4),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_30);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*5),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_40);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*6),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_50);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*7),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_60);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*8),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_70);
                }
        ));
        t.play();

    }

    public void moveBottom(double value) {
        player.setTranslateY(player.getTranslateY() + value);

        t.setCycleCount(Timeline.INDEFINITE);

        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_12);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*2),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_22);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*3),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_32);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*4),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_42);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*5),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_52);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*6),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_62);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*7),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_72);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(timeLine*8),
                (ActionEvent e) -> {
                    player.getChildren().setAll(Sprite.player_sprite_02);
                }
        ));
        t.play();
    }

    @Override
    public void start(Stage stage) {
        render();
        Scene scene = new Scene(root, width, height);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        right = true;
                        break;
                    case A:
                        left = true;
                        break;
                    case W:
                        top = true;
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
                    case D:
                        right = false;
                        break;
                    case A:
                        left = false;
                        break;
                    case W:
                        top = false;
                        break;
                    case S:
                        bottom = false;
                        break;
                }
            }
        });
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                movingEntity();
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
