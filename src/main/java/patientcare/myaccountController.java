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
import java.net.URL;
import java.util.ResourceBundle;

public class myaccountController implements Initializable {

    @FXML
    private ImageView mainImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView searchImageView;
    @FXML
    private ImageView nameImageView;
    @FXML
    private Button cancelButton;
    @FXML
    private Button specializationsBtn;
    @FXML
    private Button doctorsBtn;
    @FXML
    private Button aboutusBtn;
    @FXML
    private Button logoutBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File mainFile = new File("images/main.jpg");
        Image mainImage = new Image(mainFile.toURI().toString());
        mainImageView.setImage(mainImage);

        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File searchFile = new File("images/searchicon.png");
        Image searchImage = new Image(searchFile.toURI().toString());
        searchImageView.setImage(searchImage);

        File nameFile = new File("images/scris.png");
        Image nameImage = new Image(nameFile.toURI().toString());
        nameImageView.setImage(nameImage);

    }
    public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void handleSpecializationsBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/specializations.fxml"));

        Stage window = (Stage) specializationsBtn.getScene().getWindow();
        window.setScene(new Scene(root, 400, 400));
    }
    public void handleDoctorsBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/doctors.fxml"));

        Stage window = (Stage) doctorsBtn.getScene().getWindow();
        window.setScene(new Scene(root, 400, 400));
    }
    public void handleAboutUsBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/aboutus.fxml"));

        Stage window = (Stage) aboutusBtn.getScene().getWindow();
        window.setScene(new Scene(root, 400, 338));
    }
    public void handleLogoutBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root, 520, 400));
    }
}
