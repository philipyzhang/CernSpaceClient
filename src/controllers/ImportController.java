package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;

public class ImportController {


    @FXML
    private Button quitButton;

    @FXML
    private Button backButton;


    @FXML
    void quit(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void back(ActionEvent event) {
        Main.loadScene("/resources/sample.fxml", "Cern Space");

    }

}
