package patientcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class specializationsController implements Initializable {

    @FXML
    private ImageView logoImageView;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

    }
    public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
