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
			semesters = (List) session.createQuery("FROM SemesterInfo").list();
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

	public ArrayList<GeneralCriteria> getGeneralCriteriaByCourseID(String cID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List gCris = null;
		Criteria criteria = session.createCriteria(GeneralCriteria.class);
		criteria.add(Restrictions.eq("CID", cID));
		gCris = (List) criteria.list();
		session.close();
		return (ArrayList<GeneralCriteria>) gCris;
	}

	public ArrayList<DetailedCriteria> getDetailedCriterias(String gCriID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteGeneralCriteria(GeneralCriteria gCriteria) {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetailedCriteria(DetailedCriteria dCriteria) {
		// TODO Auto-generated method stub
		
	}

	public void updateOrSaveGeneralCriteria(GeneralCriteria gCri) {
		// TODO Auto-generated method stub
		
	}

	public void updateOrSaveDetailedCriteria(DetailedCriteria dCri) {
		// TODO Auto-generated method stub
		
	}
}
