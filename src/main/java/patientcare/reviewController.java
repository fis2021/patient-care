package patientcare;

import com.mongodb.DBCursor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import patientcare.services.AppointmentService;
import patientcare.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class reviewController implements Initializable {

    @FXML
    private TextArea reviewTextArea;
    @FXML
    private TextField reviewTextField;
    @FXML
    private ImageView logoImageView;
    @FXML
    private Button returnButton;
    @FXML
    private Label reviewStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        DBCursor cursor = UserService.getReviewCollection().find();
        while(cursor.hasNext()){
            reviewTextArea.appendText((String) cursor.next().get("review")+"\n");
        }

    }
    public void returnButtonOnAction (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/myaccount.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 768, 574));
    }


    public void submitButtonOnAction (ActionEvent event) throws IOException{
        String review = reviewTextField.getText();
        UserService.addReview(review,UserService.loggedUser.email,UserService.doctor_mail_for_appointment);
        reviewStatus.setText("Thanks for your review!");

        reviewTextField.setText("");
        reviewTextArea.appendText(review + "\n");
    }
 }
