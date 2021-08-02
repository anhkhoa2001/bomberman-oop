package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite {
    private static final int width = 32;
    private static final int height = 32;
    private double x;
    private double y;

    // Player
    public static ImageView player_sprite_00 = Sprite.getSprite("player_sprite_00");
    public static ImageView player_sprite_01 = Sprite.getSprite("player_sprite_01");
    public static ImageView player_sprite_02 = Sprite.getSprite("player_sprite_02");
    public static ImageView player_sprite_03 = Sprite.getSprite("player_sprite_03");
    public static ImageView player_sprite_10 = Sprite.getSprite("player_sprite_10");
    public static ImageView player_sprite_11 = Sprite.getSprite("player_sprite_11");
    public static ImageView player_sprite_12 = Sprite.getSprite("player_sprite_12");
    public static ImageView player_sprite_13 = Sprite.getSprite("player_sprite_13");
    public static ImageView player_sprite_20 = Sprite.getSprite("player_sprite_20");
    public static ImageView player_sprite_21 = Sprite.getSprite("player_sprite_21");
    public static ImageView player_sprite_22 = Sprite.getSprite("player_sprite_22");
    public static ImageView player_sprite_23 = Sprite.getSprite("player_sprite_23");
    public static ImageView player_sprite_30 = Sprite.getSprite("player_sprite_30");
    public static ImageView player_sprite_31 = Sprite.getSprite("player_sprite_31");
    public static ImageView player_sprite_32 = Sprite.getSprite("player_sprite_32");
    public static ImageView player_sprite_33 = Sprite.getSprite("player_sprite_33");
    public static ImageView player_sprite_40 = Sprite.getSprite("player_sprite_40");
    public static ImageView player_sprite_41 = Sprite.getSprite("player_sprite_41");
    public static ImageView player_sprite_42 = Sprite.getSprite("player_sprite_42");
    public static ImageView player_sprite_43 = Sprite.getSprite("player_sprite_43");
    public static ImageView player_sprite_50 = Sprite.getSprite("player_sprite_50");
    public static ImageView player_sprite_51 = Sprite.getSprite("player_sprite_51");
    public static ImageView player_sprite_52 = Sprite.getSprite("player_sprite_52");
    public static ImageView player_sprite_53 = Sprite.getSprite("player_sprite_53");
    public static ImageView player_sprite_60 = Sprite.getSprite("player_sprite_60");
    public static ImageView player_sprite_61 = Sprite.getSprite("player_sprite_61");
    public static ImageView player_sprite_62 = Sprite.getSprite("player_sprite_62");
    public static ImageView player_sprite_63 = Sprite.getSprite("player_sprite_63");
    public static ImageView player_sprite_70 = Sprite.getSprite("player_sprite_70");
    public static ImageView player_sprite_71 = Sprite.getSprite("player_sprite_71");
    public static ImageView player_sprite_72 = Sprite.getSprite("player_sprite_72");
    public static ImageView player_sprite_73 = Sprite.getSprite("player_sprite_73");

    //
    public static ImageView player_tile_50 = Sprite.getSprite("player_tile_50");
    public static ImageView player_tile_60 = Sprite.getSprite("player_tile_60");
    public static ImageView player_tile_70 = Sprite.getSprite("player_tile_70");

    public static void cropImagePlayer() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/game_img/bomberman2.png"));
            for(int i=0; i<4; i++) {
                for(int j=0; j<7; j++) {
                    BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    buffImg.getGraphics().drawImage(bufferedImage, 0, 0, width, height, j*width, i*height, width*(j+1), height*(i+1), null);
                    String str = "src/main/resources/game_img/player_sprite_" + j + i + ".png";
                    ImageIO.write(buffImg, "png", new File(str));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void cropImageWall() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/classic.png"));
            for(int i=0; i<12; i++) {
                for(int j=0; j<16; j++) {
                    BufferedImage buff = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                    buff.getGraphics().drawImage(bufferedImage, 0, 0 , 16, 16, j*16, i*16, (j+1)*16, (i+1)*16,null);
                    String str = "src/main/resources/game_img/player_tile_" + j + i + ".png";
                    ImageIO.write(buff, "png", new File(str));
                }
            }
        }catch (Exception e) {

        }
    }
    public static ImageView getSprite(String str) {
        try {
            File file = new File("src/main/resources/game_img/" + str + ".png");
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width*2);
            imageView.setFitHeight(height*2);
            return imageView;
        } catch (Exception e) {
            return null;
        }
    }
}
