package game_OOP.entity.tile;

import game_OOP.entity.Sprite;
import javafx.scene.image.Image;

import java.io.File;

public class Wall extends Sprite {
    protected static final String urlEntity = "src/main/resources/img/classic.png";

    private static final double widthWall = 16;
    private static final double heightWall = 16;
    private static final double widthX = Sprite.size;
    private static final double heightY = Sprite.size;

    public Wall(double x, double y) {
        super(widthWall, heightWall, widthX, heightY);

        this.x = x;
        this.y = y;

        spriteX = 80;
        spriteY = 0;
        try {
            File file = new File(urlEntity);
            drawImage = new Image(file.toURI().toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
