package uitable;

import java.util.*;
import java.util.HashMap;

import pojo.Student;

// this class is for the window which could overview students ' grade
// each attribute is corresponding to a column in that table
public class Overview {
    private Student student;
    private ArrayList<HashMap<String, List<DetailedGrade>>> dcs;  // the String is the general Score 's id (primary key)
    private ArrayList<GeneralGrade> gcScores;
    private double total; // composite
    private String grade;  // A+, A, ...

    public Overview(Student student, ArrayList<HashMap<String, List<DetailedGrade>>> dcs, ArrayList<GeneralGrade> gcScores, double total,
                    String grade) {
        super();
        this.student = student;
        this.dcs = dcs;
        this.gcScores = gcScores;
        this.total = total;
        this.grade = grade;

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<HashMap<String, List<DetailedGrade>>> getDcs() {
        return dcs;
    }

    public void setDcs(ArrayList<HashMap<String, List<DetailedGrade>>> dcs) {
        this.dcs = dcs;
    }

    public ArrayList<GeneralGrade> getGcScores() {
        return gcScores;
    }

    public void setGcScores(ArrayList<GeneralGrade> gcScores) {
        this.gcScores = gcScores;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFirstName(){
        return student.getFirstName();
    }

    public String getMiddleName(){
        return student.getMiddleName();
    }

    public String getLastName(){
        return student.getLastName();
    }
    public String getBUID(){
        return  student.getBUID();
    }
    
//    public String toString() {
//    	String out = "";
//    	
//    	
//    	return out;
//    }
    
}
