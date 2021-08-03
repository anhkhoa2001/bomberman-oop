package game_OOP;

import game_OOP.entity.*;
import game_OOP.entity.bomb.Bomb;
import game_OOP.entity.tile.Brick;
import game_OOP.entity.tile.Grass;
import game_OOP.entity.tile.Wall;
import game_OOP.map.MapOne;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameScene extends GeneralScene {

    private Player bomber;

    protected ArrayList<Wall> wallArrayList = new ArrayList<>();
    protected ArrayList<Grass> grassArrayList = new ArrayList<>();
    protected ArrayList<Brick> brickArrayList = new ArrayList<>();
    protected ArrayList<Bomb> bombArrayList = new ArrayList<>();

    public GameScene() {
        super();
        bomber = new Player(48, 96);
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
    @Override
    public void draw() {
        Bomb bomb = new Bomb(4*Sprite.size, 4*Sprite.size + 40);
        pressedKey.clear();
        createMap();
        new AnimationTimer() {
            public void handle(long l) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 40, canvas.getWidth(), canvas.getHeight());

                for (Grass grass : grassArrayList) {
                    grass.draw(gc);
                }
                bomber.draw(gc);
                bomb.draw(gc);
                bomb.changeSpriteBomb();

                for (Wall wall : wallArrayList) {
                    wall.draw(gc);
                }

                for(Brick brick : brickArrayList) {
                    brick.draw(gc);
                }

                if(pressedKey.contains(KeyCode.D)) {
                    bomber.moveX(Sprite.speed/2);
                    bomber.collisionBrick(Sprite.directionRIGHT, brickArrayList);
                    bomber.collisionWall(Sprite.directionRIGHT, wallArrayList);
                }
                else if(pressedKey.contains(KeyCode.A)) {
                    bomber.moveX(-Sprite.speed/2);
                    bomber.collisionBrick(Sprite.directionLEFT, brickArrayList);
                    bomber.collisionWall(Sprite.directionLEFT, wallArrayList);
                }
                else if(pressedKey.contains(KeyCode.W)) {
                    bomber.moveY(-Sprite.speed/2);
                    bomber.collisionBrick(Sprite.directionTOP, brickArrayList);
                    bomber.collisionWall(Sprite.directionTOP, wallArrayList);
                }
                else if(pressedKey.contains(KeyCode.S)) {
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
}
