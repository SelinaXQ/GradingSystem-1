import db.HibernateUtil;
import db.Operations;

public class TestDatabase {
	public static void main(String[] args) {
		Operations operations = new Operations();
		boolean re = operations.login("cpk", "cpk");
		// System.out.println(re);
		 operations.mOthers.getStudentDetailedGrade("1","1");
		HibernateUtil.close();
	}
}
