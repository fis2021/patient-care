package patientcare;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class homepageController implements Initializable {

    @FXML
    private ImageView mainImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView searchImageView;
    @FXML
    private ImageView nameImageView;


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
}
