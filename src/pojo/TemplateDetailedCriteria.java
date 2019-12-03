package pojo;

//Table TemplateDetailedCriteria
public class TemplateDetailedCriteria {
	private String dCriID; // *, Detailed Criteria ID, [HS], created by General Criteria ID(gCriID)[HS] and
							// detailed criteria type(deCriType)
	private String gCriID; // General Criteria ID, [HS], created by courseID(cID) and genenal criteria
							// type(GenCriType)
	private String deCriType; // Detailed Criteria Type, Assignment1, Assignment2
	private double deCriPer; // Detailed Criteria Type 's Percentage
	private double totalScore;

	public TemplateDetailedCriteria() {

	}

	public TemplateDetailedCriteria(String dCriID, String gCriID, String deCriType, double deCriPer,
			double totalScore) {
		super();
		this.dCriID = dCriID;
		this.gCriID = gCriID;
		this.deCriType = deCriType;
		this.deCriPer = deCriPer;
		this.totalScore = totalScore;
	}

	public TemplateDetailedCriteria(DetailedCriteria dCri) {
		this.dCriID = dCri.getdCriID();
		this.gCriID = dCri.getgCriID();
		this.deCriType = dCri.getDeCriType();
		this.deCriPer = dCri.getDeCriPer();
		this.totalScore = dCri.getTotalScore();
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
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
		return deCriType;
	}

	public void setDeCriType(String deCriType) {
		this.deCriType = deCriType;
	}

	public double getDeCriPer() {
		return deCriPer;
	}

	public void setDeCriPer(double deCriPer) {
		this.deCriPer = deCriPer;
	}
}
