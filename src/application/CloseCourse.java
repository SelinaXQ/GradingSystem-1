package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CloseCourse extends Application{
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("CloseCourse.fxml"));
        primaryStage.setTitle("Confirm:");
        primaryStage.setScene(new Scene(root, 300, 100));
        primaryStage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
	}
}
