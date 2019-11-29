package db;

import java.util.ArrayList;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import pojo.*;

import antlr.collections.List;

public class ManageStudents {
	public ArrayList<Student> getStudents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List students = null;
		try {
			tx = session.beginTransaction();
			students = (List) session.createQuery("FROM StudentInfo").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<Student>) students;
	}

	/*
	 * public ArrayList<Student> getStudentsByCId(Course course) { Session session =
	 * HibernateUtil.getSessionFactory().getCurrentSession(); List students = null;
	 * Criteria criteria = session.createCriteria(CourseStudents.class);
	 * criteria.add(Restrictions.eq("CID",course.getcID())); students = (List)
	 * criteria.list();
	 * 
	 * try { tx = session.beginTransaction(); tx.commit(); } catch
	 * (HibernateException e) { if (tx != null) tx.rollback(); e.printStackTrace();
	 * } finally { session.close(); } return (ArrayList<Student>) students; }
	 */

	public void updateStudent(Student s) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, s.getBUID());
			session.update(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteStudent(Student s) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();	
			Student student = (Student) session.get(Student.class, s.getBUID());
			session.delete(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void addStudent(Student s) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(s);
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
