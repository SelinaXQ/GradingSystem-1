package db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.*;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;

import pojo.Account;
import pojo.StudentDetailedGrade;

public class ManageOthers {
	public ArrayList<Account> getAccount(String userName) {
		Transaction transaction = null;
		List<Account> accs = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class);
			query.select(root).where(builder.equal(root.get("userName"), userName));
			Query<Account> q = session.createQuery(query);
			accs = q.getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}finally {
			session.close();
		}
		return (ArrayList<Account>) accs;

	}

	public ArrayList<StudentDetailedGrade> getStudentDetailedGrade(String cSID, String dCriID) {
		Transaction transaction = null;
		List<StudentDetailedGrade> studentDetailedGrades = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<StudentDetailedGrade> query = builder.createQuery(StudentDetailedGrade.class);
			Root<StudentDetailedGrade> root = query.from(StudentDetailedGrade.class);
			query.select(root).where(builder.equal(root.get("cSID"), cSID), builder.equal(root.get("dCriID"), dCriID));
			query.orderBy(builder.asc(root.get("cSID")));
			Query<StudentDetailedGrade> q = session.createQuery(query);
			studentDetailedGrades = q.getResultList();
			transaction.commit();
			studentDetailedGrades.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}finally {
			session.close();
		}
		System.out.println("CSID: " + cSID);
		System.out.println("dcriid: " + dCriID);
		System.out.println("SDG: " + studentDetailedGrades.size());
		return (ArrayList<StudentDetailedGrade>) studentDetailedGrades;

	}

	public void updateOrSaveStudentDetailedGrade(StudentDetailedGrade studentDetailedGrade) {
		// TODO Auto-generated method stub

	}
}
