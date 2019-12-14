package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import uitable.GiveDetailedGrades;

public class CommentController implements Initializable{

	GradingController gradingController = new GradingController();
	
	GiveDetailedGrades grade = new GiveDetailedGrades();
	
	@FXML private TextArea commentTextArea;
	private String comment;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		grade = gradingController.getCurGrade();
		if(!grade.getComment().equals(null) || !grade.getComment().equals("")) {
			commentTextArea.setText(grade.getComment());
		}
		
	}

	public void saveButtonClicked(ActionEvent event) throws IOException {
		
		comment = commentTextArea.getText();
		grade.setComment(comment);
		
		//save into db
		
		
		
		//jump back
		Parent commentParent = FXMLLoader.load(getClass().getResource("Grading.fxml"));
		Scene commentScene = new Scene(commentParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(commentScene);
		window.show();
		
	}
	
	public void cancelButtonClicked(ActionEvent event) throws IOException{
		
		Parent commentParent = FXMLLoader.load(getClass().getResource("Grading.fxml"));
		Scene commentScene = new Scene(commentParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(commentScene);
		window.show();
		
	}
	
}
