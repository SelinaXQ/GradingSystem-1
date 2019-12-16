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

	public String getCSID() {
		return cSID;
	}

	public void setCSID(String cSID) {
		this.cSID = cSID;
	}

	public String getCID() {
		return cID;
	}

	public void setCID(String cID) {
		this.cID = cID;
	}

	public String getBUID() {
		return bUID;
	}

	public void setBUID(String bUID) {
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
