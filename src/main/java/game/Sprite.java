package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private static final int width = 64;
    private static final int height = 96;
    public static void cropImageaddToFile() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/img/sprite1.png"));
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    buffImg.getGraphics().drawImage(bufferedImage, 0, 0, width, height, j*width, i*height, width*(j+1), height*(i+1), null);
                    String str = "src/main/resources/img/sprite_crop_" + j + i + ".png";
                    ImageIO.write(buffImg, "png", new File(str));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws IOException {
        cropImageaddToFile();
    }
}
