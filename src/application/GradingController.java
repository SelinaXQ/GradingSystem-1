package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import pojo.Course;
import pojo.DetailedCriteria;
import pojo.GeneralCriteria;
import uitable.GiveDetailedGrades;
import uitable.StudentInfo;


public class GradingController implements Initializable{

	
	Operations operations = new Operations();
	Course course = operations.getCourseInfo("1");
	String courseid = course.getcID();
	//student info
	ObservableList<StudentInfo> studentData = FXCollections.observableArrayList();
	ArrayList<StudentInfo> studentList = new ArrayList<>();
	
	// general criteria
	ObservableList<GeneralCriteria> generalCriteria = FXCollections.observableArrayList();
	ArrayList<GeneralCriteria> generalArr = operations.getGeneralCriteriasByCourseID(courseid, false);
	GeneralCriteria generalCur = new GeneralCriteria();
	//detailed criteria
	ObservableList<DetailedCriteria> detailedCriteria = FXCollections.observableArrayList();
	
	//give grade
	ObservableList<GiveDetailedGrades> grade = FXCollections.observableArrayList();
	
	//table general
	@FXML private TableView<GeneralCriteria> generalTableView;
	@FXML private TableColumn<GeneralCriteria, String> generalTypeColumn;
	@FXML private TableColumn<GeneralCriteria, Double> generalPercentageColumn;
	
	//table detailed
	@FXML private TableView<DetailedCriteria> detailedTableView;
	@FXML private TableColumn<DetailedCriteria, String> detailedTypeColumn;
	@FXML private TableColumn<DetailedCriteria, Double> detailedPercentageColumn;
	@FXML private TableColumn<DetailedCriteria, Double> detailedTotalScoreColumn;
	
	//table grade
	@FXML private TableView<GiveDetailedGrades> gradeTableView;
	@FXML private TableColumn<GiveDetailedGrades, String> BUIDColumn;
	@FXML private TableColumn<GiveDetailedGrades, String> firstNameColumn;
	@FXML private TableColumn<GiveDetailedGrades, String> middleNameColumn;
	@FXML private TableColumn<GiveDetailedGrades, String> lastNameColumn;
	@FXML private TableColumn<GiveDetailedGrades, Double> gradeScoreColumn;
	
	/*
	 * initialize table
	 */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//general table
		generalTypeColumn.setCellValueFactory(new PropertyValueFactory<GeneralCriteria, String>("genCriType"));
		generalPercentageColumn.setCellValueFactory(new PropertyValueFactory<GeneralCriteria, Double>("genCriPer"));
		generalTableView.setItems(getGeneralCriteria());
		generalTableView.setEditable(true);
		generalTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		generalPercentageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
		//detailed table
		detailedTypeColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, String>("deCriType"));
		detailedPercentageColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, Double>("deCriPer"));
		detailedTotalScoreColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, Double>("totalScore"));
		
