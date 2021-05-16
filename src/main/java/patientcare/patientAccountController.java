package patientcare;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import patientcare.services.UserService;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class patientAccountController implements Initializable {

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
    private Button aboutusBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button appointmentBtn;
    @FXML
    private Button reviewBtn;



    @FXML
    private TextField filterField;
    @FXML
    private TableView<Doctor> tableView;
    @FXML
    private TableColumn<Doctor,String> fname;
    @FXML
    private TableColumn<Doctor,String> lname;
    @FXML
    private TableColumn<Doctor,String> spec;
    @FXML
    private TableColumn<Doctor,String> email;
    @FXML
    private TableColumn<Doctor,String> phnum;

    private final ObservableList<Doctor> dataList = FXCollections.observableArrayList();


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


        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        spec.setCellValueFactory(new PropertyValueFactory<>("spec"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
       // phnum.setCellValueFactory(new PropertyValueFactory<>("phnum"));

        ArrayList<Doctor> doctori = new ArrayList<Doctor>();

        DBCursor cursor = UserService.getDoctorCollection().find();
        while(cursor.hasNext()){
            DBObject currentCursor = cursor.next();
            doctori.add(new Doctor(
                    (String) currentCursor.get("fname"),
                    (String) currentCursor.get("lname"),
                    (String) currentCursor.get("spec"),
                    (String) currentCursor.get("email")
                    //(String) currentCursor.get("phnum")
            ));
        }

        dataList.addAll(doctori);

        FilteredList<Doctor> filteredData = new FilteredList<>(dataList, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(doctor -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (doctor.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (doctor.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (doctor.getSpec().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (doctor.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }


                else
                    return false;

            });

        });

        SortedList<Doctor> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);


    }
    public void cancelButtonOnAction (ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    public void handleReviewBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/review.fxml"));

        Stage window = (Stage) reviewBtn.getScene().getWindow();
        window.setScene(new Scene(root, 520, 365));
    }

    public void handleAboutUsBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/aboutus.fxml"));

        Stage window = (Stage) aboutusBtn.getScene().getWindow();
        window.setScene(new Scene(root, 400, 338));
    }
    public void handleLogoutBtn() throws Exception {
        //to do
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root, 520, 400));
    }
    public void handleAppointmentBtn() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/appointment.fxml"));

        Stage window = (Stage) appointmentBtn.getScene().getWindow();
        window.setScene(new Scene(root, 725, 625));
    }
}
