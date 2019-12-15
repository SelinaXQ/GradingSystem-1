package pojo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DetailedCriteria {
	private String dCriID; // *, Detailed Criteria ID, [HS], created by General Criteria ID(gCriID)[HS] and
							// detailed criteria type(deCriType)
	private String gCriID; // General Criteria ID, [HS], created by courseID(cID) and genenal criteria
							// type(GenCriType)
	private SimpleStringProperty deCriType; // Detailed Criteria Type, Assignment1, Assignment2
	private SimpleDoubleProperty deCriPer; // Detailed Criteria Type 's Percentage
	private SimpleDoubleProperty totalScore;

	public DetailedCriteria() {

	}

	public DetailedCriteria(String dCriID, String gCriID, String deCriType, double deCriPer, double totalScore) {
		super();
		this.dCriID = dCriID;
		this.gCriID = gCriID;
		this.deCriType = new SimpleStringProperty(deCriType);
		this.deCriPer = new SimpleDoubleProperty(deCriPer);
		this.totalScore = new SimpleDoubleProperty(totalScore);
	}

	public DetailedCriteria(TemplateDetailedCriteria tDCri) {
		this.dCriID = tDCri.getdCriID();
		this.gCriID = tDCri.getgCriID();
		this.deCriType = new SimpleStringProperty(tDCri.getDeCriType());
		this.deCriPer = new SimpleDoubleProperty(tDCri.getDeCriPer());
		this.totalScore = new SimpleDoubleProperty(tDCri.getTotalScore());
	}

	public double getTotalScore() {
		return totalScore.get();
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = new SimpleDoubleProperty(totalScore);
	}

	public String getdCriID() {
		return dCriID;
	}

	public void setdCriID(String dCriID) {
		this.dCriID = dCriID;
	}

	public String getgCriID() {
		return gCriID;
	}

	public void setgCriID(String gCriID) {
		this.gCriID = gCriID;
	}

	public String getDeCriType() {
		return deCriType.get();
	}

	public void setDeCriType(String deCriType) {
		this.deCriType = new SimpleStringProperty(deCriType);
	}

	public double getDeCriPer() {
		return deCriPer.get();
	}

	public void setDeCriPer(double deCriPer) {
		this.deCriPer = new SimpleDoubleProperty(deCriPer);
	}
	
	public String toString() {
		return "dCriID: " + dCriID + "gCriID: " + gCriID + "deCriType: " + 
	deCriType + "deCriPer: " + deCriPer + "totalScore: " + totalScore;
	}
}
