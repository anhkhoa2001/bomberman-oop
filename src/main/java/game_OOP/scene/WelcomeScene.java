package game_OOP.scene;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class WelcomeScene extends GeneralScene {

    public AnchorPane root = new AnchorPane();

    public WelcomeScene() {
        super();

        setRoot(root);

        Group group;

        File file = new File("src/main/resources/img/button.jpg");
        try {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            group = new Group(imageView);
            Button button = new welcomeButton("OK");

            Button button1 = new Button("Click");

            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            button1.setBackground(background);


            root.getChildren().addAll(button1 , button, group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(){};
}
