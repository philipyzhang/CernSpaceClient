package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.Main;

public class MainController {

    @FXML
    private Button runningProjectButton;

    @FXML
    private Button otherProjectsButton;

    @FXML
    private Button softwareUpdateButton;

    @FXML
    private Button openWebAppButton;

    @FXML
    private ImageView image;

    @FXML
    private Button privacyPolicyButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label title;

    @FXML
    void handleOpenWebAppButtonClick(ActionEvent event) {

    }

    @FXML
    void handleOtherProjectsButtonClick(ActionEvent event) {

    }

    @FXML
    void handlePrivacyPolicyButtonClick(ActionEvent event) {

    }

    @FXML
    void handleRunningProjectButtonClick(ActionEvent event) {
        Main.loadScene("/resources/projects.fxml", "Cern Space");
    }

    @FXML
    void handleSoftwareUpdateButtonClick(ActionEvent event) {

    }

    @FXML
    void quit(ActionEvent event) {

    }
}
