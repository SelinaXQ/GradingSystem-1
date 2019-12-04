package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.Operations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pojo.Course;
import uitable.StudentInfo;


public class GradingController implements Initializable{

	
	Operations operations = new Operations();
	Course course = new Course();
	
	ObservableList<StudentInfo> studentData = FXCollections.observableArrayList();
	ArrayList<StudentInfo> studentList = new ArrayList<>();
	@FXML
	public void editStudentButtonPushed(ActionEvent event) throws IOException {
		Parent studentmManagementParent = FXMLLoader.load(getClass().getResource("studentManagement.fxml"));
		Scene studentManagementScene = new Scene(studentmManagementParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(studentManagementScene);
		window.show();
		
	}
	
	
	@FXML
	public void importStudentButtonPushed(ActionEvent event) throws IOException {
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
				//add into ObservableList
//				studentData.add(stu);
				System.out.println(stu.toString());
				
			}
			
			//add to sql
			operations.saveOpUpdateStudentsInfo(studentList, course);
			
			studentList = operations.getStudentsByCourseID(course);
			for(int i = 0; i<studentList.size(); i++) {
				studentData.add(studentList.get(i));
			}
			
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		course = operations.getCourseInfo("1");
		studentList = operations.getStudentsByCourseID(course);
		System.out.println("studentSize:" + studentList.size());
		for(int i = 0; i < studentList.size(); i++) {
			studentData.add(studentList.get(i));
		}
	}
	
	public ObservableList<StudentInfo> getStudentData(){
		System.out.println("get data!");
		return studentData;
	}

	
}
