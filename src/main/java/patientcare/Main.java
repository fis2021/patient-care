package patientcare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import patientcare.services.UserService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 280, 230));

        primaryStage.show();
    }


    public static void main(String[] args) {
        UserService.Initialize();
        launch(args);

        UserService.printDoctors();
        UserService.printPatients();

       // UserService.dropDB();
    }

}