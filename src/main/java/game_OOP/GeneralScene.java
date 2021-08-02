package game_OOP;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.Set;

public abstract class GeneralScene extends Scene {
    public AnchorPane root = new AnchorPane();
    protected Canvas canvas;
    public GraphicsContext gc;

    protected Set<KeyCode> pressedKey = new HashSet<>();
    protected Set<KeyCode> resealedKey = new HashSet<>();

    private static final double WIDTH = 1280;
    private static final double HEIGHT = 720;

    public static final double constLength = 32;


    public GeneralScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);

        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        this.setRoot(root);

        this.setOnKeyPressed(e -> {
            pressedKey.add(e.getCode());
        });
        this.setOnKeyReleased(e -> {
            pressedKey.remove(e.getCode());
            resealedKey.add(e.getCode());
        });

        root.getChildren().addAll(canvas);
    }

    public abstract void draw();
}
