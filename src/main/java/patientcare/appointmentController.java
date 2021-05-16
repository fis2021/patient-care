package patientcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import patientcare.services.AppointmentService;
import patientcare.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class appointmentController implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView infoImageView;
    @FXML
    private DatePicker dateField;
    @FXML
    private Label appointmentStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File infoFile = new File("images/info_icon.png");
        Image infoImage = new Image(infoFile.toURI().toString());
        infoImageView.setImage(infoImage);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        date = LocalDate.parse(date.format(formatter), formatter);
        dateField.setValue(date);
    }

    public void returnButtonOnAction (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/myaccount.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 768, 574));
    }

    public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void buttonOnAction(ActionEvent event) {
        if(AppointmentService.appointmentExists(UserService.doctor_mail_for_appointment,UserService.loggedUser.email,dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),((Button)event.getSource()).getText())){
            appointmentStatus.setText("Appointment already exists");
        }
        registerAppointment(((Button)event.getSource()).getText());
    }

    public void registerAppointment (String hour){

        AppointmentService.addAppointment(UserService.doctor_mail_for_appointment,UserService.loggedUser.email,dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),hour);
        appointmentStatus.setText("Success");

    }

}
