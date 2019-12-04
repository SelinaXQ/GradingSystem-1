package application;
	
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import db.Operations;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pojo.Course;
import uitable.StudentInfo;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Grading extends Application {
	
	ArrayList<StudentInfo> studentList = new ArrayList<>();
	Operations operations = new Operations();
	Course course = operations.getCourseInfo("1");
	
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("grading.fxml"));
        primaryStage.setTitle("current semester");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        
        
        
	}
	
	@FXML
	private void handleOverViewBtn() throws IOException {
		CourseOverview overview = new CourseOverview();
		overview.start(new Stage());
		
	}
	
	@FXML
	private void importExcelBtn() throws IOException, InvalidFormatException{
		FileChooser fileChooser = new FileChooser();
		System.out.println("importing...");
		fileChooser.setTitle("Open Resource File");
		Stage selectFile = new Stage();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS", "*.xls"), 
                new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
		File file = fileChooser.showOpenDialog(selectFile);
		if(file != null) {
			System.out.println("import success!");
			
			studentList = (ArrayList<StudentInfo>) ExcelUtil.getStudentList(file);
			
			//test
			int size = studentList.size();
			System.out.println("student size: " + size);
			for(int i = 0; i < size; i++) {
				StudentInfo stu = studentList.get(i);
				System.out.println(stu.toString());
				
			}
			
			//add to sql
			operations.saveOpUpdateStudentsInfo(studentList, course);
			
		}
	}
	
	@FXML
	private void editStudentBtn() throws IOException {
		StudentManagement stuManagement = new StudentManagement();
		stuManagement.start(new Stage());
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
