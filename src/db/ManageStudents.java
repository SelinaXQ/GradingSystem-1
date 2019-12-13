package db;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import pojo.*;


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
		criteria.add(Restrictions.eq("cID", course.getCID()));
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

	public void deleteStudent(CourseStudents cStudents) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			CourseStudents cS = (CourseStudents) session.get(CourseStudents.class, cStudents.getcSID());
		    session.merge(cS);
			session.delete(cS);
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
			session.saveOrUpdate(s);
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
			session.saveOrUpdate(cs);
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
		criteria.add(Restrictions.eq("bUID", buid));
		students = (List) criteria.list();
		session.close();
		return (ArrayList<CourseStudents>) students;
	}

	public ArrayList<CourseStudents> getCourseStudent(String buid, String cID) {
		Session session = null;
		Transaction tx = null;
		List<CourseStudents> cs = null;
		try  {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CourseStudents> query = builder.createQuery(CourseStudents.class);
			Root<CourseStudents> root = query.from(CourseStudents.class);
			query.select(root).where(builder.equal(root.get("bUID"),buid), builder.equal(root.get("cID"), cID));
			Query<CourseStudents> q = session.createQuery(query);
			cs  = q.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}finally {
			session.close();
		}
		return (ArrayList<CourseStudents>) cs;
	}
}
