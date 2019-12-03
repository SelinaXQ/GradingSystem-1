package pojo;

public class StudentDetailedGrade {
	private String sDGId; // *
	private String cSID; // created by courseID and buID, [HS]
	private String dCriID; // Detailed Criteria ID, [HS], created by General Criteria ID(gCriID)[HS] and
							// detailed criteria type(deCriType)
	private double score;
	private String comment;

	public StudentDetailedGrade() {

	}

	public StudentDetailedGrade(String cSID, String dCriID, double score, String comment) {
		this.cSID = cSID;
		this.dCriID = dCriID;
		this.score = score;
		this.comment = comment;
	}

	public String getSDGid() {
		return sDGId;
	}

	public void setSDGid(String sDGid) {
		this.sDGId = sDGid;
	}

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
