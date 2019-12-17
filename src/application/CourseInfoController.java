package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.xmlbeans.impl.xb.xsdschema.All;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import pojo.Course;
import pojo.DetailedCriteria;
import pojo.GeneralCriteria;

public class CourseInfoController implements Initializable {

	@FXML
	private Button saveDetailedCriteria;
	@FXML
	private Button deleteDetailedCriteria;
	@FXML
	private Button addDetailedCriteria;
	@FXML
	private Button importFromTemplate;
	@FXML
	private Button saveAsTemplate;
	@FXML
	private Button saveGeneralCriteria;
	@FXML
	private Button deleteGeneralCriteria;
	@FXML
	private Button addGeneralCriteria;
	@FXML
	private Button saveCourseInfo;
	@FXML
	private TextField generalCriteriaType;
	@FXML
	private TextField generalCriteriaPer;
	@FXML
	private TextField detailedCriteriaPer;
	@FXML
	private TextField detailedCriteriaType;

	@FXML
	private TextField detailedCScore;

	@FXML
	private TextField semester;
	@FXML
	private ChoiceBox<String> college = new ChoiceBox<String>();
	@FXML
	private TextField credit;
	@FXML
	private TextField courseName;

	private boolean ifTemplate;

	private static Course course = new Course();

	Operations operations;

	// general criteria
	ObservableList<GeneralCriteria> generalCriteria;
	ArrayList<GeneralCriteria> generalArr;
	GeneralCriteria generalCur;
	// detailed criteria
	ObservableList<DetailedCriteria> detailedCriteria;
	DetailedCriteria detailedCur;

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
	
	private CourseHomeController courseHomeController;
	private TemplateInfoController tController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ArrayList<String> collegeStr = new ArrayList<String>();
		collegeStr.add("CAS");
		collegeStr.add("GRS");
		ObservableList<String> colleges = FXCollections.observableArrayList(collegeStr);
		college.setItems(colleges);
		college.getSelectionModel().selectFirst();

		operations = new Operations();
		Login loginController = new Login();
		String semesterStr = loginController.getSemester();
		semester.setText(semesterStr);
		semester.setEditable(false);

		generalCriteria = FXCollections.observableArrayList();
		detailedCriteria = FXCollections.observableArrayList();
		generalCur = new GeneralCriteria();
		detailedCur = new DetailedCriteria();

		tController = new TemplateInfoController();
		ifTemplate = tController.getIfTemplate();

		courseHomeController = new CourseHomeController();
		boolean addOrEdit = courseHomeController.getAddOrEdit();
		if (addOrEdit == true) { // ADD
			course = new Course();
			generalArr = new ArrayList<GeneralCriteria>();
		} else { // EDIT
			course = courseHomeController.getCourse();
			generalArr = operations.getGeneralCriteriasByCourseID(course.getCID(), false);
			credit.setText("4.0");
			if (course.getCollege().equals("CAS")) {
				college.getSelectionModel().select(0);
			} else {
				college.getSelectionModel().select(1);
			}
			courseName.setText(course.getCName());
		}
		if (ifTemplate == true) {
			generalArr = tController.getGeneralCriteriaFromTemplate();
			operations.deleteGeneralCriteriaByCourseID(course.getCID());
			for (GeneralCriteria gc : generalArr) {
				System.out.println(gc.getgCriID());
				ArrayList<DetailedCriteria> detailedArr = operations.getDetailedCriteriasByGenerCriID(gc.getgCriID(),
						true);
				gc.setgCriID(null);
				gc.setcID(course.getCID());
				String uuid = operations.saveGeneralCriteria(gc);
				for (DetailedCriteria dc : detailedArr) {
					dc.setgCriID(uuid);
					dc.setdCriID(null);

				}

				operations.saveDetailedCriterias(course, detailedArr, false);
			}
			ifTemplate = false;
			tController.setIfTemplate(false);
		}

