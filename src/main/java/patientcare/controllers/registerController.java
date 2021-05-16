package patientcare.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class registerController  {

    @FXML
    private Button  patientBtn;
    @FXML
    private Button doctorBtn;
    @FXML
    private Button returnButton;


    public void handleDoctorBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/doctorRegister.fxml"));

        Stage window = (Stage) doctorBtn.getScene().getWindow();
        window.setScene(new Scene(root, 610, 470));
    }
    public void handlePatientBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/patientRegister.fxml"));

        Stage window = (Stage) patientBtn.getScene().getWindow();
        window.setScene(new Scene(root, 610, 470));
    }
    public void returnButtonOnAction (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 520, 400));
    }
}
