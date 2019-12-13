package pojo;

// Table CourseInfo
public class Course {
	private String cID; // *, course Id [HS], created by coursename and semester
	private String cName; // course name, CS 591 P1
	private String semID; // semster ID, Fall 2019, Spring 2019, Summer 2019
	private String college; // college, CAS, GRS
	private int state; // closed or not, 0 = closed, 1 = open

	public Course() {

	}

	public Course(String cID, String cName, String semID, String college, int state) {
		super();
		this.cID = cID;
		this.cName = cName;
		this.semID = semID;
		this.college = college;
		this.state = state;
	}

	public String getCID() {
		return cID;
	}

	public void setCID(String cID) {
		this.cID = cID;
	}

	public String getCName() {
		return cName;
	}

	public void setCName(String cName) {
		this.cName = cName;
	}

	public String getSemID() {
		return semID;
	}

	public void setSemID(String semID) {
		this.semID = semID;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String toString() {
		if (state == 0) {
			return college + " " + cName + "   Closed";
		} else {
			return college + " " + cName;
		}
	}

}
