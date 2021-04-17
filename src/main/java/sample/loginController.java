package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.util.ResourceBundle;

import java.net.URL;

public class loginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingimageView;
    @FXML
    private ImageView lockimageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File( " images/54780502a8d94d459ec2c286b125a37c.png");
        Image brandingImage = new Image( brandingFile.toURI().toString());
        brandingimageView.setImage(brandingImage);

        File lockFile = new File( " images/kisspng-computer-icons-login-clip-art-share-button-creative-5ae94dbe8914c0.0781218515252392305615.png.png");
        Image lockImage = new Image( lockFile.toURI().toString());
        lockimageView.setImage(brandingImage);
    }

    public void loginButtonOnAction (ActionEvent event) {
        loginMessageLabel.setText("You try to login");
    }

     public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
