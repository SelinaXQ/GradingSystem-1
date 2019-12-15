package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.Operations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pojo.DetailedCriteria;
import uitable.GiveDetailedGrades;

public class CommentController implements Initializable{
	
	Operations operations = new Operations();

	GradingController gradingController = new GradingController();
	
	GiveDetailedGrades grade = new GiveDetailedGrades();
	
	DetailedCriteria dCriteria = new DetailedCriteria();
	
	@FXML private TextArea commentTextArea;
	private String comment;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		dCriteria = gradingController.getCurCriteria();
		grade = gradingController.getCurGrade();
		System.out.println(grade);
		commentTextArea.setText(grade.getComment());
		
		
	}

	@FXML
	public void saveButtonClicked(ActionEvent event) throws IOException {
		
		System.out.println("save button clicked");
		
		comment = commentTextArea.getText();
		grade.setComment(comment);
		
		//save into db
		
		operations.saveComment(dCriteria, grade);
		
		//jump back
		Parent commentParent = FXMLLoader.load(getClass().getResource("Grading.fxml"));
		
		Scene commentScene = new Scene(commentParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(commentScene);
		window.show();
		
	}
	
	@FXML
	public void cancelButtonClicked(ActionEvent event) throws IOException{
		
		Parent commentParent = FXMLLoader.load(getClass().getResource("Grading.fxml"));
		
		Scene commentScene = new Scene(commentParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(commentScene);
		window.show();
		
	}
	
}
