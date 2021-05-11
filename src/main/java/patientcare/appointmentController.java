package patientcare;

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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class appointmentController implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView infoImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File infoFile = new File("images/info_icon.png");
        Image infoImage = new Image(infoFile.toURI().toString());
        infoImageView.setImage(infoImage);
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

}
