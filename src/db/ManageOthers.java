package db;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import antlr.collections.List;
import pojo.Account;
import pojo.DetailedCriteria;
import pojo.Student;
import pojo.StudentDetailedGrade;

public class ManageOthers {
	public ArrayList<Account> getAccount(String userName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List accs = null;
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("username", userName));
		accs = (List) criteria.list();
		return (ArrayList<Account>) accs;
	}
	
	public ArrayList<StudentDetailedGrade> getStudentDetailedGrade(String cSID, String dCriID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List studentDetailedGrades = null;
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("cSID", cSID));
		criteria.add(Restrictions.eq("dCriID", dCriID));
		criteria.addOrder(Order.asc("cSID"));
		studentDetailedGrades = (List) criteria.list();
		return (ArrayList<StudentDetailedGrade>) studentDetailedGrades;
		
		
	}

	public void updateOrSaveStudentDetailedGrade(StudentDetailedGrade studentDetailedGrade) {
		// TODO Auto-generated method stub
		
	}
}
