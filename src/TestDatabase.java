import java.util.ArrayList;

import db.HibernateUtil;
import db.Operations;
import pojo.Course;
import pojo.DetailedCriteria;
import pojo.GeneralCriteria;
import pojo.Semester;
import uitable.Overview;
import uitable.StudentInfo;

public class TestDatabase {
	public static void main(String[] args) {
		Operations operations = new Operations();

		// boolean re = operations.login("cpk", "cpk");
		// System.out.println(re);

		// ArrayList<Semester> semester = operations.getSemesters();
		// semester.forEach(System.out::println);

		// Semester semester = new Semester("Fall 2019");
		// ArrayList<Course> courses =
		// operations.getCoursesBySemester(semester.getSemID());
		// courses.forEach(System.out::println);
		Course course = operations.getCourseInfo("1");
		// System.out.println(course);

		// ArrayList<GeneralCriteria> gc =
		// operations.getGeneralCriteriasByCourseID("1",false);
		// System.out.println(gc.size());
		// System.out.println(gc.get(0).getgCriID());
		// ArrayList<GeneralCriteria> gc2 =
		// operations.getGeneralCriteriasByCourseID("1",true);
		// System.out.println(gc2);
		// System.out.println(gc2.get(0).getgCriID());
		// ArrayList<StudentInfo> sInfos = new ArrayList<StudentInfo>();
		// sInfos.add(new StudentInfo("U8899", "FAA", "M", "L", ""));

		// operations.saveOpUpdateStudentsInfo(sInfos, course);
		// operations.deleteStudentInfo(new StudentInfo("B", "FAA", "M", "L", ""));

		// saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate)
		// operations.deleteGeneralCriteria(gc2.get(1), false);
		// operations.deleteGeneralCriteria(gc2.get(1), true);
		// GeneralCriteria gc = new GeneralCriteria("abcc", "1", "final", 0.5);
		// ArrayList<DetailedCriteria> dgs =
		// operations.getDetailedCriteriasByGenerCriID(gc.getgCriID(), false);
		// System.out.println(dgs.get(0).getdCriID());
		// ArrayList<DetailedCriteria> dgs2 = new ArrayList<DetailedCriteria>();
		// System.out.println(dgs2.get(0).getdCriID());
		// dgs2.add(new DetailedCriteria(null, "abcd", "dh1", 0.50, 0.00));
		// dgs2.add(new DetailedCriteria(null, "abcd", "dh2", 0.50, 0.00));
		// operations.deleteDetailedCriteria(new
		// DetailedCriteria("234","abcc","dh",0.60,0.00),true);
		// operations.saveDetailedCriterias(dgs2, true);
		// saveCriteriaAsTemplate(ArrayList<GeneralCriteria> gCris,
		// ArrayList<DetailedCriteria> dCris)

		// public ArrayList<GiveDetailedGrades> getStudentsDetailedGrades(Course course,
		// DetailedCriteria dCriteria）

		// public void updateStudentsDetailedGrade(DetailedCriteria dCriteria,
		// ArrayList<GiveDetailedGrades> dGs)

		 ArrayList<Overview> overviews =  operations.getOverviewInfoByCourseID(course);
		 System.out.println(overviews.get(0).getGcScores().get(0).getScore());

		HibernateUtil.close();
	}
}
