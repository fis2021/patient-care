package patientcare.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import patientcare.services.UserService;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import java.net.URL;

public class loginController implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button newaccountBtn;
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


    public void loginButtonOnAction (ActionEvent event) throws IOException {

        loginMessageLabel.setText("You try to login");
         if(usernameTextField.getText().isEmpty() == false && enterPasswordField.getText().isEmpty() == false) {
             if (UserService.validateLogin(usernameTextField.getText(),enterPasswordField.getText())){
                 loginMessageLabel.setText("Login Successful");
                 if(UserService.loggedUser instanceof patientcare.users.patient) {
                     Parent root = FXMLLoader.load(getClass().getResource("/patientAccount.fxml"));


                     Stage window = (Stage) loginButton.getScene().getWindow();
                     window.setScene(new Scene(root, 768, 574));
                 }
                 if (UserService.loggedUser instanceof patientcare.users.doctor){
                     Parent root = FXMLLoader.load(getClass().getResource("/doctorAccount.fxml"));

                     Stage window = (Stage) loginButton.getScene().getWindow();
                     window.setScene(new Scene(root, 768, 574));
                 }

             }
             else{
                 loginMessageLabel.setText("Incorrect credentials!");
             }
         } else {
             loginMessageLabel.setText("Please enter username and password.");
         }
     }

    public void returnButtonOnAction (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/homepage.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 768, 574));
    }

    public void newAccountButtonOnAction (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/register.fxml"));

        Stage window = (Stage) newaccountBtn.getScene().getWindow();
        window.setScene(new Scene(root, 280, 230));
    }

}







