package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Project;
import models.ProjectsManager;
import main.Main;

public class ImportController {
    @FXML
    private Button quitButton;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Project> table;

    public void initialize(){
        ProjectsManager projectManager = ProjectsManager.getInstance();
        table.getItems().setAll(projectManager.getProjects());
    }
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
