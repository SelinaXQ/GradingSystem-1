package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pojo.Course;
import uitable.StudentInfo;

public class StudentManagementController implements Initializable{

	GradingController gradingController = new GradingController();
	ObservableList<StudentInfo> studentData = FXCollections.observableArrayList();
	Operations operations = new Operations();
	Course course = new Course();
	
	//table
	@FXML private TableView<StudentInfo> tableView;
	@FXML private TableColumn<StudentInfo, String> BUIDColumn;
	@FXML private TableColumn<StudentInfo, String> firstNameColumn;
	@FXML private TableColumn<StudentInfo, String> middleNameColumn;
	@FXML private TableColumn<StudentInfo, String> lastNameColumn;
	@FXML private TableColumn<StudentInfo, String> conditionColumn;
	
	
	//add new students
	@FXML private TextField BUIDTextField;
	@FXML private TextField firstNameTextField;
	@FXML private TextField middleNameTextField;
	@FXML private TextField lastNameTextField;
	@FXML private TextField conditionTextField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		BUIDColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("BUID"));
		
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("firstName"));
		middleNameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("middleName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("lastName"));
		conditionColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("condition"));
		

		tableView.setItems(getStudent());
		
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		conditionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

	}
	
	@FXML
	public void changeConditionCellEvent(CellEditEvent edittedCell) {
		StudentInfo studentSelected = tableView.getSelectionModel().getSelectedItem();
		int index = tableView.getSelectionModel().getSelectedIndex();
		
		studentSelected.setCondition(edittedCell.getNewValue().toString());
		studentData.get(index).setCondition(edittedCell.getNewValue().toString());
		
	}

	@FXML
	public void saveStudentConditionButton(ActionEvent event) {
		ArrayList<StudentInfo> studentInfo = new ArrayList<>();
		for(int i = 0; i<studentData.size(); i++) {
			studentInfo.add(studentData.get(i));
		}
		course = operations.getCourseInfo("1");
		
		operations.updateStudentInfo(studentInfo, course);
		
		
	}
	
	@FXML
	public void addStudentButton(ActionEvent event) {
		StudentInfo newStudent = new StudentInfo(
				BUIDTextField.getText(),
				firstNameTextField.getText(),
				middleNameTextField.getText(),
				lastNameTextField.getText(),
				conditionTextField.getText()
				);
		tableView.getItems().add(newStudent);
		//studentData.add(newStudent);
		
		
		ArrayList<StudentInfo> studentInfo = new ArrayList<>();
		for(int i = 0; i<studentData.size(); i++) {
			studentInfo.add(studentData.get(i));
		}
		course = operations.getCourseInfo("1");
		
		operations.saveStudentsInfo(studentInfo, course);
		
	}
	
	@FXML
	public void deleteButton() {

		course = operations.getCourseInfo("1");
		
		ObservableList<StudentInfo> selectedRows, allStudent;
        allStudent = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for (StudentInfo student: selectedRows)
        {
        	allStudent.remove(student);
        	System.out.println(student);
    		operations.deleteStudentInfo(student, course); 
        }
        
        ArrayList<StudentInfo> studentInfo = new ArrayList<>();
		for(int i = 0; i<allStudent.size(); i++) {
			studentInfo.add(allStudent.get(i));
		}
		  
	}
	
	@FXML
	public void backButton(ActionEvent event) throws IOException {
		Parent manageStudentParent = FXMLLoader.load(getClass().getResource("Grading.fxml"));
		Scene manageStudentScene = new Scene(manageStudentParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(manageStudentScene);
		window.show();
	}
	
	public ObservableList<StudentInfo>  getStudent()
    {
		
		studentData = gradingController.getStudentData();
//        ObservableList<StudentInfo> student = FXCollections.observableArrayList();
//        student.add(new StudentInfo("U96796201","Qian","", "Xiang", ""));
//        
        return studentData;
    }

}
