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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	static boolean changable;
	static Course course;
	Operations operations = new Operations();
	// CourseHistoryController courseHistory = new CourseHistoryController();
	// Course course = courseHistory.getCourse();
	// System.out.println(course.toString());
	String courseid = course.getCID();
	static double curveVal = 0.0;

	@FXML
	Label courseLabel;

	// GradingController gradingController = new GradingController();
	static ObservableList<Overview> overviewData = FXCollections.observableArrayList();
	ArrayList<ColumnCoexist> columnCoexists = new ArrayList<>();

	ArrayList<TableColumn<Overview, String>> columnList = new ArrayList<TableColumn<Overview, String>>();
	// table overview
	@FXML
	private TableView<Overview> tableView;
	@FXML
	private TableColumn<Overview, String> BUIDColumn;
	@FXML
	private TableColumn<Overview, String> firstNameColumn;
	@FXML
	private TableColumn<Overview, String> middleNameColumn;
	@FXML
	private TableColumn<Overview, String> lastNameColumn;
	@FXML
	private TableColumn<Overview, String> compositeColumn;
	@FXML
	private TableColumn<Overview, String> gradeColumn;
	@FXML
	private TextField curveValue;
	// general criteria
	ArrayList<GeneralCriteria> generalArr = operations.getGeneralCriteriasByCourseID(courseid, false);

	GeneralCriteria generalCur = new GeneralCriteria();
	// detailed criteria
	ObservableList<DetailedCriteria> detailedCriteria = FXCollections.observableArrayList();
	DetailedCriteria detailedCur = new DetailedCriteria();

