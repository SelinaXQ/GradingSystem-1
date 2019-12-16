package application;

import db.Operations;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import pojo.Course;
import pojo.DetailedCriteria;
import pojo.GeneralCriteria;
import uitable.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class OverviewController implements Initializable {
	Operations operations = new Operations();
	CourseHistoryController courseHistory = new CourseHistoryController(); 
	Course course = courseHistory.getCourse();
	//System.out.println(course.toString());
	String courseid = course.getCID();
	//GradingController gradingController = new GradingController();
	ObservableList<Overview> overviewData = FXCollections.observableArrayList();
	ArrayList<ColumnCoexist> columnCoexists = new ArrayList<>();

	ArrayList<TableColumn<Overview,String>> columnList = new ArrayList<TableColumn<Overview, String>>() ;
	//table overview
	@FXML private TableView<Overview> tableView;
	@FXML private TableColumn<Overview, String> BUIDColumn;
	@FXML private TableColumn<Overview, String> firstNameColumn;
	@FXML private TableColumn<Overview, String> middleNameColumn;
	@FXML private TableColumn<Overview, String> lastNameColumn;
	@FXML private TableColumn<Overview, String> compositeColumn;
	@FXML private  TableColumn<Overview,String> gradeColumn;
	// general criteria
	ArrayList<GeneralCriteria> generalArr = operations.getGeneralCriteriasByCourseID(courseid, false);

	GeneralCriteria generalCur = new GeneralCriteria();
	//detailed criteria
	ObservableList<DetailedCriteria> detailedCriteria = FXCollections.observableArrayList();
	DetailedCriteria detailedCur = new DetailedCriteria();

	/*
	 * initialize table
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BUIDColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("BUID"));

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("firstName"));
		middleNameColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("middleName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("lastName"));
		compositeColumn .setCellValueFactory(new PropertyValueFactory<Overview, String>("total"));
		gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		ArrayList <GeneralCriteria> generalCriteria =operations.getGeneralCriteriasByCourseID(courseid,false);
		for(GeneralCriteria i: generalCriteria ){
			TableColumn<Overview,String> gColumn = new TableColumn<Overview,String>();
			gColumn.setText(i.getGenCriType());
			gColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<Overview, String> param) {
					ArrayList<GeneralGrade> gcS = param.getValue().getGcScores();
					double gc=0.0;
					for(GeneralGrade g:gcS){
						if (g.getgCriID().equals(i.getgCriID())) gc=g.getScore();
						break;
					}
					return new SimpleStringProperty(new DoubleStringConverter().toString(gc));
				}
			});
			tableView.getColumns().add(gColumn);
			TableColumn<Overview,String> g1Column = new TableColumn<Overview,String>();
			g1Column.setText(i.getGenCriType());
			g1Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<Overview, String> param) {
					ArrayList<GeneralGrade> gcS = param.getValue().getGcScores();
					double gc=0.0;
					for(GeneralGrade g:gcS){
						if (g.getgCriID().equals(i.getgCriID())) gc=g.getScore();
						break;
					}
					return new SimpleStringProperty(new DoubleStringConverter().toString(gc));
				}
			});
			ArrayList<DetailedCriteria> dc =  operations.getDetailedCriteriasByGenerCriID(i.getgCriID(),false);
			for(DetailedCriteria j:dc){
				TableColumn<Overview,String> dColumn = new TableColumn<Overview,String>();
				dColumn.setText(j.getDeCriType());
				dColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Overview, String> param) {
						ArrayList<HashMap<String, DetailedGrade>> dcS = param.getValue().getDcs();
						double dc=0.0;
						for( HashMap<String, DetailedGrade> d:dcS){
							if (d.get(i.getGenCriType()).getdCriID().equals(j.getdCriID())) dc=d.get(i.getgCriID()).getScore();
							break;
						}
						return new SimpleStringProperty(new DoubleStringConverter().toString(dc));
					}
				});
				//dColumn.setVisible(false);
				g1Column.getColumns().add(dColumn);
			}
			g1Column.setVisible(false);
			tableView.getColumns().add(g1Column);
			columnCoexists.add(new ColumnCoexist(gColumn,g1Column));
			//columnList.add(gColumn);
		}

		tableView.setItems(getOverview());
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	@FXML
	public void changeGradingColumnEvent(TableColumn.CellEditEvent edittedCell){
		Overview overviewSelected = tableView.getSelectionModel().getSelectedItem();
		int index = tableView.getSelectionModel().getFocusedIndex();
		overviewSelected.setGrade(edittedCell.getNewValue().toString());
		overviewData.get(index).setGrade(edittedCell.getNewValue().toString());
		//operations.getCourseStudents
	}

	public ObservableList<Overview> getOverview(){
		System.out.println(course.toString());

//		course = operations.getCourseInfo("1");
		ArrayList<Overview> overviewList = new ArrayList<>();
		overviewList.addAll( operations.getOverviewInfoByCourseID(course));
		System.out.println("overviewSize:" + overviewList.size());
		for(int i = 0; i < overviewList.size(); i++) {
			overviewData.add(overviewList.get(i));
		}
		System.out.println(overviewData.size());
		return overviewData;
	}
	@FXML
	public void closeCourseButton(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CloseCourse.fxml"));

		Stage stage = new Stage();
		Scene scene;
		try {
			scene = new Scene(loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.show();
	}
	@FXML
	public void changeModeButtonClicked(ActionEvent event) throws IOException{
		for(ColumnCoexist i:columnCoexists)
			i.switchVisible();
	}
	@FXML
	public void curveButton(ActionEvent event) throws IOException{

	}
	@FXML
	public void statisticButton(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistics.fxml"));

		Stage stage = new Stage();
		Scene scene;
		try {
			scene = new Scene(loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		stage.show();

	}
}
