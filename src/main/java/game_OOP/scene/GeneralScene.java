package game_OOP.scene;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public abstract class GeneralScene extends Scene {
    protected static final double WIDTH = 1200;
    protected static final double HEIGHT = 664;

    public static int numberScene = 0;

    public GeneralScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);
    }

    public abstract void draw();
}
