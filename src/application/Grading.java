package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pojo.*;
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
	
	@FXML
	private void importExcel() throws IOException{
		FileChooser fileChooser = new FileChooser();
		System.out.println("importing...");
		fileChooser.setTitle("Open Resource File");
		Stage selectFile = new Stage();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS", "*.xls"), 
                new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
		File file = fileChooser.showOpenDialog(selectFile);
		if(file != null) {
			System.out.println("import success!");
			
			
			
		}
		
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
