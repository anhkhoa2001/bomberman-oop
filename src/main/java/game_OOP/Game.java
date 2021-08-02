package game_OOP;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GameScene scene = new GameScene();
        scene.draw();
        stage.setScene(scene);
        stage.setTitle("Game");
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
