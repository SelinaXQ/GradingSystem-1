package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pojo.Course;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.Operations;

public class CloseCourseController  implements Initializable {
	private Operations operations = new Operations();
	@FXML
	private Button close;
	
	@FXML
	private Button cancel;

	private Course course;

	private CourseHomeController controller = new CourseHomeController();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		course = controller.getCourse();
	}

	@FXML
	public void closeButton(ActionEvent event) throws IOException {
		operations.closeCourse(course);
		Stage main = (Stage) close.getScene().getWindow();
		main.close();
	}

	@FXML
	public void cancelButton(ActionEvent event) throws IOException {
		Stage main = (Stage) cancel.getScene().getWindow();
		main.close();
	}
}
