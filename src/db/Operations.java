package db;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Id;

import pojo.*;
import uitable.DetailedGrade;
import uitable.GeneralGrade;
import uitable.GiveDetailedGrades;
import uitable.Overview;
import uitable.StudentInfo;

public class Operations {
	static ManageCourse mCourse;
	static ManageCriteria mCriteria;
	static ManageStudents mStudents;
	static ManageTemplate mTemplate;
	public static ManageOthers mOthers;

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
		ArrayList<Account> accounts = mOthers.getAccount(userName);
		if (accounts.size() != 0) {
			if (accounts.get(0).getPassword().equals(pwd)) {
				flag = true;
			}
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

	public void saveCourseInfo(Course course) {
		mCourse.updateOrSaveCourse(course);
	}

	// get General Criterias By course ID

	public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate) {
		if (ifTemplate == true) {
			ArrayList<TemplateGeneralCriteria> tGCris = mTemplate.getGeneralCriteriaByCourseID(cID);
			ArrayList<GeneralCriteria> gCriterias = new ArrayList<GeneralCriteria>();
			for (TemplateGeneralCriteria tGCri : tGCris) {
				gCriterias.add(new GeneralCriteria(tGCri));
			}
			return gCriterias;
		} else {
			return mCriteria.getGeneralCriteriaByCourseID(cID);
		}
	}

	// get detailed Criterias By General Criterias (gCriID

