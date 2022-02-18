package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Thread createThread;
    private Thread deleteThread;
    private Threading searchThread;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextArea quoteOfTheDay;

    /**
     * Fetching new root as new FXML file with the map to creating the stage.
     * @param event
     */
    public void addWebsiteScene(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/addWebsite.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/stylesheets.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Fetching new root as new FXML file with the map to creating the stage.
     * @param event
     */
    public void removeWebsiteScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/removeWebsite.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/stylesheets.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Fetching new root as new FXML file with the map to creating the stage.
     * @param event
     */
    public void mainPageScene(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/mainPage.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/stylesheets.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Fetching new root as new FXML file with the map to creating the stage.
     * @param event
     */
    public void findFileScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/findFile.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/stylesheets.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Fetching new root as new FXML file with the map to creating the stage.
     * @param event
     */
    public void settingsPageScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/settingsPage.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/stylesheets.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Exits application when user presses "X" in top right.
     */
    public void exitApplication() {
        Platform.exit();
        System.exit(0);
    }
    /**
     * Minimizes application when user presses "-" in top right.
     */
    public void setMinimized(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Applying users input to hostfile.
     * @param filePath
     * @param inputWebsite
     */
    public void createThread(String filePath,String inputWebsite) {
        createThread = new Thread(new Reader(this,filePath,inputWebsite),"Read and Write Thread");
    }

    /**
     * grabbing user input and deleting the chosen String(website) from host file.
     * @param filePath
     * @param inputWebsite
     */
    public void deleteThread(String filePath, String inputWebsite){
        deleteThread = new Thread(new DeleteThread(this,filePath,inputWebsite),"Read and Delete Thread");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert quoteOfTheDay != null;
    }

}