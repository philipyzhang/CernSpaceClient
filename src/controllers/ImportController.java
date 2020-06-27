package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.Main;
import models.Project;
import utils.DockerManager;
import utils.ProjectsFetcher;

import java.util.List;

public class ImportController {
    @FXML
    private Button quitButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Project> table;

    @FXML
    private TableColumn<Project, RadioButton> btn;

    public void initialize() {
        // thread to do non FX calculations, in this case a cloud fetch

        // display a row that says its loading
        table.getItems().add(new Project(
                "",
                "loading...",
                "",
                "",
                "",
                0,
                "",
                0
        ));

        new Thread(() -> {
            try {
                List<Project> projects = ProjectsFetcher.getInstance().getProjects();
                Platform.runLater(new Runnable() {
                    public void run() {
                        // this code is run in the ui thread
                        table.getItems().setAll(projects);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void handleSearchBarInput(ActionEvent event) {
        // TODO:
    }

    @FXML
    void back(ActionEvent event) {
        Main.loadScene("/resources/sample.fxml", "Cern Space");

    }

    @FXML
    void handleInstallButtonClick(ActionEvent event) {
        Project project = table.getSelectionModel().getSelectedItem();
        DockerManager dockerManager = DockerManager.getInstance();
        if (DockerManager.getInstance().checkDocker()) {
            try {
                dockerManager.joinSwarm(project.getIp(), project.getPort(), project.getToken());
            } catch (Exception e) {
                System.out.println("Swarm join exception/ selection error");
            }

        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Alert");
            alert1.setHeaderText("Docker is not Installed, couldn't install " + project.getName());
            alert1.setContentText("We have detected that you do not have docker installed. Please Install Docker from the Home Screen");
            alert1.showAndWait();

        }
    }
}
