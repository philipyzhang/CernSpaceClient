module cernspaceonline {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.swt;

    opens main;
    opens controllers to javafx.fxml;
    opens models;
}