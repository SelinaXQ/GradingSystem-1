package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Table;

import db.Operations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.Course;
import pojo.GeneralCriteria;
import uitable.GiveDetailedGrades;

public class CourseHomeController implements Initializable {

	@FXML
	private Label semesterText;
	@FXML
	private TableView<Course> coursesTextView;
	@FXML
	private TableColumn<Course, String> courseIDColumn;
	@FXML
	private Button add;
	@FXML
	private Button grading;
	@FXML
	private Button edit;
	@FXML
	private Button viewHistory;

	private boolean addOrEdit = true; // T = add F = edit

	private Operations operations = new Operations();
	private ArrayList<Course> courses;

	private ObservableList<Course> coursesList = FXCollections.observableArrayList();
	private Course course;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Login loginController = new Login();
		String currentSemester = loginController.getSemester();
		semesterText.setText(currentSemester);

		courses = operations.getCoursesBySemester(currentSemester);
		courseIDColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("cName"));
		System.out.println(courses.size());
		coursesTextView.setItems(getCourseName());

	}
	
	public ObservableList<Course> getCourseName(){
		
		for(int i = 0; i < courses.size(); i++) {
			coursesList.add(courses.get(i));
		}
		return coursesList;
	}
	
	
	public void clickCourse() {
		course = coursesTextView.getSelectionModel().getSelectedItem();
		System.out.println(course);
	}
	
	@FXML
	public void addCourseButton(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseInformation.fxml"));
		addOrEdit = true;
		Stage stage = new Stage();
		Scene scene;
		try {
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.setTitle("Add Course Information & Criteria");
		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.show();

		/*
		 * Stage main = (Stage) add.getScene().getWindow(); main.close();
		 */
	}

	@FXML
	public void goToGradingButton(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Grading.fxml"));

		Stage stage = new Stage();
		Scene scene;
		try {
			scene = new Scene(loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.show();

		/*
		 * Stage main = (Stage) grading.getScene().getWindow(); main.close();
		 */
	}

	@FXML
	public void editCourseButton(ActionEvent event) {
		addOrEdit = false;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseInformation.fxml"));

		Stage stage = new Stage();
		Scene scene;
		try {
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.setTitle("Edit Course Information & Criteria");
		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.show();

		/*
		 * Stage main = (Stage) edit.getScene().getWindow(); main.close();
		 */
	}

	@FXML
	public void viewCourseHistoryButton(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseHistory.fxml"));

		Stage stage = new Stage();
		Scene scene;
		try {
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.setTitle("History Courses");
		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.show();

		/*
		 * Stage main = (Stage) viewHistory.getScene().getWindow(); main.close();
		 */
	}

	public Course getCourse() {
		return course;

	}

	public boolean getAddOrEdit() {
		return addOrEdit;
	}

}
