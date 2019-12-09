package uitable;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

// this class is for the window which could give detailed score to students
// each attribute is corresponding to a column in that table

public class GiveDetailedGrades {
    private SimpleStringProperty BUID;
    private SimpleStringProperty fName;
    private SimpleStringProperty mName;
    private SimpleStringProperty lName;
    private SimpleDoubleProperty score;
    private SimpleStringProperty comment;

    public GiveDetailedGrades(String bUID, String fName, String mName, String lName, double score) {
        this.BUID = new SimpleStringProperty(bUID);
        this.fName = new SimpleStringProperty(fName);
        this.mName = new SimpleStringProperty(mName);
        this.lName = new SimpleStringProperty(lName);
        this.score = new SimpleDoubleProperty(score);
    }

    public GiveDetailedGrades(String bUID, String fName, String mName, String lName, double score, String comment) {
        this.BUID = new SimpleStringProperty(bUID);
        this.fName = new SimpleStringProperty(fName);
        this.mName = new SimpleStringProperty(mName);
        this.lName = new SimpleStringProperty(lName);
        this.score = new SimpleDoubleProperty(score);
        this.comment = new SimpleStringProperty(comment);
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment = new SimpleStringProperty(comment);
    }

    public String getBUID() {
        return BUID.get();
    }

    public void setBUID(String bUID) {
        BUID = new SimpleStringProperty(bUID);
    }

    public String getFName() {
        return fName.get();
    }

    public void setFName(String fName) {
        this.fName = new SimpleStringProperty(fName);
    }

    public String getMName() {
        return mName.get();
    }

    public void setMName(String mName) {
        this.mName = new SimpleStringProperty(mName);
    }

    public String getLName() {
        return lName.get();
    }

    public void setLName(String lName) {
        this.lName = new SimpleStringProperty(lName);
    }

    public double getScore() {
        return score.get();
    }

    public void setScore(double score) {
        this.score = new SimpleDoubleProperty(score);
    }
    
    public String toString() {
    	return "BUID: " + BUID + "fName: " + fName +  "mName: " + mName + "lName: " + lName + "score: " + score;
    }
}
