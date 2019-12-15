package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Course;

public class OverviewController implements Initializable {
	

	@FXML
	private TextField curveValue;
	@FXML
	private Button closeCourse;
	@FXML
	private Button curve;
	@FXML
	private Button statistic;
	
	private Course course = new Course();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CourseHistoryController cHistoryController = new CourseHistoryController();
		boolean ifFromHistory = cHistoryController.getIfFromHistory();
		if(ifFromHistory == true) {
			course = cHistoryController.getCourse();
			ifFromHistory = false;
			closeCourse.setVisible(false);
			curve.setVisible(false);
			curveValue.setVisible(false);
			statistic.setVisible(false);
		}
		else {
			// when you open this window from grading.xml
		}

	}

	@FXML
	public void closeCourseButton(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CloseCourse.fxml"));
		// loader.setController(this);
		Stage closeCourse = new Stage();
		// closeCourse.setTitle("Close Courses");
		Scene scene;
		try {
			scene = new Scene(loader.load());
			closeCourse.setScene(scene);
			// initData();
		} catch (IOException e) {
			e.printStackTrace();
		}

		closeCourse.show();
	}
	
	@FXML
	public void curveButton(ActionEvent event) {
		
	}
	
	@FXML
	public void statisticButton(ActionEvent event) {
		
	}
}
