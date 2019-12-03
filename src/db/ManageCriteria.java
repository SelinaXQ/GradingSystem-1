package db;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.DetailedCriteria;
import pojo.GeneralCriteria;
import pojo.Student;

public class ManageCriteria {
	public ArrayList<GeneralCriteria> getGeneralCriteriaByCourseID(String cID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List gCris = null;
		Criteria criteria = session.createCriteria(GeneralCriteria.class);
		criteria.add(Restrictions.eq("cID", cID));
		gCris = (List) criteria.list();
		session.close();
		return (ArrayList<GeneralCriteria>) gCris;
	}

	public ArrayList<DetailedCriteria> getDetailedCriterias(String gCriID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List dCris = null;
		Criteria criteria = session.createCriteria(GeneralCriteria.class);
		criteria.add(Restrictions.eq("gCriID", gCriID));
		dCris = (List) criteria.list();
		session.close();
		return (ArrayList<DetailedCriteria>) dCris;
	}

	public void deleteGeneralCriteria(GeneralCriteria gCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GeneralCriteria gCri = (GeneralCriteria) session.get(GeneralCriteria.class, gCriteria.getgCriID());
			session.delete(gCri);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void deleteDetailedCriteria(DetailedCriteria dCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			DetailedCriteria dCri = (DetailedCriteria) session.get(DetailedCriteria.class, dCriteria.getdCriID());
			session.delete(dCri);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void updateOrSaveGeneralCriteria(GeneralCriteria gCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GeneralCriteria gCri = (GeneralCriteria) session.get(GeneralCriteria.class, gCriteria.getgCriID());
			session.merge(gCri);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void updateOrSaveDetailedCriteria(DetailedCriteria  dCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			DetailedCriteria dCri = (DetailedCriteria) session.get(DetailedCriteria.class, dCriteria.getdCriID());
			session.merge(dCri);
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
