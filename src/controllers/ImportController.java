package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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
    void handleSearchBarInput(KeyEvent event) {
        // TODO: filter table items based on regex string inputted on every keystroke
        try {
            List<Project> projects = ProjectsFetcher.getInstance().getProjects();
            ObservableList<Project> projectObservableList = FXCollections.observableList(projects);

            FilteredList<Project> projectsFilteredList = new FilteredList(projectObservableList, p -> true);//Pass the data to a filtered list
            projectsFilteredList.setPredicate(p -> p.getName().toLowerCase().contains(searchBar.getText().toLowerCase().trim()));
            table.getItems().setAll(projectsFilteredList);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                dockerManager.runProject(project);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Successfully installed " + project.getName());
                alert2.setHeaderText("Installation Success");
                alert2.showAndWait();
            } catch (Exception e) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("ERROR");
                alert2.setHeaderText("ERROR");
                alert2.setContentText("ERROR");
                alert2.showAndWait();
            }

        } else if (project == null) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Alert");
            alert2.setHeaderText("No Project Selected ");
            alert2.setContentText("Please select a project from the table");
            alert2.showAndWait();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert");
            alert1.setHeaderText("Docker is not Installed, couldn't install " + project.getName());
            alert1.setContentText("We have detected that you do not have docker installed. Please Install Docker from the Home Screen");
            alert1.showAndWait();

        }

        System.out.println(DockerManager.getInstance().getCurrentProject());
    }


}
