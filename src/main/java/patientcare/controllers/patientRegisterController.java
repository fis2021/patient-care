package patientcare.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import patientcare.exceptions.MailExists;
import patientcare.exceptions.UsernameExists;
import patientcare.exceptions.emptyTextFieldExceptions;
import patientcare.services.UserService;

//import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class patientRegisterController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button returnButton;
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
    private RadioButton genderM;
    @FXML
    private RadioButton genderF;
    @FXML
    private RadioButton genderO;
    @FXML
    private DatePicker dobField;

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

    public void returnButtonOnAction (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/register.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 280, 230));
    }

    public void registerButtonOnAction (ActionEvent event) throws InterruptedException, IOException {
        try {
            this.emptyTextField();
            UserService.checkExistingMail(emailTextField.getText());
            UserService.checkExistingUsername(usernameTextField.getText());

            if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
                registerUser();
                confirmPasswordLabel.setText("");
                registrationLabelMessage.setText("User has been registered successfully!");

                Thread.sleep(1500);

                Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

                Stage window = (Stage) registerButton.getScene().getWindow();
                window.setScene(new Scene(root, 520, 400));
            } else
                confirmPasswordLabel.setText("Password does not match");
        }catch (emptyTextFieldExceptions  e){
            registrationLabelMessage.setText(e.getMessage());
        }catch(MailExists me){
            registrationLabelMessage.setText(me.getMessage());
        }catch(UsernameExists ue){
            registrationLabelMessage.setText(ue.getMessage());
        }
    }

    public void registerUser (){
        String gender="";

        if(genderM.isSelected()) gender="Male";
        else if(genderF.isSelected()) gender="Female";
        else if(genderO.isSelected()) gender="Other";
        UserService.addPatient(fnameTextField.getText(), lnameTextField.getText(), pnTextField.getText(), dobField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ,usernameTextField.getText(),setPasswordField.getText(),emailTextField.getText(),gender);

    }
    public void emptyTextField() throws emptyTextFieldExceptions {
        if(             fnameTextField.getText().equals("") ||
                lnameTextField.getText().equals("") ||
                pnTextField.getText().equals("") ||
                usernameTextField.getText().equals("")||
                setPasswordField.getText().equals("")||
                emailTextField.getText().equals("")){
            throw new emptyTextFieldExceptions();
        }
    }
}
