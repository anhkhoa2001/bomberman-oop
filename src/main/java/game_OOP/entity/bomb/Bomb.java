package game_OOP.entity.bomb;

import game_OOP.entity.AnimationSprite;
import game_OOP.entity.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

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

    private final int countCenter = 0;

    private int changeDirection = 0;

    private Image imageBomb;
    private Image imageExplode;

    private Image imageLEFT;
    private Image imageRIGHT;
    private Image imageTOP;
    private Image imageBOTTOM;

    public void setImageAll(Image image) {
        this.imageLEFT = image;
        this.imageBOTTOM = image;
        this.imageTOP = image;
        this.imageRIGHT = image;
    }
    private final double speedBomb = 2;


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
        } catch (Exception e) {
            e.printStackTrace();
        }
        createCoordinates();
    }

    public void changeSpriteBomb() {
        changeSprite();
        updateSprite();
        updateLEFT();
        updateBOTTOM();
        updateRIGHT();
        updateTOP();
    }

    public void changeSprite() {
        countChange ++;
        if(countChange <= speedBomb*10) {
            setImageAll(imageBomb);
            countSprite = 0;
            this.setWidth(widthBomb);
        }
        else if(countChange == speedBomb*10*2) {
            countSprite = 1;
            this.setWidth(widthBomb + 0.5);
        }
        else if(countChange == speedBomb*10*3) {
            countSprite = 2;
            this.setWidth(14.7);
        }
        else if(countChange == speedBomb*10*4) {
            countSprite = 0;
            this.setWidth(widthBomb);
        }
        else if(countChange == speedBomb*10*5) {
            countSprite = 1;
            this.setWidth(widthBomb + 0.5);
        }
        else if(countChange == speedBomb*10*6) {
            countSprite = 2;
            this.setWidth(14.7);
        }
        else if(countChange == speedBomb*10*7) {
            setDrawImage(imageExplode);
            setImageAll(imageExplode);
            countSprite = 3;
            this.setWidth(32);
            this.setHeight(32);
        }
        else if(countChange == speedBomb*10*8) {
            countSprite = 4;
        }
        else if(countChange == speedBomb*10*9) {
            countSprite = 5;
        }
        else if(countChange == speedBomb*10*10) {
            countSprite = 6;
        }
        else if(countChange == speedBomb*10*11) {
            countSprite = 7;
        }
        else if(countChange > speedBomb*10*12) {
            setImageAll(imageBomb);
            countSprite = 0;
        }
    }

    public void createCoordinates() {
        //center
        coordinatesX[4] = new double[] {0, 17, 32, 0, 0,  0,  0};
        coordinatesY[4] = new double[] {0, 0,  0,  0, 32, 64, 96};
        //trai
        coordinatesX[0] = new double[] {0,  0,  0,  192, 192, 192, 192};
        coordinatesY[0] = new double[] {16, 16, 16, 0,   32,  64,  96};
        //phai
        coordinatesX[1] = new double[] {0,  0,  0,  160, 160, 160, 160};
        coordinatesY[1] = new double[] {16, 16, 16, 0,   32,  64,  96};
        //duoi
        coordinatesX[2] = new double[] {0,  0,  0,  128, 128, 128, 128};
        coordinatesY[2] = new double[] {16, 16, 16, 0,   32,  64,  96};
        //tren
        coordinatesX[3] = new double[] {0,  0,  0,  96, 96, 96, 96};
        coordinatesY[3] = new double[] {16, 16, 16, 0,  32, 64, 96};
    }

    public void updateSprite() {
        spriteX = coordinatesX[4][countSprite];
        spriteY = coordinatesY[4][countSprite];
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


    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(drawImage, spriteX, spriteY, width, height, x, y, widthX, heightY);
        gc.drawImage(imageLEFT, spriteXLEFT, spriteYLEFT, widthExplode, heightExplode, x - 48, y, widthX, heightY);
        gc.drawImage(imageTOP, spriteXTOP, spriteYTOP, widthExplode, heightExplode, x, y - 48, widthX, heightY);
        gc.drawImage(imageBOTTOM, spriteXBOTTOM, spriteYBOTTOM, widthExplode, heightExplode, x, y + 48, widthX, heightY);
        gc.drawImage(imageRIGHT, spriteXRIGHT, spriteYRIGHT, widthExplode, heightExplode, x + 48, y, widthX, heightY);
    }
}
