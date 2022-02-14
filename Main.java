package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Platform.setImplicitExit(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/mainPage.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.getIcons().add(new Image("icons/AppIcon.png"));
        Scene scene = new Scene(root, 613, 702);
        primaryStage.setTitle("WWWControllerV2");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
            Platform.exit();
            System.exit(0);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}