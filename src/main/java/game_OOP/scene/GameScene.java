package game_OOP.scene;

import game_OOP.Game;
import game_OOP.entity.*;
import game_OOP.entity.bomb.Bomb;
import game_OOP.entity.tile.Brick;
import game_OOP.entity.tile.Grass;
import game_OOP.entity.tile.Wall;
import game_OOP.map.MapOne;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameScene extends GeneralScene {

    private Player bomber;

    public AnchorPane root = new AnchorPane();
    protected Canvas canvas;
    public GraphicsContext gc;

    public Pane pane = new Pane();
    public Label labelTime = new Label();
    public Label labelPoint = new Label();

    private final int countTime = 300;
    private int countPoint = 0;
    public Timer timer = new Timer();

    public int countBomb = 0;
    public int countAlive = 0;
    public boolean top, left, right, bot, exit, alive;

    private final double timeExplode = 256;

    protected ArrayList<Wall> wallArrayList = new ArrayList<>();
    protected ArrayList<Grass> grassArrayList = new ArrayList<>();
    protected ArrayList<Brick> brickArrayList = new ArrayList<>();
    protected ArrayList<Bomb> bombArrayList = new ArrayList<>();
    protected ArrayList<Player> playerArrayList = new ArrayList<>();

    public void setTime() {
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = countTime;
            @Override
            public void run() {
                Platform.runLater(() -> labelTime.setText("Time: " + count));
                count--;

                if(count < 1) {
                    timer.cancel();
                }
            }
        }, 2000, 1000);
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

    public GameScene() {
        super();
        canvas = new Canvas(Sprite.size * MapOne.map[0].length(), HEIGHT);
        gc = canvas.getGraphicsContext2D();

        createAttr();

        this.setRoot(root);

        root.getChildren().addAll(pane, canvas);

    }

    public void createMap() {
        for(int i=0; i< MapOne.map.length; i++) {
            for(int j=0; j<MapOne.map[i].length(); j++) {
                Grass grass = new Grass(j*Sprite.size, i*Sprite.size + 40);
                grassArrayList.add(grass);
                    switch (MapOne.map[i].charAt(j)) {
                    case '#':
                        Wall wall = new Wall(j*Sprite.size, i*Sprite.size + 40);
                        wallArrayList.add(wall);
                        break;
                    case 'B':
                         Brick brick = new Brick(j*Sprite.size, i*Sprite.size + 40);
                         brickArrayList.add(brick);
                         break;
                }
            }
        }
        bomber = new Player(48, 88);
        playerArrayList.add(bomber);
    }
    @Override
    public void draw() {
        createMap();

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A:
                        left = true;
                        break;
                    case D:
                        right = true;
                        break;
                    case S:
                        bot = true;
                        break;
                    case W:
                        top = true;
                        break;
                    case F:
                        if(countBomb >= 256) {
                            Bomb bomb = createBomb(bomber.getX(), bomber.getY());
                            bombArrayList.add(bomb);
                            countBomb = 0;
                        }
                        break;
                    case ESCAPE:
                        exit = true;
                        break;
                }
            }
        });
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A:
                        left = false;
                        break;
                    case D:
                        right = false;
                        break;
                    case S:
                        bot = false;
                        break;
                    case W:
                        top = false;
                        break;
                }
            }
        });

        new AnimationTimer() {
            public void handle(long l) {
                countBomb++;
                countAlive++;
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 40, canvas.getWidth(), canvas.getHeight());

                for (Grass grass : grassArrayList) {
                    grass.draw(gc);
                }

                for (Player player : playerArrayList) {
                    player.draw(gc);
                }

                for (Wall wall : wallArrayList) {
                    wall.draw(gc);
                }

                for(Brick brick : brickArrayList) {
                    brick.draw(gc);
                }

                for(Bomb bomb : bombArrayList) {
                    bomb.draw(gc);
                    bomb.changeSpriteBomb(wallArrayList);
                    if(countBomb == timeExplode - 1) {
                        bomb.collisionBrick(brickArrayList);
                    }
                }
                if(countBomb >= timeExplode) {
                    bombArrayList.clear();
                }

                if(right) {
                    bomber.moveX(Sprite.speed/2);
                    bomber.collisionBrick(Sprite.directionRIGHT, brickArrayList);
                    bomber.collisionWall(Sprite.directionRIGHT, wallArrayList);
                }
                else if(left) {
                    bomber.moveX(-Sprite.speed/2);
                    bomber.collisionBrick(Sprite.directionLEFT, brickArrayList);
                    bomber.collisionWall(Sprite.directionLEFT, wallArrayList);
                }
                else if(top) {
                    bomber.moveY(-Sprite.speed/2);
                    bomber.collisionBrick(Sprite.directionTOP, brickArrayList);
                    bomber.collisionWall(Sprite.directionTOP, wallArrayList);
                }
                else if(bot) {
                    bomber.moveY(Sprite.speed/2);
                    bomber.collisionBrick(Sprite.directionBOTTOM, brickArrayList);
                    bomber.collisionWall(Sprite.directionBOTTOM, wallArrayList);
                }
                else if(exit) {
                    this.stop();
                    Game.changeScene(Game.endScene);
                }

                if(bombArrayList.size() > 0) {
                    if(countBomb >= 61*4 && countBomb <= timeExplode) {
                        if(bomber.collisionBomb(bombArrayList.get(0))) {
                            alive = true;
                            countAlive = 0;
                        }
                    }
                }

                if(alive) {
                    bomber.animationDie();
                    if(countAlive == 70) {
                        playerArrayList.clear();
                    }
                    if(countAlive == 80) {
                        this.stop();
                    }
                }

                if(bomber.getX() > WIDTH/2 && bomber.getX() < ((WIDTH + 480) - WIDTH/2)) {
                    root.setLayoutX(-(bomber.getX()-WIDTH/2));
                    pane.setTranslateX(bomber.getX()-WIDTH/2);
                }
            }
        }.start();
    }

    public Bomb createBomb(double x, double y) {
        double dx = x - x%48;
        double dy = y - (y-40)%48;
        return new Bomb(dx, dy);
    }
}
