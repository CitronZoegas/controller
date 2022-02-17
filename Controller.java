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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    public void mainPageScene(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/mainPage.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/stylesheets.css")).toExternalForm());
            fillMainPageWithQuotes();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public void fillMainPageWithQuotes() {
        String str = null;

        try {
            Document doc = Jsoup.connect("https://www.azquotes.com/").userAgent("Mozilla/17.0").get();
            Elements element = doc.select("div.slide");
            int i = 0;
            System.out.println("lol");
            for(Element quotesList : element){
                System.out.println(i + Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                str = i + (Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                i++;
                System.out.println("lol");
                quoteOfTheDay.setText(str);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fillMainPageWithWebsites() {
        String str = null;

        try {
            Document doc = Jsoup.connect("https://www.azquotes.com/").userAgent("Mozilla/17.0").get();
            Elements element = doc.select("div.slide");
            int i = 0;
            System.out.println("lol");
            for(Element quotesList : element){
                System.out.println(i + Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                str = i + (Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                i++;
                System.out.println("lol");
                quoteOfTheDay.setText(str);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitApplication() {
        Platform.exit();
        System.exit(0);
    }

    public void setMinimized(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert quoteOfTheDay != null;
    }
    public void createThread(String filePath,String inputWebsite) {
        createThread = new Thread(new Reader(this,filePath,inputWebsite),"Read and Write Thread");
    }
    public void deleteThread(String filePath, String inputWebsite){
        deleteThread = new Thread(new DeleteThread(this,filePath,inputWebsite),"Read and Delete Thread");
    }
}