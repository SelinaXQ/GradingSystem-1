package application;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import db.Operations;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import pojo.Semester;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Login extends Application implements Initializable {

	Operations operations = new Operations();
	Semester semester = new Semester("Fall 2019");
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		launch(args);
	}

	@FXML
	public void cancelButton(ActionEvent event) {

		Stage main = (Stage) login.getScene().getWindow();
		main.close();
	}

	@FXML
	public void loginButton(ActionEvent event) {

		String user = userName.getText().trim();
		String pwd = password.getText().trim();
		boolean flag = operations.login(user, pwd);

		if (flag == true) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseHome.fxml"));
			// SemesterController controller = new SemesterController();
			// loader.setController(this);
			Stage courseHome = new Stage();
			courseHome.setTitle("Current Courses");
			Scene scene;
			try {
				scene = new Scene(loader.load());
				courseHome.setScene(scene);
				// initData();
			} catch (IOException e) {
				e.printStackTrace();
			}

			courseHome.show();

			Stage main = (Stage) login.getScene().getWindow();
			main.close();
		} else {
			Alert info = new Alert(Alert.AlertType.ERROR);
		    Pane pane = new Pane();
		    info.setContentText("Username: cpk  Password: cpk");
		    info.getDialogPane().setExpandableContent(pane);
		    info.show();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public String getSemester() {
		return semester.toString();
	}
	
	/*
	 * public void initData() { semesterText.setText(semester.toString());
	 * 
	 * }
	 */
}
