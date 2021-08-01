package game;

import javafx.scene.image.ImageView;

public class Entity{
    private double x;
    private double y;
    private ImageView img;

    public Entity(double x, double y, ImageView img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
}
