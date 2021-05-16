module patientcare {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires mongo.java.driver;

    opens patientcare;
    opens patientcare.controllers;
    opens patientcare.services;
}