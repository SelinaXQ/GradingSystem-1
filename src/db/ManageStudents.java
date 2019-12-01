package db;

import java.util.ArrayList;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import pojo.*;

import antlr.collections.List;

public class ManageStudents {

	public ArrayList<Student> getStudentByBUID(String BUID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List students = null;
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.eq("BUID", BUID));
		students = (List) criteria.list();
		session.close();
		return (ArrayList<Student>) students;
	}
	
	public ArrayList<CourseStudents> getStudentsByCId(Course course) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List students = null;
		Criteria criteria = session.createCriteria(CourseStudents.class);
		criteria.add(Restrictions.eq("CID", course.getcID()));
		students = (List) criteria.list();
		session.close();
		return (ArrayList<CourseStudents>) students;
	}

	/*
	 * public void updateStudent(Student s) { Session session =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction tx = null; try {
	 * tx = session.beginTransaction(); Student student = (Student)
	 * session.get(Student.class, s.getBUID()); session.update(student);
	 * tx.commit(); } catch (HibernateException e) { if (tx != null) tx.rollback();
	 * e.printStackTrace(); } finally { session.close(); } }
	 */

	public void deleteStudent(Student s) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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

	/*
	 * public void addStudent(Student s) { Session session =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction tx = null; try {
	 * tx = session.beginTransaction(); session.save(s); tx.commit(); } catch
	 * (HibernateException e) { if (tx != null) tx.rollback(); e.printStackTrace();
	 * } finally { session.close(); } }
	 */

	public void updateOrSaveStudent(Student s) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, s.getBUID());
			session.merge(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void updateOrSaveCourseStudent(CourseStudents cs) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			CourseStudents cStudent = (CourseStudents) session.get(CourseStudents.class, cs.getcSID());
			session.merge(cStudent);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public ArrayList<CourseStudents> getStudentCSID(String buid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List students = null;
		Criteria criteria = session.createCriteria(CourseStudents.class);
		criteria.add(Restrictions.eq("BUID", buid));
		students = (List) criteria.list();
		session.close();
		return (ArrayList<CourseStudents>) students;
	}
}
