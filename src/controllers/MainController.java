package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.Main;
import utils.DockerManager;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainController{

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
    void handleOpenWebAppButtonClick(ActionEvent event) throws URISyntaxException, IOException {
        URI uri = new URI("https://cernspace.online/");
        Desktop dt = Desktop.getDesktop();
        dt.browse(uri);

    }

    @FXML
    void handleOtherProjectsButtonClick(ActionEvent event) {
        Main.loadScene("/resources/import.fxml", "Cern Space");

    }

    @FXML
    void handlePrivacyPolicyButtonClick(ActionEvent event) throws URISyntaxException, IOException {

        URI uri = new URI("https://cernspace.online/privacy-policy");
        Desktop dt = Desktop.getDesktop();
        dt.browse(uri);

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
    public void handleDownloadDockerButtonClick(ActionEvent actionEvent) throws URISyntaxException, IOException {
        if (DockerManager.getInstance().checkDocker()) {
            System.out.println("Docker installed! :D");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Docker Already Installed");
            alert.setContentText("We have detected that you already have docker installed.");
            alert.show();
        } else {
            System.out.println("Docker not installed. :(");
            URI uri = new URI("https://www.docker.com/get-started");
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
            // TODO: open browser to show "https://docker.com/get-started"

        }
    }

    @FXML
    public void handleSoftwareLicenseButtonClick(ActionEvent actionEvent) throws URISyntaxException, IOException {
        URI uri = new URI("https://cernspace.online/eula");
        Desktop dt = Desktop.getDesktop();
        dt.browse(uri);
    }

}
