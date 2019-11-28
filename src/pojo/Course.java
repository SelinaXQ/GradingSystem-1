package pojo;

// Table CourseInfo
public class Course {
	private String cID; // *, course Id [HS], created by coursename and semester
	private String cName; // course name, CS 591 P1
	private String semID; // semster ID, Fall 2019, Spring 2019, Summer 2019
	private String college; // college, CAS, GRS
	private boolean state; // closed or not, 0 = closed, 1 = open

	public String getcID() {
		return cID;
	}

	public void setcID(String cID) {
		this.cID = cID;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String toString() {
		return college + " " + cName;
	}

}
