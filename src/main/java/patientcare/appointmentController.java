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
import javafx.stage.Stage;
import patientcare.services.AppointmentService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        Parent root = FXMLLoader.load(getClass().getResource("/patientAccount.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 768, 574));
    }
    public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void buttonOnAction(ActionEvent event) {
        if(AppointmentService.appointmentExists("",dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),((Button)event.getSource()).getText())){
            appointmentStatus.setText("Appointment already exists");
        }
        //we need to add a function that stores the doctor name,after we select it from the list or id,idk
        registerAppointment("",((Button)event.getSource()).getText());
    }
    public void registerAppointment (String doctor,String hour){

        AppointmentService.addAppointment("",dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),hour);
        appointmentStatus.setText("Success");

    }

}
