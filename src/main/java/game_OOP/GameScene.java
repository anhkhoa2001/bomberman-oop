package game_OOP;

import game_OOP.entity.Player;
import game_OOP.entity.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GameScene extends GeneralScene {

    private Player bomber;

    public GameScene() {
        super();
        bomber = new Player();
    }

    @Override
    public void draw() {
        pressedKey.clear();
        new AnimationTimer() {
            public void handle(long l) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                bomber.draw(gc);

                if(pressedKey.contains(KeyCode.D)) {
                    bomber.moveX(Sprite.speed/2);
                }
                else if(pressedKey.contains(KeyCode.A)) {
                    bomber.moveX(-Sprite.speed/2);
                }
                else if(pressedKey.contains(KeyCode.W)) {
                    bomber.moveY(-Sprite.speed/2);
                }
                else if(pressedKey.contains(KeyCode.S)) {
                    bomber.moveY(Sprite.speed/2);
                }
                else {
                    bomber.stop();
                }
            }
        }.start();
    }
}
