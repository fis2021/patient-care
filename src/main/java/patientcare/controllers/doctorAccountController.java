package patientcare.controllers;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import patientcare.services.AppointmentService;
import patientcare.services.UserService;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class doctorAccountController implements Initializable {

    @FXML
    private ImageView mainImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView nameImageView;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView searchImageView;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button aboutusBtn;
    @FXML
    private Label loggedUsername;

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, String> fname;
    @FXML
    private TableColumn<Patient, String> lname;
    @FXML
    private TableColumn<Patient, String> email;
    @FXML
    private TableColumn<Patient, String> mobilenum;

    private final ObservableList<Patient> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File mainFile = new File("images/main.jpg");
        Image mainImage = new Image(mainFile.toURI().toString());
        mainImageView.setImage(mainImage);

        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File nameFile = new File("images/scris.png");
        Image nameImage = new Image(nameFile.toURI().toString());
        nameImageView.setImage(nameImage);

        File searchFile = new File("images/searchicon.png");
        Image searchImage = new Image(searchFile.toURI().toString());
        searchImageView.setImage(searchImage);

        loggedUsername.setText( UserService.loggedUser.username);


        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobilenum.setCellValueFactory(new PropertyValueFactory<>("mobilenum"));

        ArrayList<Patient> pacienti = new ArrayList<Patient>();

        DBCursor cursor = UserService.getPatientCollection().find();
        while(cursor.hasNext()) {
            DBObject currentCursor = cursor.next();
            if (AppointmentService.appointmentExistsByPatientAndDoctor((String) currentCursor.get("email"), UserService.loggedUser.email)) {
                pacienti.add(new Patient(
                        (String) currentCursor.get("fname"),
                        (String) currentCursor.get("lname"),
                        (String) currentCursor.get("email"),
                        (String) currentCursor.get("mobilenum")
                ));
            }
        }
        dataList.addAll(pacienti);

        FilteredList<Patient> filteredData = new FilteredList<>(dataList, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(patient -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (patient.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (patient.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if (patient.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }


                else
                    return false;

            });

        });

        SortedList<Patient> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);


    }
    public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void handleAboutUsBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/aboutus.fxml"));

        Stage window = (Stage) aboutusBtn.getScene().getWindow();
        window.setScene(new Scene(root, 400, 338));
    }
    public void handleLogoutBtn() throws Exception {
        //to do
        Parent root = FXMLLoader.load(getClass().getResource("/homepage.fxml"));

        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root, 768, 574));
    }
}