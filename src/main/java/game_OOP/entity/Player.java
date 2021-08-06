package game_OOP.entity;

import game_OOP.entity.bomb.Bomb;
import game_OOP.entity.tile.Brick;
import game_OOP.entity.tile.Wall;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Player extends AnimationSprite {
    private static final double player_width = 16;
    private static final double player_widthX = 40;
    private static final double player_height = 19;
    private static final double player_heightY = 48;

    private static final String urlPlayer = "src/main/resources/img/bomberman.png";

    public Player(double x, double y) {
        super(player_width, player_height, player_widthX, player_heightY);

        this.x = x;
        this.y = y;

        spriteX = 94;
        spriteY = 4;

        try {
            File file = new File(urlPlayer);
            drawImage = new Image(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        createCoordinates();
    }

    public void createCoordinates() {
        coordinatesX[directionRIGHT] = new double[] {94, 110, 94, 78};
        coordinatesY[directionRIGHT] = new double[] {4, 4, 4, 4};

        coordinatesX[directionLEFT] = new double[] {46, 30, 46, 62};
        coordinatesY[directionLEFT] = new double[] {4, 4, 4, 4};

        coordinatesX[directionBOTTOM] = new double[] {46, 30, 46, 62};
        coordinatesY[directionBOTTOM] = new double[] {23, 23, 23, 23};

        coordinatesX[directionTOP] = new double[] {94, 109, 94, 78};
        coordinatesY[directionTOP] = new double[] {23, 23, 23, 23};

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
        if(isNo) {
            animation(directionBOTTOM);
        }
        else {
            animation(directionTOP);
        }
        setY(getY() + value);
    }

    public void collisionWall(int direction, ArrayList<Wall> walls) {
        switch (direction) {
            //trai
            case 0:
                for(int i=0; i<walls.size(); i++) {
                    if (this.getX()> walls.get(i).getX() && this.getX() < walls.get(i).getX() + Sprite.size && this.getY() < walls.get(i).getY() + Sprite.size && this.getY() + 36 > walls.get(i).getY()) {
                        if(this.getY() + Sprite.size > walls.get(i).getY()) {
                            this.setX(walls.get(i).getX() + Sprite.size);
                        }
                    }
                }
                break;
                //phai
            case 1:
                for(int i=0; i<walls.size(); i++) {
                    if (this.getX() + 36 > walls.get(i).getX() && this.getX() < walls.get(i).getX() + Sprite.size && this.getY() < walls.get(i).getY() + Sprite.size && this.getY() + 36 > walls.get(i).getY()) {
                        if(this.getX() < walls.get(i).getX() && this.getY() + Sprite.size > walls.get(i).getY()) {
                            this.setX(walls.get(i).getX() - 36);
                        }
                    }
                }
                break;
                //xuong
            case 2:
                for(int i=0; i<walls.size(); i++) {
                    if (this.getX() + 28 > walls.get(i).getX() && this.getX() < walls.get(i).getX() + Sprite.size && this.getY() < walls.get(i).getY() + Sprite.size && this.getY() + 42 > walls.get(i).getY()) {
                        if(this.getY() < walls.get(i).getY()) {
                            this.setY(walls.get(i).getY() - 42);
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

    public boolean collisionBomb(Bomb bomb) {
        if(this.getX() < (bomb.getX() + Sprite.size*2) && this.getX() + 28 > (bomb.getX() - Sprite.size) && this.getY() + 36 > bomb.getY() && this.getY() < (bomb.getY() + Sprite.size)) {
            return true;
        }
        if(this.getX() < (bomb.getX() + Sprite.size) && this.getX() > (bomb.getX()) && this.getY() + 36 > (bomb.getY() - Sprite.size) && this.getY() < bomb.getY()) {
            return true;
        }
        if(this.getX() < (bomb.getX() + Sprite.size) && this.getX() > (bomb.getX()) && this.getY() + 36 > (bomb.getY() + Sprite.size) && this.getY() < (bomb.getY() + Sprite.size*2)) {
            return true;
        }
        return false;
    }

    public void collisionBrick(int direction, ArrayList<Brick> bricks) {
        switch (direction) {
            //phai
            case 1:
                for(int i=0; i<bricks.size(); i++) {
                    if (this.getX() + 36 > bricks.get(i).getX() && this.getX() < bricks.get(i).getX() + Sprite.size && this.getY() < bricks.get(i).getY() + Sprite.size && this.getY() + 36 > bricks.get(i).getY()) {
                        if(this.getX() < bricks.get(i).getX() && this.getY() + Sprite.size > bricks.get(i).getY()) {
                            this.setX(bricks.get(i).getX() - 36);
                        }
                    }
                }
                break;
            //trai
            case 0:
                for(int i=0; i<bricks.size(); i++) {
                    if (this.getX() + 36 > bricks.get(i).getX() && this.getX() < bricks.get(i).getX() + Sprite.size && this.getY() < bricks.get(i).getY() + Sprite.size && this.getY() + 36 > bricks.get(i).getY()) {
                        if(this.getX() > bricks.get(i).getX() && this.getY() + Sprite.size > bricks.get(i).getY()) {
                            this.setX(bricks.get(i).getX() + Sprite.size);
                        }
                    }
                }
                break;
            //xuong
            case 2:
                for(int i=0; i<bricks.size(); i++) {
                    if (this.getX() + 28 > bricks.get(i).getX() && this.getX() < bricks.get(i).getX() + Sprite.size && this.getY() < bricks.get(i).getY() + Sprite.size && this.getY() + 42 > bricks.get(i).getY()) {
                        if(this.getY() < bricks.get(i).getY()) {
                            this.setY(bricks.get(i).getY() - 42);
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
}
