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
import javafx.stage.Stage;

public class OverviewController implements Initializable {
	@FXML
	private Button closeCourse;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

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
}
