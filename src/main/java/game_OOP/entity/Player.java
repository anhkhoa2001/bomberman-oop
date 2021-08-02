package game_OOP.entity;

import javafx.scene.image.Image;

import java.io.File;

public class Player extends AnimationSprite {
    private static final double player_width = 64;
    private static final double player_widthX = 64;
    private static final double player_height = 96;
    private static final double player_heightY = 96;

    private final String urlPlayer = "src/main/resources/img/sprite1.png";

    public Player() {
        super(player_width, player_height, player_widthX, player_heightY);
        try {
            File file = new File(urlPlayer);
            drawImage = new Image(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        createCoordinates();
    }

    public void createCoordinates() {
        coordinatesX[directionRIGHT] = new double[] {0, 64, 128, 192};
        coordinatesY[directionRIGHT] = new double[] {224, 224, 224, 224};

        coordinatesX[directionLEFT] = new double[] {0, 64, 128, 192};
        coordinatesY[directionLEFT] = new double[] {128, 128, 128, 128};

        coordinatesX[directionBOTTOM] = new double[] {0, 64, 128, 192};
        coordinatesY[directionBOTTOM] = new double[] {32, 32, 32, 32};

        coordinatesX[directionTOP] = new double[] {0, 64, 128, 192};
        coordinatesY[directionTOP] = new double[] {320, 320, 320, 320};

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

    public void stop() {
        spriteX = 0;
        spriteY = 32;
    }
}
