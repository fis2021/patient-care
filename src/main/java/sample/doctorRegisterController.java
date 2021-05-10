package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.services.UserService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class doctorRegisterController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private ImageView registerImageView;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label registrationLabelMessage;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField fnameTextField;
    @FXML
    private TextField lnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField pnTextField;
    @FXML
    private TextField specializationTextField;
    @FXML
    private RadioButton genderM;
    @FXML
    private RadioButton genderF;
    @FXML
    private RadioButton genderO;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File registerFile = new File("images/PC logo.png");
        Image registerImage = new Image(registerFile.toURI().toString());
        registerImageView.setImage(registerImage);
    }
    public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction (ActionEvent event) {

        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            confirmPasswordLabel.setText("");
            registrationLabelMessage.setText("User has been registered successfully!");
        } else
            confirmPasswordLabel.setText("Password does not match");
        if(fnameTextField.getText().isBlank() || lnameTextField.getText().isBlank() ||  usernameTextField.getText().isBlank() || emailTextField.getText().isBlank() || pnTextField.getText().isBlank() || specializationTextField.getText().isBlank()) {
            registrationLabelMessage.setText("");
        }
    }
    public void registerUser (){
        String gender="";
        if(genderM.isSelected()) gender="Male";
        else if(genderF.isSelected()) gender="Female";
        else if(genderO.isSelected()) gender="Other";
        UserService.addDoctor(fnameTextField.getText(), lnameTextField.getText(), pnTextField.getText(), specializationTextField.getText(),usernameTextField.getText(),setPasswordField.getText(),emailTextField.getText(), gender);


    }
}