		generalTypeColumn.setCellValueFactory(new PropertyValueFactory<GeneralCriteria, String>("genCriType"));
		generalPercentageColumn.setCellValueFactory(new PropertyValueFactory<GeneralCriteria, Double>("genCriPer"));
		generalTableView.setItems(getGeneralCriteria());
		generalTableView.setEditable(true);
		generalTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		generalPercentageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		// detailed table
		detailedTypeColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, String>("deCriType"));
		detailedPercentageColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, Double>("deCriPer"));
		detailedTotalScoreColumn.setCellValueFactory(new PropertyValueFactory<DetailedCriteria, Double>("totalScore"));

//		detailedTableView.setItems(getDetailedCriteria(generalArr));

		detailedTableView.setEditable(true);
		detailedTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		detailedPercentageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		detailedTotalScoreColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

	}

	public void userClickedOnGeneralTable() {
		System.out.println("general clicked!");
		detailedCriteria = FXCollections.observableArrayList();
		generalCur = generalTableView.getSelectionModel().getSelectedItem();

		ArrayList<DetailedCriteria> detailedArr = new ArrayList<>();
		if (generalArr.size() != 0) {
			detailedArr = operations.getDetailedCriteriasByGenerCriID(generalCur.getgCriID(), ifTemplate);
			for (int i = 0; i < detailedArr.size(); i++) {
				detailedCriteria.add(detailedArr.get(i));
			}
			detailedTableView.setItems(detailedCriteria);
		}
	}

	private ObservableList<GeneralCriteria> getGeneralCriteria() {
		generalArr = operations.getGeneralCriteriasByCourseID(course.getCID(), false);
		for (int i = 0; i < generalArr.size(); i++) {
			generalCriteria.add(generalArr.get(i));
		}
		return generalCriteria;
	}

	@FXML
	public void saveGeneralCriteriaButton(ActionEvent event) {

		ArrayList<GeneralCriteria> temp = new ArrayList<>();
		for (int i = 0; i < generalCriteria.size(); i++) {
			GeneralCriteria tempGeneral = generalCriteria.get(i);
			System.out.println(tempGeneral.getgCriID());
			temp.add(tempGeneral);
		}

		if (operations.saveGeneralCriterias(temp, false)) {
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			Pane pane = new Pane();
			info.setContentText("Save successfully!!");
			info.getDialogPane().setExpandableContent(pane);
			info.show();
			generalTableView.refresh();

		} else {
			Alert info = new Alert(Alert.AlertType.ERROR);
			Pane pane = new Pane();
			info.setContentText("General criterias added up should be 100%!!");
			info.getDialogPane().setExpandableContent(pane);
			info.show();

			// refresh
			generalCriteria = FXCollections.observableArrayList();
			generalTableView.setItems(getGeneralCriteria());
			generalTableView.refresh();
		}
	}

	@FXML
	public void addGeneralCriteriaButton(ActionEvent event) {
		GeneralCriteria gCriteria = new GeneralCriteria(null, course.getCID(), generalCriteriaType.getText(),
				Double.parseDouble(generalCriteriaPer.getText()));
		generalTableView.getItems().add(gCriteria);
	}

	@FXML
	public void deleteGeneralCriteriaButton(ActionEvent event) {
		ObservableList<GeneralCriteria> allGeCriterias, selectedGeCriterias;
		allGeCriterias = generalTableView.getItems();
		selectedGeCriterias = generalTableView.getSelectionModel().getSelectedItems();
		for (GeneralCriteria gc : selectedGeCriterias) {
			allGeCriterias.remove(gc);
			if (ifTemplate == false) {
				operations.deleteGeneralCriteria(gc, false);
			}
		}
	}

	@FXML
	public void importFromTemplateButton(ActionEvent event) {
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

		Stage main = (Stage) importFromTemplate.getScene().getWindow();
		main.close();
	}

	@FXML
	public void saveAsTemplateButton(ActionEvent event) {
		ArrayList<GeneralCriteria> generalCriterias = operations.getGeneralCriteriasByCourseID(course.getCID(), false);
		for (GeneralCriteria gc : generalCriterias) {
			ArrayList<DetailedCriteria> dCris = operations.getDetailedCriteriasByGenerCriID(gc.getgCriID(), false);
			operations.saveDetailedCriterias(course, dCris, true);
		}
		operations.saveGeneralCriterias(generalCriterias, true);

	}

	@FXML
	public void saveDetailedCriteriaButton(ActionEvent event) {
		ArrayList<DetailedCriteria> temp = new ArrayList<>();
		for (int i = 0; i < detailedCriteria.size(); i++) {
			DetailedCriteria tempDetailed = detailedCriteria.get(i);
			temp.add(tempDetailed);
		}

		if (operations.saveDetailedCriterias(course, temp, false)) {
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			Pane pane = new Pane();
			info.setContentText("Save successfully!!");
			info.getDialogPane().setExpandableContent(pane);
			info.show();
			detailedTableView.refresh();

		} else {
			System.out.println("Added up should be 100%!!");
			Alert info = new Alert(Alert.AlertType.ERROR);
			Pane pane = new Pane();
			info.setContentText("Detailed criterias added up should be 100%!!!!");
			info.getDialogPane().setExpandableContent(pane);
			info.show();
			// refresh
			detailedCriteria = FXCollections.observableArrayList();

			ArrayList<DetailedCriteria> detailedArr = new ArrayList<>();
			detailedArr = operations.getDetailedCriteriasByGenerCriID(generalCur.getgCriID(), ifTemplate);

			for (int i = 0; i < detailedArr.size(); i++) {
				detailedCriteria.add(detailedArr.get(i));
			}
			detailedTableView.setItems(detailedCriteria);
			detailedTableView.refresh();
		}
	}

	@FXML
	public void addDetailedCriteriaButton(ActionEvent event) {
		System.out.println("Add detailed clicked");
		System.out.println("general cur: " + generalCur.getgCriID());
		DetailedCriteria dCriteria = new DetailedCriteria(null, generalCur.getgCriID(), detailedCriteriaType.getText(),
				Double.parseDouble(detailedCriteriaPer.getText()), Double.parseDouble(detailedCScore.getText()));
//		detailedTableView.getItems().add(dCriteria);
		
		detailedCriteria.add(dCriteria);
		detailedTableView.refresh();
	}

	@FXML
	public void deleteDetailedCriteriaButton(ActionEvent event) {
		ObservableList<DetailedCriteria> allDeCriterias, selectedDeCriterias;
		allDeCriterias = detailedTableView.getItems();
		selectedDeCriterias = detailedTableView.getSelectionModel().getSelectedItems();
		for (DetailedCriteria dc : selectedDeCriterias) {
			allDeCriterias.remove(dc);
			if (ifTemplate == false) {
				operations.deleteDetailedCriteria(dc, false);
			}
		}
	}

	@FXML
	public void saveCourseInfoButton(ActionEvent event) {
		course.setCName(courseName.getText());
		course.setCollege(college.getSelectionModel().getSelectedItem());
		course.setState(1);
		course.setSemID(semester.getText());
		courseHomeController.setCourse(course);
		operations.saveCourseInfo(course);
	}

	@FXML
	public void backButton(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("CourseHome.fxml"));
		Scene scene = new Scene(parent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Current Courses");
		window.show();
	}

	@FXML
	public void changeGeneralTypeCellEvent(CellEditEvent edittedCell) {
		GeneralCriteria generalCriteriaSelected = generalTableView.getSelectionModel().getSelectedItem();
		int index = generalTableView.getSelectionModel().getFocusedIndex();

		generalCriteriaSelected.setGenCriType(edittedCell.getNewValue().toString());

		generalCriteria.get(index).setGenCriType(edittedCell.getNewValue().toString());

	}

	@FXML
	void changeGeneralPercentageCellEvent(CellEditEvent edittedCell) {
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

}
