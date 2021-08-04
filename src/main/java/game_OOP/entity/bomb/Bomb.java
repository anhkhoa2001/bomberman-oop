package game_OOP.entity.bomb;

import game_OOP.entity.AnimationSprite;
import game_OOP.entity.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Bomb extends AnimationSprite {
    private final String urlBomb = "src/main/resources/img/bomb2.png";
    private final String urlExplode = "src/main/resources/img/explode.png";

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

    private ArrayList<Double> dx = new ArrayList<>();
    private ArrayList<Double> dy = new ArrayList<>();

    public void setImageAll(Image image) {
        this.imageLEFT = image;
        this.imageBOTTOM = image;
        this.imageTOP = image;
        this.imageRIGHT = image;
    }
    private static final double speedBomb = 4;


    public Bomb() {
        super(widthBomb, heightBomb, widthX, heightY);

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
            setImageAll(imageBomb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createCoordinates();
        updateDirection();
    }

    public void changeSpriteBomb() {
        changeSprite();
        updateSprite();
        updateLEFT();
        updateBOTTOM();
        updateRIGHT();
        updateTOP();
    }

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void changeSprite() {
        countChange ++;
        if(countChange <= 0) {
            setImageAll(imageBomb);
            countSprite = 0;
        }
        else if(countChange <= speedBomb*10) {
            setImageAll(imageBomb);
            countSprite = 0;
            this.setWidth(widthBomb);
        }
        else if(countChange <= speedBomb*10*2) {
            setImageAll(imageBomb);
            countSprite = 1;
            this.setWidth(widthBomb + 0.5);
        }
        else if(countChange <= speedBomb*10*3) {
            setImageAll(imageBomb);
            countSprite = 2;
            this.setWidth(14.7);
        }
        else if(countChange <= speedBomb*10*4) {
            setImageAll(imageBomb);
            countSprite = 0;
            this.setWidth(widthBomb);
        }
        else if(countChange <= speedBomb*10*5) {
            setImageAll(imageBomb);
            countSprite = 1;
            this.setWidth(widthBomb + 0.5);
        }
        else if(countChange <= speedBomb*10*6) {
            setImageAll(imageBomb);
            countSprite = 2;
            this.setWidth(14.7);
        }
        else if(countChange <= speedBomb*10*6.1) {
            setDrawImage(imageExplode);
            setImageAll(imageExplode);
            countSprite = 3;
            this.setWidth(32);
            this.setHeight(32);
        }
        else if(countChange <= speedBomb*10*6.15) {
            countSprite = 4;
        }
        else if(countChange <= speedBomb*10*6.25) {
            countSprite = 5;
        }
        else if(countChange <= speedBomb*10*6.4) {
            countSprite = 6;
        }
        else {
            setImageAll(imageBomb);
            setDrawImage(imageBomb);
            countSprite = 7;
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

    public void updateDirection() {
        countSprite = 0;
        updateTOP();
        updateRIGHT();
        updateBOTTOM();
        updateLEFT();
    }

    public void updateTOP() {
        spriteXTOP = coordinatesX[3][countSprite];
        spriteYTOP = coordinatesY[3][countSprite];
    }

    public void updateBOTTOM() {
        spriteXBOTTOM = coordinatesX[2][countSprite];
        spriteYBOTTOM = coordinatesY[2][countSprite];
    }

    public void updateRIGHT() {
        spriteXRIGHT = coordinatesX[1][countSprite];
        spriteYRIGHT = coordinatesY[1][countSprite];
    }

    public void updateLEFT() {
        spriteXLEFT = coordinatesX[0][countSprite];
        spriteYLEFT = coordinatesY[0][countSprite];
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(drawImage, spriteX, spriteY, width, height, x, y, widthX, heightY);
        gc.drawImage(imageLEFT, spriteXLEFT, spriteYLEFT, widthExplode, heightExplode, x - 48, y, widthX, heightY);
        gc.drawImage(imageTOP, spriteXTOP, spriteYTOP, widthExplode, heightExplode, x, y - 48, widthX, heightY);
        gc.drawImage(imageBOTTOM, spriteXBOTTOM, spriteYBOTTOM, widthExplode, heightExplode, x, y + 48, widthX, heightY);
        gc.drawImage(imageRIGHT, spriteXRIGHT, spriteYRIGHT, widthExplode, heightExplode, x + 48, y, widthX, heightY);
    }
}
