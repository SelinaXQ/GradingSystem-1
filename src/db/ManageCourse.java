package db;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import antlr.collections.List;
import pojo.*;

public class ManageCourse {
	public ArrayList<Course> getCoursesBySemester(String semester){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List courses = null;
		Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("SEMID", semester));
		courses = (List) criteria.list();
		session.close();
		return (ArrayList<Course>) courses;
	}
	
	public ArrayList<Semester> getSemesters(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List semesters = null;
		try {
			tx = session.beginTransaction();
			semesters = (List) session.createQuery("FROM Semesters").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<Semester>) semesters;
	}

	public ArrayList<Course> getCoursesByCID(String cID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List courses = null;
		Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("CID", cID));
		courses = (List) criteria.list();
		session.close();
		return (ArrayList<Course>) courses;
	}

	

	public void updateOrSaveCourse(Course c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Course course = (Course) session.get(Course.class, c.getcID());
			session.merge(course);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
