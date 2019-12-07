package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseInformation extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("CourseInformation.fxml"));
        primaryStage.setTitle("Course Information");
        primaryStage.setScene(new Scene(root, 500, 620));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
