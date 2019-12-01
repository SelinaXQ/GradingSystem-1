package pojo;

// Table SemesterInfo
public class Semester {
	private String semID; // *
	// semster ID, Fall 2019, Spring 2019, Summer 2019
	

	public String getSemID() {
		return semID;
	}

	public Semester(String semID) {
		super();
		this.semID = semID;
	}

	public void setSemID(String semID) {
		this.semID = semID;
	}
	public String toString() {
		return semID;
	}
}
