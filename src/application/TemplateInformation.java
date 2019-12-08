package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TemplateInformation extends Application{
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("TemplateInformation.fxml"));
        primaryStage.setTitle("TemplateInformation:");
        primaryStage.setScene(new Scene(root, 460, 590));
        primaryStage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
	}
}