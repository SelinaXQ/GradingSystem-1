package application;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import pojo.Course;
import pojo.DetailedCriteria;
import pojo.GeneralCriteria;

public class TemplateInfoController implements Initializable {
	
	@FXML
	private TextField semester;
	@FXML
	private TextField college;
	@FXML
	private TextField credit;
	@FXML
	private TextField courseName;
	@FXML
	private Button cancelTemplate;
	@FXML
	private Button importTemplate;

	private Course course;

	private Operations operations;

	private boolean addOrEdit;
	private static boolean ifTemplate = false;

	// general criteria
	private ObservableList<GeneralCriteria> generalCriteria;
	private static ArrayList<GeneralCriteria> generalArr;
	private GeneralCriteria generalCur;
	// detailed criteria
	private ObservableList<DetailedCriteria> detailedCriteria;
	private DetailedCriteria detailedCur;

	// table general
	@FXML
	private TableView<GeneralCriteria> generalTableView;
	@FXML
	private TableColumn<GeneralCriteria, String> generalTypeColumn;
	@FXML
	private TableColumn<GeneralCriteria, Double> generalPercentageColumn;

	// table detailed
	@FXML
	private TableView<DetailedCriteria> detailedTableView;
	@FXML
	private TableColumn<DetailedCriteria, String> detailedTypeColumn;
	@FXML
	private TableColumn<DetailedCriteria, Double> detailedPercentageColumn;
	@FXML
	private TableColumn<DetailedCriteria, Double> detailedTotalScoreColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CourseHomeController cHomeController = new CourseHomeController();
		addOrEdit = cHomeController.getAddOrEdit();
		
		ImportTemplateController iController = new ImportTemplateController();
		course = iController.getCourse();
		courseName.setText(course.getCName());
		college.setText(course.getCollege());
		credit.setText("4.0");
		semester.setText(course.getSemID());
		
		operations = new Operations();
		generalArr = operations.getGeneralCriteriasByCourseID(course.getCID(), true);

		generalCriteria = FXCollections.observableArrayList();
		detailedCriteria = FXCollections.observableArrayList();
		generalCur = new GeneralCriteria();
		detailedCur = new DetailedCriteria();

		generalTypeColumn.setCellValueFactory(new PropertyValueFactory<GeneralCriteria, String>("genCriType"));
		generalPercentageColumn.setCellValueFactory(new PropertyValueFactory<GeneralCriteria, Double>("genCriPer"));
		generalTableView.setItems(getGeneralCriteria());
		generalTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		generalPercentageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		// detailed table
		detailedTypeColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, String>("deCriType"));
		detailedPercentageColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, Double>("deCriPer"));
		detailedTotalScoreColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, Double>("totalScore"));

//		detailedTableView.setItems(getDetailedCriteria(generalArr));

		detailedTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		detailedPercentageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		detailedTotalScoreColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

	}

	private ObservableList<GeneralCriteria> getGeneralCriteria() {
		for (int i = 0; i < generalArr.size(); i++) {
			generalCriteria.add(generalArr.get(i));
		}
		return generalCriteria;
	}

	@FXML
	public void cancelTemplateButton(ActionEvent event) {
		ifTemplate = false;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ImportTemplate.fxml"));
		// SemesterController controller = new SemesterController();
		// loader.setController(this);
		Stage importTemplate = new Stage();

		Scene scene;
		try {
			scene = new Scene(loader.load());
			importTemplate.setScene(scene);
			importTemplate.setTitle("Import from a template");
			// initData();
		} catch (IOException e) {
			e.printStackTrace();
		}

		importTemplate.show();

		Stage main = (Stage) cancelTemplate.getScene().getWindow();
		main.close();
	}

	@FXML
	public void importTemplateButton(ActionEvent event) {
		ifTemplate = true;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseInformation.fxml"));
		// SemesterController controller = new SemesterController();
		// loader.setController(this);
		Stage courseInfo = new Stage();

		Scene scene;
		try {
			scene = new Scene(loader.load());
			courseInfo.setScene(scene);
			if (addOrEdit == true) {
				courseInfo.setTitle("Add Course Information & Criteria");
			} else {
				courseInfo.setTitle("Edit Course Information & Criteria");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		courseInfo.show();

		Stage main = (Stage) importTemplate.getScene().getWindow();
		main.close();
	}

	public void userClickedOnGeneralTable() {
		System.out.println("general clicked!");
		detailedCriteria = FXCollections.observableArrayList();
		generalCur = generalTableView.getSelectionModel().getSelectedItem();

		ArrayList<DetailedCriteria> detailedArr = new ArrayList<>();
		detailedArr = operations.getDetailedCriteriasByGenerCriID(generalCur.getgCriID(), true);

		for (int i = 0; i < detailedArr.size(); i++) {
			detailedCriteria.add(detailedArr.get(i));
		}
		detailedTableView.setItems(detailedCriteria);
	}


	public boolean getIfTemplate() {
		return ifTemplate;
	}

	public ArrayList<GeneralCriteria> getGeneralCriteriaFromTemplate() {
		return generalArr;
	}
	
	public void setIfTemplate(boolean flag) {
		ifTemplate = flag;
	}

}
