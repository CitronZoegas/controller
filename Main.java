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

    public Main instance;
    public static Controller controller;


    @Override
    public void start(Stage primaryStage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FXML/mainPage.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.fillMainPageWithQuotes();
        controller.fillMainPageWithWebsites();

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

    public static Controller getController(){
        return controller;
    }

}