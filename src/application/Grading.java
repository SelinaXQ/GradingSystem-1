package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class Grading extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("grading.fxml"));
        primaryStage.setTitle("current semester");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        
	}
	
	@FXML
	private void handleOverViewBtn() throws IOException {
		CourseOverview overview = new CourseOverview();
		overview.start(new Stage());
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
