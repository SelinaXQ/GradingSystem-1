package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojo.Course;

public class CourseInfoController implements Initializable {
	
	@FXML
	private Label semesterText;
	@FXML
	private TableView<Course> coursesTextView;
	@FXML
	private TableColumn<Course, String> courseIDColumn;
	@FXML
	private Button saveDetailedCriteria;
	@FXML
	private Button deleteDetailedCriteria;
	@FXML
	private Button addDetailedCriteria;
	@FXML
	private Button importFromTemplate;
	@FXML
	private Button saveAsTemplate;
	@FXML
	private Button saveGeneralCriteria;
	@FXML
	private Button deleteGeneralCriteria;
	@FXML
	private Button addGeneralCriteria;
	@FXML
	private Button saveCourseInfo;
	
	private Course course;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CourseHomeController courseHomeController = new CourseHomeController();
		boolean addOrEdit = courseHomeController.getAddOrEdit();
		if (addOrEdit == true) {
			course = new Course();
		} else {
			course = courseHomeController.getCourse();
		}
		
	}
	
	@FXML
	public void saveGeneralCriteriaButton(ActionEvent event) {
		
	}

	@FXML
	public void addGeneralCriteriaButton(ActionEvent event) {
		
	}
	
	@FXML
	public void deleteGeneralCriteriaButton(ActionEvent event) {
		
	}
	
	@FXML
	public void importFromTemplateButton(ActionEvent event) {
		
	}
	
	@FXML
	public void saveAsTemplateButton(ActionEvent event) {
		
	}
	
	@FXML
	public void saveDetailedCriteriaButton(ActionEvent event) {
		
	}

	@FXML
	public void addDetailedCriteriaButton(ActionEvent event) {
		
	}
	
	@FXML
	public void deleteDetailedCriteriaButton(ActionEvent event) {
		
	}
	
	@FXML
	public void saveCourseInfoButton(ActionEvent event) {
		
	}
}
