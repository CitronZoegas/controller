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
import javafx.scene.control.TextField;
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
    private Threading threading;
    private Controller controller;
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
    @FXML
    private TextArea popularWebsite;
    @FXML
    private TextField enterHostArea;


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
     * Fetching new root to a new FXML file creating the stage.
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
            controller = Main.getController();
            controller.fillMainPageWithWebsites();
            controller.fillMainPageWithQuotes();
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
     * Filling a "Pane" of "TextArea" with quotes from https://www.azquotes.com/ which have a daily quote which could inspire you.
     *
     * I added this for no systematical benefit at all, I just really wanted to have a motivational quite section in my application.
     */
    @FXML
    protected void fillMainPageWithQuotes() {

        try {
            Document doc = Jsoup.connect("https://www.azquotes.com/").userAgent("Mozilla/17.0").get();
            Elements element = doc.select("div.slide");
            int i = 0;
            for(Element quotesList : element){
                System.out.println(i + Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                String str = (Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                i++;
                quoteOfTheDay.setWrapText(true);
                quoteOfTheDay.setText(str);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Filling a "Pane" of "TextArea" with commonly used websites, to make it easier for users to get inspiration and possibly remember what to block.
     *
     * tagName: "a" is the Anchor in html lingo.
     * div.slide: its the class called slide that we are selecting with the help of Jsoup library.
     *
     * @return String
     */
    @FXML
    protected void fillMainPageWithWebsites() {

        try {
            Document doc = Jsoup.connect("https://www.investisdigital.com/blog/technology/tiktok-most-visited-site-2021").userAgent("Mozilla/17.0").get();
            Elements element = doc.select("div.blog-body");
            int i = 0;
            for(Element quotesList : element){
                //System.out.println(i +" " +Objects.requireNonNull(quotesList.getElementsByTag("ol").first()).text());
                String str = (Objects.requireNonNull(quotesList.getElementsByTag("ol").first()).text());
                i++;

                popularWebsite.setWrapText(true);
                popularWebsite.setText(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchingForHostFile() {
        String hostName = enterHostArea.getText();
        threading = new Threading(this,hostName);
    }
    public void whyButton() {
        System.out.println("This is to find, on your unique computer the host file and make this program work as good as possible!:)");
    }
    /**
     * Applying users input to hostfile.
     * @param filePath
     * @param inputWebsite
     */
    public void createThread(String filePath,String inputWebsite) {
        createThread = new Thread(new ReadingInputThread(this,filePath,inputWebsite),"Read and Write Thread");
    }

    /**
     * grabbing user input and deleting the chosen String(website) from host file.
     * @param filePath
     * @param inputWebsite
     */
    public void deleteThread(String filePath, String inputWebsite){
        deleteThread = new Thread(new DeleteThread(this,filePath,inputWebsite),"Read and Delete Thread");
    }
    public void outPutFoundFile(String fileFound) {
        //mainPanel.OutPutToTextArea(fileFound);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}