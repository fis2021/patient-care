package patientcare.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import patientcare.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class aboutusController implements Initializable {
    @FXML
    private ImageView logoImageView;
    @FXML
    private Button returnButton;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

    }
    public void returnButtonOnAction (ActionEvent event) throws IOException {

        if(UserService.loggedUser instanceof patientcare.users.patient) {
            Parent root = FXMLLoader.load(getClass().getResource("/patientAccount.fxml"));

            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 768, 574));
        }
       else if (UserService.loggedUser instanceof patientcare.users.doctor){
            Parent root = FXMLLoader.load(getClass().getResource("/doctorAccount.fxml"));

            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 768, 574));
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/homepage.fxml"));

            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 768, 574));
        }
    }
}
