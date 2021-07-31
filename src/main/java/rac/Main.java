package rac;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    private final double move = 8;
    private boolean top, bot, right, left;
    private final double width = 1280, height = 720;

    private ArrayList<Node> array = new ArrayList<>();

    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();

    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();


    private Node player;
    private Point2D playerVelo = new Point2D(0, 0);
    private boolean canJump = true;

    private int levelWidth;

    private String[] level = {
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000000000000000000000000000000",
            "000111000000000000000000000000",
            "000000001110000000000000000000",
            "000002000000011100002000000000",
            "000001110000000000011100011000",
            "111111110011110001111100111111"
    };

    public Node createEntity(double x, double y, double width, double height, Color color) {
        Rectangle r = new Rectangle(width, height);
        r.setTranslateX(x);
        r.setTranslateY(y);
        r.setFill(color);
        gameRoot.getChildren().add(r);
        return r;
    }

    private void createMap() {
        Rectangle bg = new Rectangle(1280, 720);

        levelWidth = level[0].length() * 60;

        for (int i = 0; i < level.length; i++) {
            String line = level[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        Node platform = createEntity(j*60, i*60, 60, 60, Color.BROWN);
                        array.add(platform);
                        break;
                }
            }
        }

        player = createEntity(0, 600, 40, 40, Color.BLUE);

        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();

            if (offset > 640 && offset < levelWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });

        appRoot.getChildren().addAll(bg, gameRoot);
    }

    private void update() {
        if (isPressed(KeyCode.W)) {
            moveY(-5);
        }

        if (isPressed(KeyCode.S)) {
            moveY(5);
        }

        if (isPressed(KeyCode.A) && player.getTranslateX() >= 0) {
            moveX(-5);
        }

        if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5) {
            moveX(5);
        }

    }

    private void moveX(int value) {
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : array) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (player.getTranslateX() + 40 == platform.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        if (player.getTranslateX() == platform.getTranslateX() + 60) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    private void moveY(int value) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : array) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (player.getTranslateY() + 40 == platform.getTranslateY()) {
                            return;
                        }
                    }
                    else {
                        if (player.getTranslateY() == platform.getTranslateY() + 60) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
        }
    }


    public void jumPlayer() {
        if(canJump) {
            playerVelo = playerVelo.add(0, -20);
            canJump = false;
        }
    }
    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key,false);
    }

    @Override
    public void start(Stage primaryStage){
        createMap();

        Scene scene = new Scene(appRoot, 1280, 720);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        /*scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        moveX(5);
                        break;
                    case A:
                        moveX(-10);
                        break;
                    case W:
                        top = true;
                        break;
                    case S:
                        bot = true;
                        break;
                }
            }
        });*/
        /*scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
                        bot = false;
                        break;
                }
            }
        });*/
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        AnimationTimer time = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        time.start();

    }
    /*private void moveRectangle(double dx, double dy) {
        double x = rectangle.getX() + dx;
        double y = rectangle.getY() + dy;
        if(x>=0 && y>=0 && x<=(width-rectangle.getWidth()) && y<=(height-rectangle.getHeight())) {
            rectangle.setX(x);
            rectangle.setY(y);
        }
    }*/

    /*public boolean collision(ArrayList<Rectangle> a, double dx, double dy) {
        int count = 0;
        for(int i=0; i<a.size(); i++) {
            double h = a.get(i).getHeight();
            double w = a.get(i).getWidth();
            double x = a.get(i).getX();
            double y = a.get(i).getY();

            if(dx<=(x - rectangle.getWidth()) || dx>=(x + w) || dy<=(y - rectangle.getHeight()) || dy>=(h + y)) {
                count++;
            }
        }
        if(count == a.size()) {
            return true;
        }
        return false;
    }*/



    public static void main(String[] args) {
        launch(args);
    }
}
