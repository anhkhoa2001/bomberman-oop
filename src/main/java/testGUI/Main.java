package testGUI;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private final double move = 5;
    private Rectangle rectangle = new Rectangle();
    private Rectangle rectangle1 = new Rectangle();
    private boolean top, bot, right, left;
    private final double width = 1024, height = 768;


    @Override
    public void start(Stage primaryStage){
        FXMLLoader load = new FXMLLoader();
        Controller controller = new Controller();
        //
        Group root = new Group();
        //
        //
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setHeight(100);
        rectangle.setWidth(100);
        rectangle.setFill(Color.RED);
        //
        rectangle1.setX(200);
        rectangle1.setY(300);
        rectangle1.setHeight(150);
        rectangle1.setWidth(200);
        rectangle1.setFill(Color.GREEN);


        System.out.println(rectangle.getX());
        //
        root.getChildren().addAll(rectangle, rectangle1);
        Scene scene = new Scene(root, width, height, Color.BLACK);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        right = true;
                        break;
                    case A:
                        left = true;
                        break;
                    case W:
                        top = true;
                        break;
                    case S:
                        bot = true;
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        right = false;
                        break;
                    case A:
                        left = false;
                        break;
                    case W:
                        top = false;
                        break;
                    case S:
                        bot = false;
                        break;
                }
            }
        });
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                double dx = 0, dy = 0;
                if(top) dy -= move;
                if(left) dx -= move;
                if(right) dx += move;
                if(bot) dy += move;

                moveRectangle(dx, dy);
            }
        };
        timer.start();
    }

    private void moveRectangle(double dx, double dy) {
        double x = rectangle.getX() + dx;
        double y = rectangle.getY() + dy;
        if(x>=0 && y>=0 && x<=(width-rectangle.getWidth()) && y<=(height-rectangle.getHeight())) {
            rectangle.setX(x);
            rectangle.setY(y);
        }
    }

    public void collision() {

    }



    public static void main(String[] args) {
        launch(args);
    }
}
