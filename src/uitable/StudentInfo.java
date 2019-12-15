package uitable;

import javafx.beans.property.SimpleStringProperty;

public class StudentInfo {
    private SimpleStringProperty BUID; // *
    private SimpleStringProperty firstName;
    private SimpleStringProperty middleName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty condition; // w or null

    public StudentInfo() {

    }

    public StudentInfo(String bUID, String firstName, String middleName, String lastName, String condition) {
        super();
        this.BUID = new SimpleStringProperty(bUID);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.condition = new SimpleStringProperty(condition);
    }

    public String getBUID() {
        return BUID.get();
    }

    public void setBUID(String bUID) {
        BUID = new SimpleStringProperty(bUID);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public void setMiddleName(String middleName) {
        this.middleName = new SimpleStringProperty(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getCondition() {
        return condition.get();
    }

    public void setCondition(String condition) {
        this.condition = new SimpleStringProperty(condition);
    }

    @Override
    public String toString() {
        return BUID + "," + firstName + "," + middleName + "," + lastName + "," + condition;

    }


}
