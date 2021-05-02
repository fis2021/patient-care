package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/54780502a8d94d459ec2c286b125a37c.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("images/kisspng-computer-icons-login-clip-art-share-button-creative-5ae94dbe8914c0.0781218515252392305615.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }


    public void loginButtonOnAction (ActionEvent event) {

        loginMessageLabel.setText("You try to login");
         if(usernameTextField.getText().isEmpty() == false && enterPasswordField.getText().isEmpty() == false) {
             //validateLogin();
         } else {
             loginMessageLabel.setText("Please enter username and password.");
         }
     }

     public void cancelButtonOnAction (ActionEvent event) {
         Stage stage = (Stage) cancelButton.getScene().getWindow();
         stage.close();
     }
}







