package pojo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

// Table General Criteria

public class GeneralCriteria {
	private String gCriID; // *, General Criteria ID, [HS], created by courseID(cID) and genenal criteria
							// type(GenCriType)
	private String cID; // courseID, [HS], created by coursename and semester
	private SimpleStringProperty genCriType; // General Criteria Type, Assignment, Quiz, Exam
	private SimpleDoubleProperty genCriPer; // General Criteria Type 's Percentage

	public GeneralCriteria() {

	}

	public GeneralCriteria(String gCriID, String cID, String genCriType, double genCriPer) {
		super();
		this.gCriID = gCriID;
		this.cID = cID;
		this.genCriType = new SimpleStringProperty(genCriType);
		this.genCriPer = new SimpleDoubleProperty(genCriPer);
	}

	public GeneralCriteria(TemplateGeneralCriteria tGCri) {
		super();
		this.gCriID = tGCri.getgCriID();
		this.cID = tGCri.getcID();
		this.genCriType = new SimpleStringProperty(tGCri.getGenCriType());
		this.genCriPer = new SimpleDoubleProperty(tGCri.getGenCriPer());
	}

	public String getgCriID() {
		return gCriID;
	}

	public void setgCriID(String gCriID) {
		this.gCriID = gCriID;
	}

	public String getcID() {
		return cID;
	}

	public void setcID(String cID) {
		this.cID = cID;
	}

	public String getGenCriType() {
		return genCriType.get();
	}

	public void setGenCriType(String genCriType) {
		this.genCriType = new SimpleStringProperty(genCriType);
	}

	public double getGenCriPer() {
		return genCriPer.get();
	}

	public void setGenCriPer(double genCriPer) {
		this.genCriPer = new SimpleDoubleProperty(genCriPer);
	}
	
	public String toString() {
		return "gCriID: " + gCriID + "cID: " + cID + "genCriType: " + genCriType + "genCriPer: " + genCriPer;
	}
}
