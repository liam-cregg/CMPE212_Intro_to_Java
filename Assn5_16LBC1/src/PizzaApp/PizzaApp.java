package PizzaApp;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PizzaApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            GridPane root = FXMLLoader.load(getClass().getResource("PizzaApp.fxml"));
            primaryStage.setTitle("Pizza Order");
            Image img = new Image("PizzaApp/pepperoni.jpg");
            primaryStage.getIcons().add(img);
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}