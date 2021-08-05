package game_OOP.scene;

import game_OOP.Game;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class WelcomeScene extends GeneralScene {

    public WelcomeScene() {
        super();
    }
    @Override
    public void draw() {
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case SPACE:
                        Game.changeScene(Game.gameScene);
                        break;
                }
            }
        });
    }
}
