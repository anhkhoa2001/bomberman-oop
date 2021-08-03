package game_OOP.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double widthX;
    protected double heightY;
    protected double spriteX;
    protected double spriteY;

    protected Image drawImage;

    public static int directionLEFT = 0;
    public static int directionRIGHT = 1;
    public static int directionBOTTOM = 2;
    public static int directionTOP = 3;

    public static final double size = 48;

    public static double speed;

    public Sprite(double width, double height, double widthX, double heightY) {
        this.width = width;
        this.height = height;
        this.widthX = widthX;
        this.heightY = heightY;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(drawImage, spriteX, spriteY, width, height, x, y, widthX, heightY);
    }

    public void clear(GraphicsContext gc) {
        gc.clearRect(x, y, width, height);
    }
}
