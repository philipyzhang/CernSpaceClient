package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Project;
import models.ProjectsManager;

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

    @FXML
    void handleSearchBarInput(ActionEvent event) {

    }

    @FXML
    void quit(ActionEvent event) {

    }

}
