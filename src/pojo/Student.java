package pojo;

// Table StudentInfo
public class Student {
	private String BUID; // *
	private String firstName;
	private String middleName;
	private String lastName;

	public Student() {

	}

	public Student(String bUID, String firstName, String middleName, String lastName) {
		super();
		this.BUID = bUID;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public String getBUID() {
		return BUID;
	}

	public void setBUID(String bUID) {
		BUID = bUID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
