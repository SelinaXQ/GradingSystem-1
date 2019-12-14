package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojo.Course;

public class CourseHistoryController implements Initializable {

	@FXML
	private Button overview;
	@FXML
	private Button back;
	@FXML
	private TableView<Course> coursesTextView;
	@FXML
	private TableColumn<Course, String> courseIDColumn;
	@FXML
	private TableColumn<Course, String> collegeColumn;
	@FXML
	private TableColumn<Course, String> stateColumn;
	
	private Course course;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void clickCourse() {
		course = coursesTextView.getSelectionModel().getSelectedItem();
	}

}
