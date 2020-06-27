package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import utils.Project;
import utils.ProjectsFetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static Stage stage;

    /* EFFECTS: loads the window given the filePath and titles it
       eg. Main.loadScene("/resources/sample.fxml", "Cern Space");
       can use this from any file
     */
    public static void loadScene(String resourceName, String title) {
        //Load new FXML and assign it to scene
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(resourceName));
            stage.setTitle(title);
            stage.setScene(new Scene(root, 803, 696));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Main.loadScene("/resources/sample.fxml", "Cern Space");
    }

    public static void main(String[] args) { launch(args); }
}
