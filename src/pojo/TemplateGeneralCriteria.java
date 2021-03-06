package pojo;

import javafx.beans.property.SimpleDoubleProperty;

// Table TemplateGeneralCriteria
public class TemplateGeneralCriteria {
	private String gCriID; // *, General Criteria ID, [HS], created by courseID(cID) and genenal criteria
							// type(GenCriType)
	private String cID; // courseID, [HS], created by coursename and semester
	private String genCriType; // General Criteria Type, Assignment, Quiz, Exam
	private SimpleDoubleProperty genCriPer; // General Criteria Type 's Percentage

	public TemplateGeneralCriteria() {

	}

	public TemplateGeneralCriteria(String gCriID, String cID, String genCriType, double genCriPer) {
		super();
		this.gCriID = gCriID;
		this.cID = cID;
		this.genCriType = genCriType;
		this.genCriPer = new SimpleDoubleProperty(genCriPer);
	}

	public TemplateGeneralCriteria(GeneralCriteria gCri) {
		super();
		this.gCriID = gCri.getgCriID();
		this.cID = gCri.getcID();
		this.genCriType = gCri.getGenCriType();
		this.genCriPer = new SimpleDoubleProperty(gCri.getGenCriPer());
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
		return genCriType;
	}

	public void setGenCriType(String genCriType) {
		this.genCriType = genCriType;
	}

	public double getGenCriPer() {
		return genCriPer.get();
	}

	public void setGenCriPer(double genCriPer) {
		this.genCriPer = new SimpleDoubleProperty(genCriPer);
	}
}
