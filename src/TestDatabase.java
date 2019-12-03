import java.util.ArrayList;

import db.HibernateUtil;
import db.Operations;
import pojo.Course;
import pojo.GeneralCriteria;
import pojo.Semester;
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
		System.out.println(course);

		// ArrayList<GeneralCriteria> gc =
		// operations.getGeneralCriteriasByCourseID("12345678",false);
		// System.out.println(gc);
		// ArrayList<GeneralCriteria> gc2 =
		// operations.getGeneralCriteriasByCourseID("12345678",true);
		// System.out.println(gc2);
		ArrayList<StudentInfo> sInfos = new ArrayList<StudentInfo>();
		sInfos.add(new StudentInfo("U8899", "FAA", "M", "L", ""));

		operations.saveOpUpdateStudentsInfo(sInfos, course);
		operations.deleteStudentInfo(new StudentInfo("B", "FAA", "M", "L", ""));

		HibernateUtil.close();
	}
}
