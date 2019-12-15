package application;

import db.Operations;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import uitable.GeneralGrade;
import uitable.GiveDetailedGrades;
import uitable.Overview;
import uitable.StudentInfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseOverviewController implements Initializable {
    Operations operations = new Operations();
    Course course = operations.getCourseInfo("1");
    String courseid = course.getcID();
    GradingController gradingController = new GradingController();
    //student info
    ObservableList<Overview> overViewData = FXCollections.observableArrayList();
    ArrayList<Overview> overViewList = new ArrayList<>();
    ArrayList <GeneralCriteria> generalCriteria =operations.getGeneralCriteriasByCourseID("1",false);

    // general criteria
/*    ObservableList<GeneralCriteria> generalCriteria = FXCollections.observableArrayList();
    ArrayList<GeneralCriteria> generalArr = operations.getGeneralCriteriasByCourseID(courseid, false);
    GeneralCriteria generalCur = new GeneralCriteria();
    //detailed criteria
    ObservableList<DetailedCriteria> detailedCriteria = FXCollections.observableArrayList();
    DetailedCriteria detailedCur = new DetailedCriteria();
    //give grade
    ObservableList<GiveDetailedGrades> grade = FXCollections.observableArrayList();*/

//table overview
    @FXML private TableView<Overview> tableView;
    @FXML private TableColumn<Overview, String> BUIDColumn;
    @FXML private TableColumn<Overview, String> firstNameColumn;
    @FXML private TableColumn<Overview, String> middleNameColumn;
    @FXML private TableColumn<Overview, String> lastNameColumn;
    @FXML private TableColumn<Overview, String> generalCriteriaColumns;
    private TableColumn<Overview, String> compositeColumn;
    private  TableColumn<Overview,String> gradeColumn;
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

        ArrayList <GeneralCriteria> generalCriteria =operations.getGeneralCriteriasByCourseID("1",false);
        for(GeneralCriteria i: generalCriteria ){
            TableColumn<Overview,String> gColumn = new TableColumn<Overview,String>();
            gColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Overview, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Overview, String> param) {
                    ArrayList<GeneralGrade> gcS = param.getValue().getGcScores();
                    double gc=0.0;
                    for(GeneralGrade g:gcS){
                        if (g.getgCriID().equals(i.getgCriID())) gc=g.getPer();
                        break;
                    }
                    return new SimpleStringProperty(new DoubleStringConverter().toString(gc));
                }
            });
            tableView.getColumns().add(gColumn);
        }

        tableView.setItems(getOverview());
        tableView.setEditable(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        compositeColumn= new TableColumn<>("Composite");
        compositeColumn .setCellValueFactory(new PropertyValueFactory<Overview, String>("total"));
        gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public ObservableList<Overview> getOverview(){
        ArrayList<Overview> overviews = operations.getOverviewInfoByCourseID(course);
        overViewData = FXCollections.observableArrayList(overviews);
        return overViewData;
    }
}
