package game_OOP.entity;

import javafx.scene.image.Image;

import java.io.File;

public class Brick extends AnimationSprite {

    private static final double widthBrick = 14;
    private static final double heightBrick = 14;
    private static final double widthX = Sprite.size;
    private static final double heightY = Sprite.size;

    public Brick(double x, double y) {
        super(widthBrick, heightBrick, widthX, heightY);

        this.x = x;
        this.y = y;
        spriteX = 112;
        spriteY = 0;
        try {
            File file = new File(Wall.urlEntity);
            drawImage = new Image(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