	public ArrayList<DetailedCriteria> getDetailedCriteriasByGenerCriID(String gCriID, boolean ifTemplate) {
		if (ifTemplate == true) {
			ArrayList<TemplateDetailedCriteria> tDCris = mTemplate.getDetailedCriterias(gCriID);
			ArrayList<DetailedCriteria> dCriterias = new ArrayList<DetailedCriteria>();
			for (TemplateDetailedCriteria tDCri : tDCris) {
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
		if (Math.abs(total - 1) < 0.00001) {
			flag = true;
		}
		if (flag == false) {
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
			System.out.println(gCriteria.getgCriID());
			mTemplate.deleteGeneralCriteria(new TemplateGeneralCriteria(gCriteria));

		} else {
			mCriteria.deleteGeneralCriteria(gCriteria);

		}
	}

	// save the percentage of a detailed criteria,

	public boolean saveDetailedCriterias(Course c, ArrayList<DetailedCriteria> dCris, boolean ifTemplate) {
		boolean flag = true;
		double total = 0;
		for (DetailedCriteria dCri : dCris) {
			total += dCri.getDeCriPer();
		}
		if (Math.abs(total - 1) > 0.00001) {
			flag = false;
		}
		if (flag == false) {
			return flag;
		} else {
			if (ifTemplate == true) {
				for (DetailedCriteria dCri : dCris) {
					mTemplate.updateOrSaveDetailedCriteria(new TemplateDetailedCriteria(dCri));
				}
			} else {
				for (DetailedCriteria dCri : dCris) {
					boolean ifNull = dCri.getdCriID() == null ? true : false;
					ArrayList<StudentInfo> sInfos = getStudentsByCourseID(c);
					mCriteria.updateOrSaveDetailedCriteria(dCri);
					System.out.println("DCriID return??? " + dCri.getdCriID());
					if (ifNull && sInfos.size() != 0) {
						ArrayList<GiveDetailedGrades> grades = new ArrayList<GiveDetailedGrades>();
						for (StudentInfo sInfo : sInfos) {
							GiveDetailedGrades grade = new GiveDetailedGrades(sInfo.getBUID(), sInfo.getFirstName(),
									sInfo.getMiddleName(), sInfo.getLastName(), 0.00, null);
							grades.add(grade);
						}
						updateStudentsDetailedGrade(dCri, grades);
					}
				}
			}
			return flag;
		}
	}

	// delete a detailed criteria

	public void deleteDetailedCriteria(DetailedCriteria dCriteria, boolean ifTemplate) {
		if (ifTemplate == true) {
			mTemplate.deleteDetailedCriteria(new TemplateDetailedCriteria(dCriteria));

		} else {
			mCriteria.deleteDetailedCriteria(dCriteria);
		}
	}

	// get information for the overview window

	public ArrayList<Overview> getOverviewInfoByCourseID(Course course) {
		ArrayList<Overview> overviews = new ArrayList<Overview>();
		ArrayList<CourseStudents> students = mStudents.getStudentsByCId(course);
		for (CourseStudents s : students) {
			Student student = mStudents.getStudentByBUID(s.getBUID()).get(0);
			ArrayList<GeneralCriteria> gcs = mCriteria.getGeneralCriteriaByCourseID(course.getCID());
			ArrayList<HashMap<String, DetailedGrade>> dgs = new ArrayList<HashMap<String, DetailedGrade>>();
			ArrayList<GeneralGrade> genGrades = new ArrayList<GeneralGrade>();
			for (GeneralCriteria gc : gcs) {
				HashMap<String, DetailedGrade> dList = new HashMap<String, DetailedGrade>();
				ArrayList<DetailedCriteria> dCs = mCriteria.getDetailedCriterias(gc.getgCriID());
				for (DetailedCriteria dc : dCs) {
					StudentDetailedGrade sdGrade = mOthers.getStudentDetailedGrade(s.getCSID(), dc.getdCriID()).get(0);
					double grade = sdGrade.getScore();
					double totalScore = dc.getTotalScore();
					double per = dc.getDeCriPer();
					if (grade < 0) {
						grade = (totalScore - Math.abs(grade)) / totalScore;
					}
					DetailedGrade dGrade = new DetailedGrade(dc.getdCriID(), grade, per);
					dList.put(gc.getgCriID(), dGrade);
				}
				dgs.add(dList);
				GeneralGrade gg = new GeneralGrade(gc.getgCriID(), gc.getGenCriPer(), getGeneralGrades(dList, gc));
				genGrades.add(gg);
			}
			Overview overview = new Overview(student, dgs, genGrades, getOverviewPercentage(genGrades), "");
			overviews.add(overview);
		}
		return overviews;
	}

	// get a general criteria score
	private double getGeneralGrades(HashMap<String, DetailedGrade> dgs, GeneralCriteria gc) {
		double totalScore = 0;
		for (String gCriID : dgs.keySet()) {
			DetailedGrade dGrade = dgs.get(gCriID);
			double per = dGrade.getPer();
			double score = dGrade.getScore();
			totalScore += per * score;
		}
		return totalScore;
	}

	// get overview score for the overview window
	private double getOverviewPercentage(ArrayList<GeneralGrade> ggs) {
		double totalScore = 0;
		for (GeneralGrade gg : ggs) {
			double per = gg.getPer();
			double score = gg.getScore();
			totalScore += per * score;
		}
		return totalScore;
	}

	// save students ' detailed grades and comments
	// use uitable.detailedgrades class
	public void updateStudentsDetailedGrade(DetailedCriteria dCriteria, ArrayList<GiveDetailedGrades> gdgs) {
		for (GiveDetailedGrades gdg : gdgs) {
			Student s = mStudents.getStudentByBUID(gdg.getBUID()).get(0);
			CourseStudents cs = mStudents.getStudentCSID(s.getBUID()).get(0);
			ArrayList<StudentDetailedGrade> sdgs = mOthers.getStudentDetailedGrade(cs.getCSID(), dCriteria.getdCriID());
			StudentDetailedGrade sdg;
			StudentDetailedGrade studentDetailedGrade;
			if (sdgs.size() == 0) {
				System.out.println("??????" + dCriteria.getdCriID());
				studentDetailedGrade = new StudentDetailedGrade(cs.getCSID(), dCriteria.getdCriID(), gdg.getScore(),
						gdg.getComment());
			} else {
				sdg = sdgs.get(0);
				studentDetailedGrade = new StudentDetailedGrade(sdg.getSDGID(), cs.getCSID(), dCriteria.getdCriID(),
						gdg.getScore(), gdg.getComment());
			}
			mOthers.updateOrSaveStudentDetailedGrade(studentDetailedGrade);
		}
	}

	// close a course
	public void closeCourse(Course c) {
		c.setState(0);
		mCourse.updateOrSaveCourse(c);
	}

	// Grading, this will return a arraylist which could be used directly by the
	// table

	public ArrayList<GiveDetailedGrades> getStudentsDetailedGrades(Course course, DetailedCriteria dCriteria) {
		ArrayList<CourseStudents> students = mStudents.getStudentsByCId(course);
		ArrayList<GiveDetailedGrades> giveDetailedGrades = new ArrayList<GiveDetailedGrades>();
		for (CourseStudents cs : students) {
			System.out.println("CSID:"+cs.getCSID());
			System.out.println("Dcriid"+ dCriteria.getdCriID());
			StudentDetailedGrade sDetailedGrade = mOthers.getStudentDetailedGrade(cs.getCSID(), dCriteria.getdCriID())
					.get(0);
			Student s = mStudents.getStudentByBUID(cs.getBUID()).get(0);

			// GiveDetailedGrades giveDetailedGrade = new GiveDetailedGrades(s.getBUID(),
			// s.getFirstName(),
			// s.getMiddleName(), s.getLastName(), sDetailedGrade.getScore());
			GiveDetailedGrades giveDetailedGrade = new GiveDetailedGrades(s.getBUID(), s.getFirstName(),
					s.getMiddleName(), s.getLastName(), sDetailedGrade.getScore(), sDetailedGrade.getComment());
			giveDetailedGrades.add(giveDetailedGrade);
		}
		return giveDetailedGrades;

	}

	// Load Information to Manage student information window

	public ArrayList<StudentInfo> getStudentsByCourseID(Course course) {
		ArrayList<StudentInfo> sInfos = new ArrayList<StudentInfo>();
		ArrayList<CourseStudents> css = mStudents.getStudentsByCId(course);
		if (css.size() != 0) {
			for (CourseStudents cs : css) {
				Student student = mStudents.getStudentByBUID(cs.getBUID()).get(0);
				StudentInfo sInfo = new StudentInfo(student.getBUID(), student.getFirstName(), student.getMiddleName(),
						student.getLastName(), cs.getCondition());
				sInfos.add(sInfo);
			}
		}
		return sInfos;
	}

	// Save Information from Manage student information window

	public void saveStudentsInfo(ArrayList<StudentInfo> sInfos, Course c) {
		for (StudentInfo sInfo : sInfos) {
			Student student = new Student(sInfo.getBUID(), sInfo.getFirstName(), sInfo.getMiddleName(),
					sInfo.getLastName());
			mStudents.updateOrSaveStudent(student);

			ArrayList<CourseStudents> css = mStudents.getCourseStudent(sInfo.getBUID(), c.getCID());
			CourseStudents cs = new CourseStudents();
			cs.setBUID(sInfo.getBUID());
			cs.setCID(c.getCID());
			cs.setCondition(sInfo.getCondition());
			if (css.size() == 0) {
				mStudents.updateOrSaveCourseStudent(cs);
				initDetailedGrades(cs, c);
			} else {
				cs.setCSID(css.get(0).getCSID());
				mStudents.updateOrSaveCourseStudent(cs);
			}
		}
	}

	private void initDetailedGrades(CourseStudents cs, Course c) {
		CourseStudents cStudent = mStudents.getCourseStudent(cs.getBUID(), c.getCID()).get(0);
		ArrayList<GeneralCriteria> gCriterias = getGeneralCriteriasByCourseID(c.getCID(), false);
		for (GeneralCriteria gc : gCriterias) {
			ArrayList<DetailedCriteria> dcs = getDetailedCriteriasByGenerCriID(gc.getgCriID(), false);
			for (DetailedCriteria dc : dcs) {
				StudentDetailedGrade sdg = new StudentDetailedGrade(cStudent.getCSID(), dc.getdCriID(), 0.00, null);
				mOthers.updateOrSaveStudentDetailedGrade(sdg);
			}
		}
	}

	public void updateStudentInfo(ArrayList<StudentInfo> sInfos, Course c) {
		for (StudentInfo sInfo : sInfos) {
			CourseStudents cs = mStudents.getCourseStudent(sInfo.getBUID(), c.getCID()).get(0);
			CourseStudents cStudents = new CourseStudents();
			cStudents.setCSID(cs.getCSID());
			cStudents.setBUID(sInfo.getBUID());
			cStudents.setCID(c.getCID());
			cStudents.setCondition(sInfo.getCondition());
			mStudents.updateOrSaveCourseStudent(cStudents);

			if (sInfo.getCondition().trim().equals("w")) {
				deleteDetailedGrades(cs, c);
			}
		}
	}

	private void deleteDetailedGrades(CourseStudents cs, Course c) {
		CourseStudents cStudent = mStudents.getCourseStudent(cs.getBUID(), c.getCID()).get(0);
		ArrayList<GeneralCriteria> gCriterias = getGeneralCriteriasByCourseID(c.getCID(), false);
		for (GeneralCriteria gc : gCriterias) {
			ArrayList<DetailedCriteria> dcs = getDetailedCriteriasByGenerCriID(gc.getgCriID(), false);
			for (DetailedCriteria dc : dcs) {
				StudentDetailedGrade sdg = mOthers.getStudentDetailedGrade(cStudent.getCSID(), dc.getdCriID()).get(0);
				mOthers.deleteDetailedGrade(sdg);
			}
		}
	}

	public void deleteStudentInfo(StudentInfo sInfo, Course c) {
		CourseStudents cs = mStudents.getCourseStudent(sInfo.getBUID(), c.getCID()).get(0);
		// CourseStudents courseStudents = new CourseStudents(null, cs.getcID(),
		// cs.getbUID(), cs.getCondition(),
		// cs.getGrade(), cs.getComment());
		mStudents.deleteStudent(cs);
	}

	public void deleteGeneralCriteriaByCourseID(String cid) {
		mCriteria.deleteGeneralCriteriasByCID(cid);
	}

	public String saveGeneralCriteria(GeneralCriteria gCriteria) {
		return mCriteria.updateOrSaveGeneralCriteria(gCriteria);
	}

	public boolean saveComment(DetailedCriteria dCriteria, GiveDetailedGrades gdg) {
		Student s = mStudents.getStudentByBUID(gdg.getBUID()).get(0);
		CourseStudents cs = mStudents.getStudentCSID(s.getBUID()).get(0);
		StudentDetailedGrade sdg = mOthers.getStudentDetailedGrade(cs.getCSID(), dCriteria.getdCriID()).get(0);
		StudentDetailedGrade studentDetailedGrade = new StudentDetailedGrade(sdg.getSDGID(), cs.getCSID(),
				dCriteria.getdCriID(), gdg.getScore(), gdg.getComment());
		mOthers.updateOrSaveStudentDetailedGrade(studentDetailedGrade);
		return true;
	}
}
