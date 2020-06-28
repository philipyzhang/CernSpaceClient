package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;
import models.Project;
import utils.DockerManager;

import java.io.IOException;

public class ProjectsController {

    @FXML
    private Button quitButton;

    @FXML
    private Button backButton;

    @FXML
    private Button checkSystemPerformance;

    @FXML
    private Label projectNameLabel;

    @FXML
    private Label projectDescriptionLabel;

    /**
     * On screen switch, displays the current project
     */
    public void initialize() {
        Project project = DockerManager.getInstance().getCurrentProject();

        if(project != null) {
            projectNameLabel.setText(project.getName());
            projectDescriptionLabel.setText(project.getLongDescription());
        }
    }

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
