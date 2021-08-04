package game_OOP;

import game_OOP.entity.*;
import game_OOP.entity.tile.Brick;
import game_OOP.entity.tile.Grass;
import game_OOP.entity.tile.Wall;
import game_OOP.map.MapOne;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameScene extends GeneralScene {

    private Player bomber;

    protected ArrayList<Wall> wallArrayList = new ArrayList<>();
    protected ArrayList<Grass> grassArrayList = new ArrayList<>();
    protected ArrayList<Brick> brickArrayList = new ArrayList<>();

    protected ArrayList<Wall> wallArrayList1 = new ArrayList<>();

    protected ArrayList<Double> ddx = new ArrayList<>();
    protected ArrayList<Double> ddy = new ArrayList<>();

    protected int countChange = 0;

    public GameScene() {
        super();
        bomber = new Player(48, 88);
    }

    public void createMap() {
        for(int i=0; i< MapOne.map1.length; i++) {
            for(int j=0; j<MapOne.map1[i].length(); j++) {
                Grass grass = new Grass(j*Sprite.size, i*Sprite.size + 40);
                grassArrayList.add(grass);
                    switch (MapOne.map1[i].charAt(j)) {
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
    }

    public void addElement(ArrayList<Double> array, Double var) {
        int count = 0;
        for(int i=0; i<array.size(); i++) {
            if(var.equals(array.get(i))) {
                count++;
            }
        }
        if(count == 0) {
            array.add(var);
        }
    }

    @Override
    public void draw() {
        pressedKey.clear();
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
                    case K:
                        Wall w = new Wall(bomber.getX(), bomber.getY());
                        w.draw(gc);
                        wallArrayList.add(w);
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
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 40, canvas.getWidth(), canvas.getHeight());

                for (Grass grass : grassArrayList) {
                    grass.draw(gc);
                }
                bomber.draw(gc);

                for (Wall wall : wallArrayList) {
                    wall.draw(gc);
                }

                for(Brick brick : brickArrayList) {
                    brick.draw(gc);
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
                else {
                    bomber.stop();
                }
                if(bomber.getX() > WIDTH/2 && bomber.getX() < ((WIDTH + 480) - WIDTH/2)) {
                    root.setLayoutX(-(bomber.getX()-WIDTH/2));
                    pane.setTranslateX(bomber.getX()-WIDTH/2);
                }

            }
        }.start();
    }

    public Wall create(double x, double y) {
        Wall wall = new Wall(x, y);
        return wall;
    }
}
