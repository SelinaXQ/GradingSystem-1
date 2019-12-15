package pojo;


// Table CourseStudentInfo
public class CourseStudents {
	private String cSID; // *, created by courseID and buID, [HS]

	private String cID; // course Id [HS], created by coursename and semester

	private String bUID;

	private String condition; // w or null

	private String grade; // A+ , A-

	private String comment;

	public CourseStudents() {

	}

	public CourseStudents(String cSID, String cID, String bUID, String condition, String grade, String comment) {
		this.cSID = cSID;
		this.cID = cID;
		this.bUID = bUID;
		this.condition = condition;
		this.grade = grade;
		this.comment = comment;
	}

	public String getcSID() {
		return cSID;
	}

	public void setcSID(String cSID) {
		this.cSID = cSID;
	}

	public String getcID() {
		return cID;
	}

	public void setcID(String cID) {
		this.cID = cID;
	}

	public String getbUID() {
		return bUID;
	}

	public void setbUID(String bUID) {
		this.bUID = bUID;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
