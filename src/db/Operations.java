package db;

import java.util.ArrayList;
import java.util.HashMap;

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
		Account account = mOthers.getAccount(userName).get(0);
		if (account.getPassword().equals(pwd)) {
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

	// click save as template.
	// if not save as template, but just the course' s criteria, click 'save' in two
	// steps

	public void saveCriteriaAsTemplate(ArrayList<GeneralCriteria> gCris, ArrayList<DetailedCriteria> dCris) {
		saveGeneralCriterias(gCris, true);
		saveDetailedCriterias(null, dCris, true);
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
					mCriteria.updateOrSaveDetailedCriteria(dCri);
					if (c != null) {
						ArrayList<CourseStudents> csStudents = mStudents.getStudentsByCId(c);
						for (CourseStudents cs : csStudents) {
							StudentDetailedGrade sdg = new StudentDetailedGrade(cs.getcSID(), dCri.getdCriID(), 0.00,
									null);
							mOthers.updateOrSaveStudentDetailedGrade(sdg);
						}
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
			Student student = mStudents.getStudentByBUID(s.getbUID()).get(0);
			ArrayList<GeneralCriteria> gcs = mCriteria.getGeneralCriteriaByCourseID(course.getcID());
			ArrayList<HashMap<String, DetailedGrade>> dgs = new ArrayList<HashMap<String, DetailedGrade>>();
			ArrayList<GeneralGrade> genGrades = new ArrayList<GeneralGrade>();
			for (GeneralCriteria gc : gcs) {
				HashMap<String, DetailedGrade> dList = new HashMap<String, DetailedGrade>();
				ArrayList<DetailedCriteria> dCs = mCriteria.getDetailedCriterias(gc.getgCriID());
				for (DetailedCriteria dc : dCs) {
					System.out.println(s.getcSID());
					System.out.println(dc.getdCriID());
					StudentDetailedGrade sdGrade = mOthers.getStudentDetailedGrade(s.getcSID(), dc.getdCriID()).get(0);
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
			StudentDetailedGrade studentDetailedGrade = new StudentDetailedGrade(cs.getcSID(), dCriteria.getdCriID(),
					gdg.getScore(), gdg.getComment());
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
			StudentDetailedGrade sDetailedGrade = mOthers.getStudentDetailedGrade(cs.getcSID(), dCriteria.getdCriID())
					.get(0);
			Student s = mStudents.getStudentByBUID(cs.getbUID()).get(0);

			GiveDetailedGrades giveDetailedGrade = new GiveDetailedGrades(s.getBUID(), s.getFirstName(),
					s.getMiddleName(), s.getLastName(), sDetailedGrade.getScore());
			// GiveDetailedGrades giveDetailedGrade = new GiveDetailedGrades(s.getBUID(),
			// s.getFirstName(), s.getMiddleName(), s.getLastName(),
			// sDetailedGrade.getScore(), sDetailedGrade.getComment());
			giveDetailedGrades.add(giveDetailedGrade);
		}
		return giveDetailedGrades;

	}

	// Load Information to Manage student information window

	public ArrayList<StudentInfo> getStudentsByCourseID(Course course) {
		ArrayList<StudentInfo> sInfos = new ArrayList<StudentInfo>();
		ArrayList<CourseStudents> css = mStudents.getStudentsByCId(course);
		for (CourseStudents cs : css) {
			Student student = mStudents.getStudentByBUID(cs.getbUID()).get(0);
			StudentInfo sInfo = new StudentInfo(student.getBUID(), student.getFirstName(), student.getMiddleName(),
					student.getLastName(), cs.getCondition());
			sInfos.add(sInfo);
		}
		return sInfos;
	}

	// Save Information from Manage student information window

	public void saveOpUpdateStudentsInfo(ArrayList<StudentInfo> sInfos, Course c) {
		for (StudentInfo sInfo : sInfos) {
			Student student = new Student(sInfo.getBUID(), sInfo.getFirstName(), sInfo.getMiddleName(),
					sInfo.getLastName());
			mStudents.updateOrSaveStudent(student);
			System.out.println(sInfo.getCondition());
			ArrayList<CourseStudents> css = mStudents.getCourseStudent(sInfo.getBUID(), c.getcID());
			System.out.println(css.size());
			if (css.size() == 0) {
				System.out.println("Save students!");
				CourseStudents cs = new CourseStudents();
				cs.setbUID(sInfo.getBUID());
				cs.setcID(c.getcID());
				cs.setCondition(sInfo.getCondition());
				mStudents.updateOrSaveCourseStudent(cs);
			} else {
				CourseStudents cs = new CourseStudents();
				cs.setcSID(css.get(0).getcSID());
				cs.setbUID(sInfo.getBUID());
				cs.setcID(c.getcID());
				cs.setCondition(sInfo.getCondition());
				mStudents.updateOrSaveCourseStudent(cs);
			}

		}
	}

	public void deleteStudentInfo(StudentInfo sInfo, Course c) {
		CourseStudents cs = mStudents.getCourseStudent(sInfo.getBUID(), c.getcID()).get(0);
		// CourseStudents courseStudents = new CourseStudents(null, cs.getcID(),
		// cs.getbUID(), cs.getCondition(),
		// cs.getGrade(), cs.getComment());
		mStudents.deleteStudent(cs);
	}
}
