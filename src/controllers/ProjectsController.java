package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;

import java.io.IOException;

public class ProjectsController {

    @FXML
    private Button quitButton;

    @FXML
    private Button backButton;

    @FXML
    private Button checkSystemPerformance;


    @FXML
    void handleCheckSystemPerformanceButton(ActionEvent event) throws IOException {
        Runtime.getRuntime().exec("cmd /c taskmgr");

    }

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void back(ActionEvent event) {
        Main.loadScene("/resources/sample.fxml", "Cern Space");

    }

}
