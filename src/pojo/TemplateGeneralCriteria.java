package pojo;

// Table TemplateGeneralCriteria
public class TemplateGeneralCriteria {
	private String gCriID;  // *, General Criteria ID, [HS], created by courseID(cID) and genenal criteria type(GenCriType)
	private String cID; // courseID, [HS], created by coursename and semester
	private String genCriType; // General Criteria Type, Assignment, Quiz, Exam
	private double genCriPer; // General Criteria Type 's Percentage
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
		return genCriPer;
	}
	public void setGenCriPer(double genCriPer) {
		this.genCriPer = genCriPer;
	}
}
