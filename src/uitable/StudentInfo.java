package uitable;

public class StudentInfo {
    private String BUID; // *
    private String firstName;
    private String middleName;
    private String lastName;
    private String condition; // w or null

    public StudentInfo() {

    }

    public StudentInfo(String bUID, String firstName, String middleName, String lastName, String condition) {
        super();
        this.BUID = bUID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.condition = condition;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return BUID + "," + firstName + "," + middleName + "," + lastName + "," + condition;

    }


}
