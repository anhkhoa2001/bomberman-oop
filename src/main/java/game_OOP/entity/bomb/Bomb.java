package game_OOP.entity.bomb;

import game_OOP.entity.AnimationSprite;
import game_OOP.entity.Sprite;
import game_OOP.entity.tile.Brick;
import game_OOP.entity.tile.Wall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Bomb extends AnimationSprite {
    private final String urlBomb = "src/main/resources/img/bomb2.png";
    private final String urlExplode = "src/main/resources/img/explode.png";

    private boolean top, bot, left, right;

    private static final double widthBomb = 16;
    private static final double heightBomb = 16;
    private static final double widthX = Sprite.size;
    private static final double heightY = Sprite.size;
    private static final double widthExplode = 32;
    private static final double heightExplode = 32;

    private double spriteXLEFT;
    private double spriteYLEFT;
    private double spriteXTOP;
    private double spriteYTOP;
    private double spriteXRIGHT;
    private double spriteYRIGHT;
    private double spriteXBOTTOM;
    private double spriteYBOTTOM;

    private Image imageBomb;
    private Image imageExplode;

    private Image imageLEFT;
    private Image imageRIGHT;
    private Image imageTOP;
    private Image imageBOTTOM;

    public void setImageAll(Image imageLEFT, Image imageRIGHT, Image imageBOTTOM, Image imageTOP) {
        this.imageLEFT = imageLEFT;
        this.imageBOTTOM = imageBOTTOM;
        this.imageTOP = imageTOP;
        this.imageRIGHT = imageRIGHT;
    }
    public static final double speedBomb = 40;


    public Bomb(double x, double y) {
        super(widthBomb, heightBomb, widthX, heightY);

        this.x = x;
        this.y = y;

        try {
            File fileBomb = new File(urlBomb);
            imageBomb = new Image(fileBomb.toURI().toString());
            setDrawImage(imageBomb);

            File fileExplode = new File(urlExplode);
            imageExplode = new Image(fileExplode.toURI().toString());
            imageLEFT = new Image(fileExplode.toURI().toString());
            imageRIGHT = new Image(fileExplode.toURI().toString());
            imageBOTTOM = new Image(fileExplode.toURI().toString());
            imageTOP = new Image(fileExplode.toURI().toString());
            setImageAll(imageBomb, imageBomb, imageBomb, imageBomb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createCoordinates();
        updateDirection(0);
    }

    public void changeSpriteBomb(ArrayList<Wall> walls) {
        changeSprite();
        updateSprite();
        collisionWall(walls, countSprite);
    }

    public void changeSprite() {
        countChange ++;
        if(countChange <= 0) {
            countSprite = 0;
        }
        else if(countChange <= speedBomb) {
            countSprite = 0;
            this.setWidth(widthBomb);
        }
        else if(countChange <= speedBomb*2) {
            countSprite = 1;
            this.setWidth(widthBomb + 0.5);
        }
        else if(countChange <= speedBomb*3) {
            countSprite = 2;
            this.setWidth(14.7);
        }
        else if(countChange <= speedBomb*4) {
            countSprite = 0;
            this.setWidth(widthBomb);
        }
        else if(countChange <= speedBomb*5) {
            countSprite = 1;
            this.setWidth(widthBomb + 0.5);
        }
        else if(countChange <= speedBomb*6) {
            countSprite = 2;
            this.setWidth(14.7);
        }
        else if(countChange <= speedBomb*6.1*100) {
            setDrawImage(imageExplode);
            setImageAll(imageExplode, imageExplode, imageExplode, imageExplode);
            countSprite = 3;
            this.setWidth(32);
            this.setHeight(32);
        }
        else if(countChange <= speedBomb*6.2*100) {
            countSprite = 4;
        }
        else if(countChange <= speedBomb*6.3*100) {
            countSprite = 5;
        }
        else if(countChange <= speedBomb*6.4*100) {
            countSprite = 6;
        }
    }

    public void createCoordinates() {
        //center
        coordinatesX[4] = new double[] {0, 17, 32, 0, 0,  0,  0,  0};
        coordinatesY[4] = new double[] {0, 0,  0,  0, 32, 64, 96, 16};
        //trai
        coordinatesX[0] = new double[] {0,  0,  0,  192, 192, 192, 192, 0};
        coordinatesY[0] = new double[] {16, 16, 16, 0,   32,  64,  96,  16};
        //phai
        coordinatesX[1] = new double[] {0,  0,  0,  160, 160, 160, 160, 0};
        coordinatesY[1] = new double[] {16, 16, 16, 0,   32,  64,  96,  16};
        //duoi
        coordinatesX[2] = new double[] {0,  0,  0,  128, 128, 128, 128, 0};
        coordinatesY[2] = new double[] {16, 16, 16, 0,   32,  64,  96,  16};
        //tren
        coordinatesX[3] = new double[] {0,  0,  0,  96, 96, 96, 96, 0};
        coordinatesY[3] = new double[] {16, 16, 16, 0,  32, 64, 96, 16};
    }

    public void updateSprite() {
        spriteX = coordinatesX[4][countSprite];
        spriteY = coordinatesY[4][countSprite];
    }

    public void updateDirection(int cs) {
        updateTOP(cs);
        updateRIGHT(cs);
        updateBOTTOM(cs);
        updateLEFT(cs);
    }

    public void updateTOP(int cs) {
        spriteXTOP = coordinatesX[3][cs];
        spriteYTOP = coordinatesY[3][cs];
    }

    public void updateBOTTOM(int cs) {
        spriteXBOTTOM = coordinatesX[2][cs];
        spriteYBOTTOM = coordinatesY[2][cs];
    }

    public void updateRIGHT(int cs) {
        spriteXRIGHT = coordinatesX[1][cs];
        spriteYRIGHT = coordinatesY[1][cs];
    }

    public void updateLEFT(int cs) {
        spriteXLEFT = coordinatesX[0][cs];
        spriteYLEFT = coordinatesY[0][cs];
    }
    public void collisionBrick(ArrayList<Brick> bricks) {
        double dxLEFT = getX();
        double dxRIGHT = getX() + 48;
        double dyTOP = getY();
        double dyBOT = getY() + 48;

        int directionT, directionB, directionR, directionL;

        for(int i=0; i<bricks.size(); i++) {
            if(dxLEFT - 48 == bricks.get(i).getX() && dyTOP == bricks.get(i).getY()) {
                bricks.remove(i);
            }
            if(dxRIGHT == bricks.get(i).getX() && dyTOP == bricks.get(i).getY()) {
                bricks.remove(i);
            }
            if(dyTOP - 48 == bricks.get(i).getY() && dxLEFT == bricks.get(i).getX()) {
                bricks.remove(i);
            }
            if(dyBOT == bricks.get(i).getY() && dxLEFT == bricks.get(i).getX()) {
                bricks.remove(i);
            }
        }
    }

    public void collisionWall(ArrayList<Wall> walls, int cs) {
        double dxLEFT = getX();
        double dxRIGHT = getX() + 48;
        double dyTOP = getY();
        double dyBOT = getY() + 48;

        for(int i=0; i<walls.size(); i++) {
            if(dxLEFT - 48 == walls.get(i).getX() && dyTOP == walls.get(i).getY()) {
                left = true;
            }
            else if(dxRIGHT == walls.get(i).getX() && dyTOP == walls.get(i).getY()) {
                right = true;
            }
            else if(dyTOP - 48 == walls.get(i).getY() && dxLEFT == walls.get(i).getX()) {
                top = true;
            }
            else if(dyBOT == walls.get(i).getY() && dxLEFT == walls.get(i).getX()) {
                bot = true;
            }
        }
        if(top) {
            imageTOP = imageBomb;
            updateTOP(0);
            updateRIGHT(cs);
            updateBOTTOM(cs);
            updateLEFT(cs);
        }
        if(bot) {
            imageBOTTOM = imageBomb;
            updateTOP(cs);
            updateRIGHT(cs);
            updateBOTTOM(0);
            updateLEFT(cs);
        }
        if(left) {
            imageLEFT = imageBomb;
            updateTOP(cs);
            updateRIGHT(cs);
            updateBOTTOM(cs);
            updateLEFT(0);
        }
        if(right) {
            imageRIGHT = imageBomb;
            updateTOP(cs);
            updateRIGHT(0);
            updateBOTTOM(cs);
            updateLEFT(cs);
        }
        if(bot && top) {
            imageBOTTOM = imageBomb;
            imageTOP = imageBomb;
            updateTOP(0);
            updateRIGHT(cs);
            updateBOTTOM(0);
            updateLEFT(cs);
        }
        if(bot && left) {
            imageBOTTOM = imageBomb;
            imageLEFT = imageBomb;
            updateTOP(cs);
            updateRIGHT(cs);
            updateBOTTOM(0);
            updateLEFT(0);
        }
        if(bot && right) {
            imageRIGHT = imageBomb;
            imageBOTTOM = imageBomb;
            updateTOP(cs);
            updateRIGHT(0);
            updateBOTTOM(0);
            updateLEFT(cs);
        }
        if(top && left) {
            imageLEFT = imageBomb;
            imageTOP = imageBomb;
            updateTOP(0);
            updateRIGHT(cs);
            updateBOTTOM(cs);
            updateLEFT(0);
        }
        if(right && top) {
            imageRIGHT = imageBomb;
            imageTOP = imageBomb;
            updateTOP(0);
            updateRIGHT(0);
            updateBOTTOM(cs);
            updateLEFT(cs);
        }
        if(left && right) {
            imageLEFT = imageBomb;
            imageRIGHT = imageBomb;
            updateTOP(cs);
            updateRIGHT(0);
            updateBOTTOM(cs);
            updateLEFT(0);
        }
        if(left && right && top) {
            imageLEFT = imageBomb;
            imageRIGHT = imageBomb;
            imageTOP = imageBomb;
            updateTOP(0);
            updateRIGHT(0);
            updateBOTTOM(cs);
            updateLEFT(0);
        }
        if(left && right && bot) {
            imageLEFT = imageBomb;
            imageRIGHT = imageBomb;
            imageBOTTOM = imageBomb;
            updateTOP(cs);
            updateRIGHT(0);
            updateBOTTOM(0);
            updateLEFT(0);
        }
        if(left && bot && top) {
            imageLEFT = imageBomb;
            imageBOTTOM = imageBomb;
            imageTOP = imageBomb;
            updateTOP(0);
            updateRIGHT(cs);
            updateBOTTOM(0);
            updateLEFT(0);
        }
        if(right && bot && top) {
            imageRIGHT = imageBomb;
            imageBOTTOM = imageBomb;
            imageTOP = imageBomb;
            updateTOP(0);
            updateRIGHT(0);
            updateBOTTOM(0);
            updateLEFT(cs);
        }
        if(!top && !bot && !right && !left) {
            updateTOP(cs);
            updateRIGHT(cs);
            updateBOTTOM(cs);
            updateLEFT(cs);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(drawImage, spriteX, spriteY, width, height, x, y, widthX, heightY);
        gc.drawImage(imageLEFT, spriteXLEFT, spriteYLEFT, widthExplode, heightExplode, x - 48, y, widthX, heightY);
        gc.drawImage(imageTOP, spriteXTOP, spriteYTOP, widthExplode, heightExplode, x, y - 48, widthX, heightY);
        gc.drawImage(imageBOTTOM, spriteXBOTTOM, spriteYBOTTOM, widthExplode, heightExplode, x, y + 48, widthX, heightY);
        gc.drawImage(imageRIGHT, spriteXRIGHT, spriteYRIGHT, widthExplode, heightExplode, x + 48, y, widthX, heightY);
    }
}
