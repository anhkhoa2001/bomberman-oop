package game_OOP.entity.tile;

import game_OOP.entity.Sprite;
import javafx.scene.image.Image;

import java.io.File;

public class Grass extends Sprite {
    private static final double widthGrass = 14;
    private static final double heightGrass = 14;
    private static final double widthX = Sprite.size;
    private static final double heightY = Sprite.size;

    public Grass(double x, double y) {
        super(widthGrass, heightGrass, widthX, heightY);
        this.x = x;
        this.y = y;

        spriteX = 97;
        spriteY = 0;

        try {
            File file = new File(Wall.urlEntity);
            drawImage = new Image(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
