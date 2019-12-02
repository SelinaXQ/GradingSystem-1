package uitable;

// this class is for expand columns for the overview window 's table
// build ArrayList

public class DetailedGrade {
	private String dCriID;  // Detailed Criteria ID, [HS], created by General Criteria ID(gCriID)[HS] and detailed criteria type(deCriType)
	private double score;
	private double per;
	
	public DetailedGrade(String dCriID, double score,double per) {
		super();
		this.dCriID = dCriID;
		this.score = score;
		this.per = per;
	}
	
	public double getPer() {
		return per;
	}

	public void setPer(double per) {
		this.per = per;
	}

	public String getdCriID() {
		return dCriID;
	}
	public void setdCriID(String dCriID) {
		this.dCriID = dCriID;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
