package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FXML/mainPage.fxml"));
        //Controller controller = fxmlLoader.getController();
        Parent root = fxmlLoader.load();
        updateQuotePanel();
        Scene scene = new Scene(root, 613, 702);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/stylesheets.css")).toExternalForm());

        primaryStage.getIcons().add(new Image("icons/AppIcon.png"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("WWWControllerV2");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public void updateQuotePanel() {
        Controller controller = new Controller();
        controller.fillMainPageWithQuotes();
    }
}