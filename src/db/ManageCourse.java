package db;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import pojo.*;

public class ManageCourse {
	public ArrayList<Course> getCoursesBySemester(String semester){
		Session session = null;
		Transaction tx = null;
		List<Course> courses = null;
		try  {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Course> query = builder.createQuery(Course.class);
			Root<Course> root = query.from(Course.class);
			query.select(root).where(builder.equal(root.get("semID"), semester));
			Query<Course> q = session.createQuery(query);
			courses  = q.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}finally {
			session.close();
		}
		return (ArrayList<Course>) courses;
	}
	
	public ArrayList<Semester> getSemesters(){
		Session session = null;
		Transaction tx = null;
		List<Semester> semesters = null;
		try  {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Semester> query = builder.createQuery(Semester.class);
			Root<Semester> root = query.from(Semester.class);
			query.select(root);
			Query<Semester> q = session.createQuery(query);
			semesters  = q.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}finally {
			session.close();
		}
		return (ArrayList<Semester>) semesters;
	}

	public ArrayList<Course> getCoursesByCID(String cID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List courses = null;
		Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("cID", cID));
		courses = (List) criteria.list();
		session.close();
		return (ArrayList<Course>) courses;
	}

	

	public void updateOrSaveCourse(Course c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		//	Course course = (Course) session.get(Course.class, c.getcID());
			session.saveOrUpdate(c);
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
