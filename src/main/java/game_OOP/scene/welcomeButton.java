package game_OOP.scene;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class welcomeButton extends Button {
    private static final String urlFont = "src/main/resources/font/NotoSansJP-Bold.otf";

    private static final String urlButton = "-fx-background-image: url('src/main/resources/img/button.png')";

    public welcomeButton(String text) {
        setText(text);
        setFontBtn();
        setLayoutBtn();
    }
    public void setFontBtn() {
        try {
            setFont(Font.loadFont(new FileInputStream(urlFont) ,24));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Arial", 24));
        }
    }
    public void setLayoutBtn() {
        setStyle(urlButton);
        setTranslateX(200);
        setTranslateY(200);
        setPrefHeight(200);
        setLayoutY(getLayoutY() + 10);
    }
}
