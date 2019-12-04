package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import uitable.StudentInfo;

public class studentManagementController implements Initializable{

	GradingController gradingController = new GradingController();
	ObservableList<StudentInfo> studentData = FXCollections.observableArrayList();
	
	@FXML private TableView<StudentInfo> tableView;
	@FXML private TableColumn<StudentInfo, String> BUIDColumn;
	@FXML private TableColumn<StudentInfo, String> firstNameColumn;
	@FXML private TableColumn<StudentInfo, String> middleNameColumn;
	@FXML private TableColumn<StudentInfo, String> lastNameColumn;
	@FXML private TableColumn<StudentInfo, String> conditionColumn;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		BUIDColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("BUID"));
		
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("firstName"));
		middleNameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("middleName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("lastName"));
		conditionColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("condition"));
		
		studentData = gradingController.getStudentData();
		System.out.println("studentData size:" + studentData.size());
		for(int i = 0; i<studentData.size(); i++) {
			System.out.println(studentData.toString());
		}
		tableView.setItems(getStudent());
		
		tableView.setEditable(true);
	}
	
	public ObservableList<StudentInfo>  getStudent()
    {
        ObservableList<StudentInfo> student = FXCollections.observableArrayList();
        student.add(new StudentInfo("U96796201","Qian","", "Xiang", ""));
        
        return student;
    }

}
