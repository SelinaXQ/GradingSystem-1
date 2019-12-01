package pojo;

// Table StudentInfo
public class Student {
	private String BUID; // *
	private String firstName;
	private String middleName;
	private String lastName;
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
	
	@Override
	public String toString() {
		return BUID + " , " + firstName + " , " + middleName + " , " + lastName;
		
	}
	
}
