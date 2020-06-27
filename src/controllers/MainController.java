package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.Main;
import utils.DockerManager;

public class MainController {

    @FXML
    private Button runningProjectButton;

    @FXML
    private Button otherProjectsButton;

    @FXML
    private Button softwareUpdateButton;

    @FXML
    private Button downloadDockerButton;

    @FXML
    private Button openWebAppButton;

    @FXML
    private ImageView image;

    @FXML
    private Button privacyPolicyButton;
    @FXML
    private Button softwareLicenseButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label title;

    @FXML
    void handleOpenWebAppButtonClick(ActionEvent event) {

    }

    @FXML
    void handleOtherProjectsButtonClick(ActionEvent event) {
        Main.loadScene("/resources/import.fxml", "Cern Space");

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
        Platform.exit();

    }

    @FXML
    public void handleDownloadDockerButtonClick(ActionEvent actionEvent) {
        if (DockerManager.getInstance().checkDocker()) {
            System.out.println("Docker installed! :D");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Docker Already Installed");
            alert.setContentText("We have detected that you already have docker installed.");
            alert.show();
        } else {
            System.out.println("Docker not installed. :(");
            // TODO: open browser to show "https://docker.com/get-started"

        }
    }

    @FXML
    public void handleSoftwareLicenseButtonClick(ActionEvent actionEvent) {
    }
}
