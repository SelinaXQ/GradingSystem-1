package db;

import java.util.ArrayList;

import pojo.*;
import uitable.DetailedGrades;
import uitable.Overview;

public class Operations {
	ManageCourse mCourse;
	ManageCriteria mCriteria;
	ManageStudents mStudents;
	ManageTemplate mTemplate;
	ManageOthers mOthers;

	public Operations() {
		mCourse = new ManageCourse();
		mCriteria = new ManageCriteria();
		mStudents = new ManageStudents();
		mTemplate = new ManageTemplate();
		mOthers = new ManageOthers();
	}

	// Login by cpk cpk

	public boolean login(String userName, String pwd) {
		boolean flag = false;
		Account account = mOthers.getAccount(userName).get(0);
		if (account.getPassward().equals(pwd)) {
			flag = true;
		}
		return flag;
	}

	// get semesters

	public ArrayList<Semester> getSemesters() {
		return mCourse.getSemesters();
	}

	// view classes of a semester

	public ArrayList<Course> getCoursesBySemester(String semester) {
		return mCourse.getCoursesBySemester(semester);
	}

	// get a course 's information

	public Course getCourseInfo(String cID) {
		return mCourse.getCoursesByCID(cID).get(0);
	}

	// get General Criterias By course ID

	public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate) {
		if (ifTemplate == true) {
			ArrayList<TemplateGeneralCriteria> tGCris = mTemplate.getGeneralCriteriaByCourseID(cID);
			ArrayList<GeneralCriteria> gCriterias = null;
			for(TemplateGeneralCriteria tGCri : tGCris) {
				gCriterias.add(new GeneralCriteria(tGCri));
			}
			return gCriterias;
		} else {
			return mCriteria.getGeneralCriteriaByCourseID(cID);
		}
	}

	// get detailed Criterias By General Criterias (gCriID

	public ArrayList<DetailedCriteria> getDetailedCriteriasByCourseID(String gCriID, boolean ifTemplate) {
		if (ifTemplate == true) {
			ArrayList<TemplateDetailedCriteria> tDCris = mTemplate.getDetailedCriterias(gCriID);
			ArrayList<DetailedCriteria> dCriterias = null;
			for(TemplateDetailedCriteria tDCri : tDCris) {
				dCriterias.add(new DetailedCriteria(tDCri));
			}
			return dCriterias;
		} else {
			return mCriteria.getDetailedCriterias(gCriID);
		}
	}

	// save the percentage of a general criterias

	public boolean saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate) {
		boolean flag = false;
		double total = 0;
		for (GeneralCriteria gCri : gCris) {
			total += gCri.getGenCriPer();
		}
		if (Math.abs(total - 1) > 0.00001) {
			flag = true;
		}
		if (flag == true) {
			return flag;
		} else {
			if (ifTemplate == true) {
				for (GeneralCriteria gCri : gCris) {
					mTemplate.updateOrSaveGeneralCriteria(new TemplateGeneralCriteria(gCri));
				}
			} else {
				for (GeneralCriteria gCri : gCris) {
					mCriteria.updateOrSaveGeneralCriteria(gCri);
				}
			}
			return flag;
		}
	}


	// delete a general criteria

	public void deleteGeneralCriteria(GeneralCriteria gCriteria, boolean ifTemplate) {
		if (ifTemplate == true) {
			mTemplate.deleteGeneralCriteria(new TemplateGeneralCriteria(gCriteria));

		} else {
			mCriteria.deleteGeneralCriteria(gCriteria);

		}
	}
	

	// click save as template. 
	// if not save as template, but just the course' s criteria, click 'save' in two steps
	
	public void saveCriteriaAsTemplate(ArrayList<GeneralCriteria> gCris, ArrayList<DetailedCriteria> dCris) {
		saveGeneralCriterias(gCris, true);
		saveDetailedCriterias(dCris, true);
	}
	
	// save the percentage of a detailed criteria, 

	public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate) {
		boolean flag = false;
		double total = 0;
		for (DetailedCriteria dCri : dCris) {
			total += dCri.getDeCriPer();
		}
		if (Math.abs(total - 1) > 0.00001) {
			flag = true;
		}
		if (flag == true) {
			return flag;
		} else {
			if (ifTemplate == true) {
				for (DetailedCriteria dCri : dCris) {
					mTemplate.updateOrSaveDetailedCriteria(new TemplateDetailedCriteria(dCri));
				}
			} else {
				for (DetailedCriteria dCri : dCris) {
					mCriteria.updateOrSaveDetailedCriteria(dCri);
				}
			}
			return flag;
		}
	}

	// delete a detailed criteria

	public void deleteDetailedCriteria(DetailedCriteria dCriteria, boolean ifTemplate) {
		mCriteria.deleteDetailedCriteria(dCriteria);
	}

	// get information for the overview window

	public ArrayList<Overview> getOverviewInfoByCourseID(String cID) {
		return null;
	}

	// get overview score for the overview window
	public double getOverviewPercentage() {
		return 0;
	}

	// save students ' detailed grades and comments
	// use uitable.detailedgrades class
	public void updateStudentsDetailedGrade(ArrayList<DetailedGrades> dGs) {
		
	}
	
	
	// save students information
	// Thus, the add/delete/edit operations only make sense on GUI
	// only save make sense on database
	
	public void updateStudentInfo(ArrayList<Student> students) {
		for(Student s:students) {
			mStudents.updateOrSaveStudent(s);
		}
	}
	
	// close a course
	public void closeCourse(Course c) {
		c.setState(false);
		mCourse.updateOrSaveCourse(c);
	}
	
	

}
