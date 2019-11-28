package pojo;

public class StudentDetailedGrade {
	// no key
	private String cSID; // created by courseID and buID, [HS]
	private String dCriID;  // Detailed Criteria ID, [HS], created by General Criteria ID(gCriID)[HS] and detailed criteria type(deCriType)
	private double score;
	public String getcSID() {
		return cSID;
	}
	public void setcSID(String cSID) {
		this.cSID = cSID;
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
