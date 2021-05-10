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

public class doctorsController implements Initializable {
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

        Parent root = FXMLLoader.load(getClass().getResource("/homepage.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 768, 574));
    }
}
