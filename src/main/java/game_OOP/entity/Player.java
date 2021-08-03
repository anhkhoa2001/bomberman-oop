package game_OOP.entity;

import game_OOP.entity.tile.Brick;
import game_OOP.entity.tile.Wall;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Player extends AnimationSprite {
    private static final double player_width = 48;
    private static final double player_widthX = 40;
    private static final double player_height = 80;
    private static final double player_heightY = 64;

    private final String urlPlayer = "src/main/resources/img/bomber.png";

    public Player(double x, double y) {
        super(player_width, player_height, player_widthX, player_heightY);

        this.x = x;
        this.y = y;

        try {
            File file = new File(urlPlayer);
            drawImage = new Image(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        createCoordinates();
    }

    public void createCoordinates() {
        coordinatesX[directionRIGHT] = new double[] {16, 80, 144, 208};
        coordinatesY[directionRIGHT] = new double[] {240, 240, 240, 240};

        coordinatesX[directionLEFT] = new double[] {16, 80, 144, 208};
        coordinatesY[directionLEFT] = new double[] {144, 144, 144, 144};

        coordinatesX[directionBOTTOM] = new double[] {16, 80, 144, 208};
        coordinatesY[directionBOTTOM] = new double[] {54, 54, 54, 54};

        coordinatesX[directionTOP] = new double[] {16, 80, 144, 208};
        coordinatesY[directionTOP] = new double[] {342, 342, 342, 342};

    }

    public void moveX(double value) {
        boolean isNo = value > 0;
        if(isNo) {
            animation(directionRIGHT);
        }
        else {
            animation(directionLEFT);
        }
        setX(getX() + value);
    }

    public void moveY(double value) {
        boolean isNo = value > 0;
        for(int i=0; i<Math.abs(value); i++) {
            if(isNo) {
                animation(directionBOTTOM);
            }
            else {
                animation(directionTOP);
            }
            setY(getY() + (isNo ? 1 : -1));
        }
    }

    public void collisionWall(int direction, ArrayList<Wall> walls) {
        switch (direction) {
            //trai
            case 0:
                for(int i=0; i<walls.size(); i++) {
                    if (this.getX() + 28 > walls.get(i).getX() && this.getX() < walls.get(i).getX() + Sprite.size && this.getY() < walls.get(i).getY() + Sprite.size && this.getY() + 36 > walls.get(i).getY()) {
                        if(this.getX() > walls.get(i).getX() && this.getY() + Sprite.size > walls.get(i).getY()) {
                            this.setX(walls.get(i).getX() + Sprite.size);
                        }
                    }
                }
                break;
                //phai
            case 1:
                for(int i=0; i<walls.size(); i++) {
                    if (this.getX() + 28 > walls.get(i).getX() && this.getX() < walls.get(i).getX() + Sprite.size && this.getY() < walls.get(i).getY() + Sprite.size && this.getY() + 36 > walls.get(i).getY()) {
                        if(this.getX() < walls.get(i).getX() && this.getY() + Sprite.size > walls.get(i).getY()) {
                            this.setX(walls.get(i).getX() - 28);
                        }
                    }
                }
                break;
                //xuong
            case 2:
                for(int i=0; i<walls.size(); i++) {
                    if (this.getX() + 28 > walls.get(i).getX() && this.getX() < walls.get(i).getX() + Sprite.size && this.getY() < walls.get(i).getY() + Sprite.size && this.getY() + 36 > walls.get(i).getY()) {
                        if(this.getY() < walls.get(i).getY()) {
                            this.setY(walls.get(i).getY() - 36);
                        }
                    }
                }
                break;
                //len
            case 3:
                for(int i=0; i<walls.size(); i++) {
                    if (this.getX() + 28 > walls.get(i).getX() && this.getX() < walls.get(i).getX() + 48 && this.getY() < walls.get(i).getY() + Sprite.size && this.getY() + 36 > walls.get(i).getY()) {
                        if(this.getY() > walls.get(i).getY()) {
                            this.setY(walls.get(i).getY() + Sprite.size);
                        }
                    }
                }
                break;
        }
    }

    public void collisionBrick(int direction, ArrayList<Brick> bricks) {
        switch (direction) {
            //phai
            case 1:
                for(int i=0; i<bricks.size(); i++) {
                    if (this.getX() + 28 > bricks.get(i).getX() && this.getX() < bricks.get(i).getX() + Sprite.size && this.getY() < bricks.get(i).getY() + Sprite.size && this.getY() + 36 > bricks.get(i).getY()) {
                        if(this.getX() < bricks.get(i).getX() && this.getY() + Sprite.size > bricks.get(i).getY()) {
                            this.setX(bricks.get(i).getX() - 28);
                        }
                    }
                }
                break;
            //trai
            case 0:
                for(int i=0; i<bricks.size(); i++) {
                    if (this.getX() + 28 > bricks.get(i).getX() && this.getX() < bricks.get(i).getX() + Sprite.size && this.getY() < bricks.get(i).getY() + Sprite.size && this.getY() + 36 > bricks.get(i).getY()) {
                        if(this.getX() > bricks.get(i).getX() && this.getY() + Sprite.size > bricks.get(i).getY()) {
                            this.setX(bricks.get(i).getX() + Sprite.size);
                        }
                    }
                }
                break;
            //xuong
            case 2:
                for(int i=0; i<bricks.size(); i++) {
                    if (this.getX() + 28 > bricks.get(i).getX() && this.getX() < bricks.get(i).getX() + Sprite.size && this.getY() < bricks.get(i).getY() + Sprite.size && this.getY() + 36 > bricks.get(i).getY()) {
                        if(this.getY() < bricks.get(i).getY()) {
                            this.setY(bricks.get(i).getY() - 36);
                        }
                    }
                }
                break;
            //len
            case 3:
                for(int i=0; i<bricks.size(); i++) {
                    if (this.getX() + 28 > bricks.get(i).getX() && this.getX() < bricks.get(i).getX() + Sprite.size && this.getY() < bricks.get(i).getY() + Sprite.size && this.getY() + 36 > bricks.get(i).getY()) {
                        if(this.getY() > bricks.get(i).getY()) {
                            this.setY(bricks.get(i).getY() + Sprite.size);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    public void stop() {
        spriteX = 16;
        spriteY = 54;
    }
}