//		detailedTableView.setItems(getDetailedCriteria(generalArr));
		
		detailedTableView.setEditable(true);
		detailedTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		detailedPercentageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		detailedTotalScoreColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
		//grade table
		BUIDColumn.setCellValueFactory(new PropertyValueFactory<GiveDetailedGrades, String>("BUID"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<GiveDetailedGrades, String>("fName"));
		middleNameColumn.setCellValueFactory(new PropertyValueFactory<GiveDetailedGrades, String>("mName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<GiveDetailedGrades, String>("lName"));
		gradeScoreColumn.setCellValueFactory(new PropertyValueFactory<GiveDetailedGrades, Double>("score"));
		gradeTableView.setEditable(true);
		gradeScoreColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
	}
	
	public void userClickedOnGeneralTable() {
		System.out.println("general clicked!");
		detailedCriteria = FXCollections.observableArrayList();
		
		generalCur = generalTableView.getSelectionModel().getSelectedItem();
		
		ArrayList<DetailedCriteria> detailedArr = new ArrayList<>();
		detailedArr = operations.getDetailedCriteriasByGenerCriID(generalCur.getgCriID(), false);
		
		for(int i = 0; i < detailedArr.size(); i++) {
			detailedCriteria.add(detailedArr.get(i));
		}
		detailedTableView.setItems(detailedCriteria);
	}
	
	public void userClickOnDetailedTable() {
		System.out.println("detailed clicked!");
		grade = FXCollections.observableArrayList();
		DetailedCriteria detailedCur = detailedTableView.getSelectionModel().getSelectedItem();
		ArrayList<GiveDetailedGrades> gradeArr = operations.getStudentsDetailedGrades(course, detailedCur);
		System.out.println("grade size:" + gradeArr.size());
		for(int i = 0; i < gradeArr.size(); i++) {
			grade.add(gradeArr.get(i));
		}
		gradeTableView.setItems(grade);
		
	}
	
	@FXML
	public void changeGeneralTypeCellEvent(CellEditEvent edittedCell) {
		GeneralCriteria generalCriteriaSelected = generalTableView.getSelectionModel().getSelectedItem();
		int index = generalTableView.getSelectionModel().getFocusedIndex();
		
		generalCriteriaSelected.setGenCriType(edittedCell.getNewValue().toString());
		
		generalCriteria.get(index).setGenCriType(edittedCell.getNewValue().toString());
		
		
	}
	
	@FXML void changeGeneralPercentageCellEvent(CellEditEvent edittedCell) {
		GeneralCriteria generalCriteriaSelected = generalTableView.getSelectionModel().getSelectedItem();
		int index = generalTableView.getSelectionModel().getFocusedIndex();
		
		generalCriteriaSelected.setGenCriPer(Double.valueOf(edittedCell.getNewValue().toString()));
		generalCriteria.get(index).setGenCriPer(Double.valueOf(edittedCell.getNewValue().toString()));
		
	}
	
	@FXML
	public void changeDetailedTypeCellEvent(CellEditEvent edittedCell) {
		DetailedCriteria detailedCriteriaSelected = detailedTableView.getSelectionModel().getSelectedItem();
		int index = detailedTableView.getSelectionModel().getFocusedIndex();
		
		detailedCriteriaSelected.setDeCriType(edittedCell.getNewValue().toString());
		
		detailedCriteria.get(index).setDeCriType(edittedCell.getNewValue().toString());
	}
	
	@FXML
	public void changeDetailedPercentageCellEvent(CellEditEvent edittedCell) {
		DetailedCriteria detailedCriteriaSelected = detailedTableView.getSelectionModel().getSelectedItem();
		int index = detailedTableView.getSelectionModel().getFocusedIndex();
		
		detailedCriteriaSelected.setDeCriPer(Double.valueOf(edittedCell.getNewValue().toString()));
		
		detailedCriteria.get(index).setDeCriPer(Double.valueOf(edittedCell.getNewValue().toString()));
	}
	
	@FXML
	public void changeDetailedScoreCellEvent(CellEditEvent edittedCell) {
		DetailedCriteria detailedCriteriaSelected = detailedTableView.getSelectionModel().getSelectedItem();
		int index = detailedTableView.getSelectionModel().getFocusedIndex();
		
		detailedCriteriaSelected.setTotalScore(Double.valueOf(edittedCell.getNewValue().toString()));
		
		detailedCriteria.get(index).setTotalScore(Double.valueOf(edittedCell.getNewValue().toString()));
	}
	
	/*
	 * save general criteria
	 */
	
	@FXML
	public void saveGeneralCriteriaButton(ActionEvent event) {
		
//		saveGeneralCriterias
		System.out.println("Button clicked");
		System.out.println("general size:" + generalCriteria.size());
		
		ArrayList<GeneralCriteria> temp = new ArrayList<>();
		for(int i = 0; i < generalCriteria.size(); i++) {
			GeneralCriteria tempGeneral = generalCriteria.get(i);
			temp.add(tempGeneral);
		}
		
		if(operations.saveGeneralCriterias(temp, false)) {
			System.out.println("Save successfully");
			generalTableView.refresh();
			
		}else {
			System.out.println("Added up should be 100%!!");
			//refresh
			generalCriteria = FXCollections.observableArrayList();
			generalTableView.setItems(getGeneralCriteria());
			generalTableView.refresh();
		}
		
//		//save before change
//		ObservableList<GeneralCriteria> generalCriteriaTemp = FXCollections.observableArrayList();
//		ArrayList<GeneralCriteria> generalArrTemp = operations.getGeneralCriteriasByCourseID(courseid, false);
//		for(int i = 0; i<generalArrTemp.size(); i++) {
//			generalCriteriaTemp.add(generalArrTemp.get(i));
//		}
//		
//		
//		//save current and delete in database
//		ArrayList<GeneralCriteria> temp = new ArrayList<>();
//		for(int i = 0; i < generalCriteria.size(); i++) {
//			GeneralCriteria tempGenaral = generalCriteria.get(i);
//			operations.deleteGeneralCriteria(generalCriteria.get(i), false);
//			tempGenaral.setcID(null);
//			temp.add(tempGenaral);	
//		}
//		
//		//try to save 
//		
//		if(operations.saveGeneralCriterias(temp, false)) {
//			//refresh table
//			generalCriteria = FXCollections.observableArrayList();
//			generalTableView.setItems(getGeneralCriteria());
//			System.out.println("Save successfully");
////			generalTableView.refresh();
//			
//		}else {
//			System.out.println("Added up should be 100%!!");
//			//refresh
//			//add back to database
//			operations.saveGeneralCriterias(generalArrTemp, false);
//			generalCriteria = FXCollections.observableArrayList();
//			for(int i = 0; i<generalCriteriaTemp.size(); i++) {
//				generalCriteria.add(generalCriteriaTemp.get(i));
//			}
//			generalTableView.setItems(generalCriteria);
//		}
		
	}
	
	/*
	 * save detailed criteria
	 */
	
	@FXML
	public void saveDetailedCriteriaButton(ActionEvent event) {
//		saveDetailedCriterias
		System.out.println("Button clicked");
		System.out.println("detailed size:" + detailedCriteria.size());
		ArrayList<DetailedCriteria> temp = new ArrayList<>();
		for(int i = 0; i < detailedCriteria.size(); i++) {
			DetailedCriteria tempDetailed = detailedCriteria.get(i);
			temp.add(tempDetailed);
		}
		
		if(operations.saveDetailedCriterias(temp, false)) {
			System.out.println("Save successfully");
			detailedTableView.refresh();
			
		}else {
			System.out.println("Added up should be 100%!!");
			//refresh
			detailedCriteria = FXCollections.observableArrayList();
			ArrayList<DetailedCriteria> detailedArr = new ArrayList<>();
			detailedArr = operations.getDetailedCriteriasByGenerCriID(generalCur.getgCriID(), false);
			
			for(int i = 0; i < detailedArr.size(); i++) {
				detailedCriteria.add(detailedArr.get(i));
			}
			detailedTableView.setItems(detailedCriteria);
			detailedTableView.refresh();
		}
	}
	
	
	
	/*
	 * Edit student
	 */
	
	@FXML
	public void editStudentButtonPushed(ActionEvent event) throws IOException {
		Parent studentmManagementParent = FXMLLoader.load(getClass().getResource("StudentManagement.fxml"));
		Scene studentManagementScene = new Scene(studentmManagementParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(studentManagementScene);
		window.show();
		
	}
	
	/*
	 * Import student
	 */
	
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
				System.out.println(stu.toString());
				
			}
			
			//add to sql
//			course = operations.getCourseInfo("1");
			operations.saveOpUpdateStudentsInfo(studentList, course);
			
			studentList = operations.getStudentsByCourseID(course);
			for(int i = 0; i<studentList.size(); i++) {
				studentData.add(studentList.get(i));
			}
			
		}
	}
	

	/*
	 * Get student data by course
	 */
	
	public ObservableList<StudentInfo> getStudentData(){
		System.out.println("get data!");

//		course = operations.getCourseInfo("1");
		studentList = operations.getStudentsByCourseID(course);
		System.out.println("studentSize:" + studentList.size());
		for(int i = 0; i < studentList.size(); i++) {
			studentData.add(studentList.get(i));
		}
		System.out.println(studentData.size());
		return studentData;
	}

	
	/*
	 * Get general criteria
	 */
	
	public ObservableList<GeneralCriteria> getGeneralCriteria(){
		generalArr = operations.getGeneralCriteriasByCourseID(courseid, false);
		for(int i = 0; i < generalArr.size(); i++) {
			generalCriteria.add(generalArr.get(i));
		}
		return generalCriteria;
	}
	
	/*
	 * Get detailed criteria
	 */
	
	public ObservableList<DetailedCriteria> getDetailedCriteria(ArrayList<GeneralCriteria> generalArr){
		ArrayList<DetailedCriteria> detailedArr = new ArrayList<>();
		for(int i = 0; i<generalArr.size(); i++){
			detailedArr = operations.getDetailedCriteriasByGenerCriID(generalArr.get(i).getgCriID(), false);
			for(int j = 0; j < detailedArr.size(); j++) {
				detailedCriteria.add(detailedArr.get(j));
			}
		}		
		return detailedCriteria;
	}
	
}
