package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ImportTemplate extends Application{
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("ImportTemplate.fxml"));
        primaryStage.setTitle("Import Template");
        primaryStage.setScene(new Scene(root, 276, 338));
        primaryStage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
	}
}
