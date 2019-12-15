package uitable;

// this class is for the overview window 's table
// build ArrayList

public class GeneralGrade {
	private String gCriID;  // *, General Criteria ID, [HS], created by courseID(cID) and genenal criteria type(GenCriType)
	private double score;
	private double per;
	
	public GeneralGrade(String gCriID,  double per, double score) {
		super();
		this.gCriID = gCriID;
		this.per = per;
		this.score = score;
	}
	public String getgCriID() {
		return gCriID;
	}
	public void setgCriID(String gCriID) {
		this.gCriID = gCriID;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getPer() {
		return per;
	}
	public void setPer(double per) {
		this.per = per;
	}
	
}