//	@FXML
//	private TextField curveValue;
	@FXML
	private Button closeCourse;
	@FXML
	private Button curve;
	@FXML
	private Button statistic;

	/*
	 * initialize table
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		courseLabel.setText(course.getCName());

		// boolean ifFromHistory = courseHistory.getIfFromHistory();
		if (changable == false) {
			closeCourse.setVisible(false);
			curve.setVisible(false);
			curveValue.setVisible(false);
			statistic.setVisible(false);
		}

		BUIDColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("BUID"));

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("firstName"));
		middleNameColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("middleName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("lastName"));
		compositeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Overview, String> param) {
						return new SimpleStringProperty(
								String.format("%.2f", param.getValue().getTotal() * (curveVal + 1)));
					}
				});
		gradeColumn.setCellValueFactory(new PropertyValueFactory<Overview, String>("grade"));
		gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		ArrayList<GeneralCriteria> generalCriteria = operations.getGeneralCriteriasByCourseID(courseid, false);
		System.out.println("General Size: " + generalCriteria.size() + " !!!!!!");

		for (GeneralCriteria generalCri : generalCriteria) {
			TableColumn<Overview, String> gColumn = new TableColumn<Overview, String>();
			gColumn.setText(generalCri.getGenCriType());
			gColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(TableColumn.CellDataFeatures<Overview, String> param) {
							ArrayList<GeneralGrade> gcS = param.getValue().getGcScores();
							double gc = 0.0;
							for (GeneralGrade gg : gcS) {
								if (gg.getgCriID().equals(generalCri.getgCriID())) {
									gc = gg.getScore();
									break;
								}
							}
							return new SimpleStringProperty(new DoubleStringConverter().toString(gc));
						}
					});
			tableView.getColumns().add(gColumn);

			// expand
			TableColumn<Overview, String> g1Column = new TableColumn<Overview, String>();
			g1Column.setText(generalCri.getGenCriType());
			g1Column.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(TableColumn.CellDataFeatures<Overview, String> param) {
							ArrayList<GeneralGrade> gcS = param.getValue().getGcScores();
							double gc = 0.0;
							for (GeneralGrade gg : gcS) {
								if (gg.getgCriID().equals(generalCri.getgCriID())) {
									gc = gg.getScore();
									break;
								}
							}
							return new SimpleStringProperty(new DoubleStringConverter().toString(gc));
						}
					});
			ArrayList<DetailedCriteria> detailedList = operations
					.getDetailedCriteriasByGenerCriID(generalCri.getgCriID(), false);
			System.out.println("Detailed Size: " + detailedList.size() + " !!!!!!");

			if (detailedList != null) {
				for (DetailedCriteria detailedCri : detailedList) {
					System.out.println("cur detailed type: " + detailedCri.getDeCriType());
					TableColumn<Overview, String> dColumn = new TableColumn<Overview, String>();
					dColumn.setText(detailedCri.getDeCriType());

					dColumn.setCellValueFactory(
							new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
								@Override
								public ObservableValue<String> call(
										TableColumn.CellDataFeatures<Overview, String> param) {
									ArrayList<HashMap<String, List<DetailedGrade>>> dcS = param.getValue().getDcs();
									System.out.println("list size: " + dcS.size());
									double dc = 0.0;
									for (HashMap<String, List<DetailedGrade>> d : dcS) {
//										if ((d.get(generalCri.getgCriID()) != null) && (d.get(generalCri.getgCriID())
//												.getdCriID().equals(detailedCri.getdCriID()))) {
										System.out.println("GID: " + generalCri.getgCriID());
										System.out.println("DID: " + detailedCri.getdCriID());
										for (String key : d.keySet()) {
											System.out.println("Key size:" + d.get(key).size());
											System.out.println("HashMap size:" + d.size());
											for (DetailedGrade dGrade : d.get(key)) {
												if (dGrade.getdCriID().equals(detailedCri.getdCriID())) {
													dc = dGrade.getScore();
													break;
												}
											}
										}
									}
									return new SimpleStringProperty(new DoubleStringConverter().toString(dc));
								}
							});
					// dColumn.setVisible(false);
					g1Column.getColumns().add(dColumn);
					System.out.println("Add once!!!!!!!!!!!!!");
				}
			}

			tableView.getColumns().add(g1Column);
			g1Column.setVisible(false);
			columnCoexists.add(new ColumnCoexist(gColumn, g1Column));
			// columnList.add(gColumn);
		}

		tableView.setItems(getOverview());
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	@FXML
	public void changeGradingColumnEvent(TableColumn.CellEditEvent edittedCell) {
		Overview overviewSelected = tableView.getSelectionModel().getSelectedItem();
		int index = tableView.getSelectionModel().getFocusedIndex();
		overviewSelected.setGrade(edittedCell.getNewValue().toString());
		overviewData.get(index).setGrade(edittedCell.getNewValue().toString());
		// operations.getCourseStudents
	}

	public ObservableList<Overview> getOverview() {
		System.out.println(course.toString());
		overviewData = FXCollections.observableArrayList();
//		course = operations.getCourseInfo("1");
		ArrayList<Overview> overviewList = new ArrayList<>();
		overviewList.addAll(operations.getOverviewInfoByCourseID(course));
		System.out.println("overviewSize:" + overviewList.size());
		for (int i = 0; i < overviewList.size(); i++) {
			overviewData.add(overviewList.get(i));
			System.out.println(overviewData.toString());
		}
		System.out.println("Overview size : " + overviewData.size());
		return overviewData;
	}

	@FXML
	public void closeCourseButton(ActionEvent event) throws IOException {
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
	public void changeModeButtonClicked(ActionEvent event) throws IOException {
		for (ColumnCoexist i : columnCoexists) {
			System.out.println("Column...");
			i.switchVisible();
		}

	}

	@FXML
	public void saveButtonClicked(ActionEvent event) throws IOException {
		ArrayList<Overview> temp = new ArrayList<>();
		for (int i = 0; i < overviewData.size(); i++) {
			Overview tempOverview = overviewData.get(i);
			temp.add(tempOverview);
		}

		operations.saveOrUpdateOverview(temp, course);
		// overviewData = FXCollections.observableArrayList();
		// tableView.setItems(getOverview());
		tableView.refresh();
	}

	@FXML
	public void curveButton(ActionEvent event) throws IOException {
		String val = curveValue.getText();
		if (val != "")
			curveVal = Double.parseDouble(val);
		overviewData = FXCollections.observableArrayList();
		tableView.refresh();

	}

	@FXML
	public void statisticButton(ActionEvent event) throws IOException {
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

	@FXML
	public void backButton(ActionEvent event) throws IOException {

		if (changable == true) {
			Parent gradingParent = FXMLLoader.load(getClass().getResource("Grading.fxml"));
			Scene gradingScene = new Scene(gradingParent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(gradingScene);
			window.setTitle("Grading");
			window.show();
		} else {
			Parent gradingParent = FXMLLoader.load(getClass().getResource("CourseHistory.fxml"));
			Scene gradingScene = new Scene(gradingParent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(gradingScene);
			window.setTitle("History Courses");
			window.show();
		}

	}

}
