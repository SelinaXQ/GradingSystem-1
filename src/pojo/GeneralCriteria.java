package pojo;

// Table General Criteria

public class GeneralCriteria {
	private String gCriID; // *, General Criteria ID, [HS], created by courseID(cID) and genenal criteria
							// type(GenCriType)
	private String cID; // courseID, [HS], created by coursename and semester
	private String genCriType; // General Criteria Type, Assignment, Quiz, Exam
	private double genCriPer; // General Criteria Type 's Percentage

	public GeneralCriteria() {

	}

	public GeneralCriteria(String gCriID, String cID, String genCriType, double genCriPer) {
		super();
		this.gCriID = gCriID;
		this.cID = cID;
		this.genCriType = genCriType;
		this.genCriPer = genCriPer;
	}

	public GeneralCriteria(TemplateGeneralCriteria tGCri) {
		super();
		this.gCriID = tGCri.getgCriID();
		this.cID = tGCri.getcID();
		this.genCriType = tGCri.getGenCriType();
		this.genCriPer = tGCri.getGenCriPer();
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
		return genCriPer;
	}

	public void setGenCriPer(double genCriPer) {
		this.genCriPer = genCriPer;
	}
}
