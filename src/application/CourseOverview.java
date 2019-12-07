package application;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class CourseOverview extends Application{
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("courseOverview.fxml"));
        primaryStage.setTitle("Course Overview");
        primaryStage.setScene(new Scene(root, 800, 530));
        primaryStage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
	}
}
