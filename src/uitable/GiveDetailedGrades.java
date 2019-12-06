package uitable;

import javafx.beans.property.SimpleDoubleProperty;

// this class is for the window which could give detailed score to students
// each attribute is corresponding to a column in that table

public class GiveDetailedGrades {
    private String BUID;
    private String fName;
    private String mName;
    private String lName;
    private SimpleDoubleProperty score;
    private String comment;

    public GiveDetailedGrades(String bUID, String fName, String mName, String lName, double score) {
        this.BUID = bUID;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.score = new SimpleDoubleProperty(score);
    }

    public GiveDetailedGrades(String bUID, String fName, String mName, String lName, double score, String comment) {
        this.BUID = bUID;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.score = new SimpleDoubleProperty(score);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBUID() {
        return BUID;
    }

    public void setBUID(String bUID) {
        BUID = bUID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public double getScore() {
        return score.get();
    }

    public void setScore(double score) {
        this.score = new SimpleDoubleProperty(score);
    }
}
