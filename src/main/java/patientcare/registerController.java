package patientcare;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class registerController  {

    @FXML
    private Button  patientBtn;
    @FXML
    private Button doctorBtn;

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
}
