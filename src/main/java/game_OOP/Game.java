package game_OOP;

import game_OOP.scene.EndScene;
import game_OOP.scene.GameScene;
import game_OOP.scene.GeneralScene;
import game_OOP.scene.WelcomeScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    private static GeneralScene[] scenes = new GeneralScene[3];

    public static final int welcomeScene = 0;
    public static final int gameScene= 1;
    public static final int endScene = 2;

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Game.stage = stage;

        scenes[welcomeScene] = new WelcomeScene();
        scenes[gameScene] = new GameScene();
        scenes[endScene] = new EndScene();

        changeScene(welcomeScene);
        stage.setTitle("Game");
        stage.show();
    }

    public static void changeScene(int number) {
        stage.setScene(scenes[number]);
        scenes[number].draw();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
