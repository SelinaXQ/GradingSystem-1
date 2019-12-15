package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.Operations;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.Course;
import pojo.Semester;

public class CourseHistoryController implements Initializable {
	@FXML
	private Button overview;
	@FXML
	private Button cancel;
	@FXML
	private TableView<Course> coursesTextView;
	@FXML
	private TableColumn<Course, String> courseIDColumn;
	@FXML
	private TableColumn<Course, String> collegeColumn;
	@FXML
	private TableColumn<Course, String> stateColumn;
	@FXML
	private ChoiceBox<Semester> semestersList = new ChoiceBox<Semester>();

	private ObservableList<Course> coursesList = FXCollections.observableArrayList();

	private ArrayList<Semester> semesters;

	private ArrayList<Course> courses;

	private static Course course = new Course();

	private Operations operations;

	private Semester curSemester;
	
	private static boolean ifFromHistory = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		operations = new Operations();
		semesters = operations.getSemesters();
		ObservableList<Semester> semsetersStr = FXCollections.observableArrayList(semesters);
		semestersList.setItems(semsetersStr);
		semestersList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue arg0, Number arg1, Number arg2) {
				coursesList.clear();
				courseIDColumn.setCellValueFactory(null);
				coursesTextView.setItems(null);

				curSemester = semesters.get(arg2.intValue());
				courses = operations.getCoursesBySemester(curSemester.toString());
				if (courses.size() != 0) {
					courseIDColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("cName"));
					coursesTextView.setItems(getCourseName());
				}
			}
		});
		semestersList.getSelectionModel().selectFirst();
	}

	public ObservableList<Course> getCourseName() {

		for (int i = 0; i < courses.size(); i++) {
			coursesList.add(courses.get(i));
		}
		return coursesList;
	}

	@FXML
	public void clickCourse() {
		course = coursesTextView.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	public void overviewButton(ActionEvent event) {
		ifFromHistory = true;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseOverview.fxml"));
		Stage overview = new Stage();
		overview.setTitle("Ovewview Course");
		Scene scene;
		try {
			scene = new Scene(loader.load());
			overview.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		overview.show();
	}
	
	@FXML
	public void canccelButton(ActionEvent event) {
		Stage main = (Stage) cancel.getScene().getWindow();
		main.close();
	}

	public Course getCourse() {
		return course;
	}
	
	public boolean getIfFromHistory() {
		return ifFromHistory;
	}
	
}
