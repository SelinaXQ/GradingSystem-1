package uitable;

// this class is for the overview window 's table
// build ArrayList

public class GeneralGrade {
	private String gCriID;  // *, General Criteria ID, [HS], created by courseID(cID) and genenal criteria type(GenCriType)
	private double score;
	
	public GeneralGrade(String gCriID, double score) {
		super();
		this.gCriID = gCriID;
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
	
}
