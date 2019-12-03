package db;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.GeneralCriteria;
import pojo.TemplateDetailedCriteria;
import pojo.TemplateGeneralCriteria;

public class ManageTemplate {

	public ArrayList<TemplateGeneralCriteria> getGeneralCriteriaByCourseID(String cID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List gCris = null;
		Criteria criteria = session.createCriteria(GeneralCriteria.class);
		criteria.add(Restrictions.eq("cID", cID));
		gCris = (List) criteria.list();
		session.close();
		return (ArrayList<TemplateGeneralCriteria>) gCris;
	}

	public ArrayList<TemplateDetailedCriteria> getDetailedCriterias(String gCriID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List dCris = null;
		Criteria criteria = session.createCriteria(GeneralCriteria.class);
		criteria.add(Restrictions.eq("gCriID", gCriID));
		dCris = (List) criteria.list();
		session.close();
		return (ArrayList<TemplateDetailedCriteria>) dCris;
	}


	public void deleteGeneralCriteria(TemplateGeneralCriteria templateGeneralCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TemplateGeneralCriteria gCri =  (TemplateGeneralCriteria) session.get(TemplateGeneralCriteria.class, templateGeneralCriteria.getgCriID());
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

	public void updateOrSaveGeneralCriteria(TemplateGeneralCriteria templateGeneralCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TemplateGeneralCriteria gCri = (TemplateGeneralCriteria) session.get(TemplateGeneralCriteria.class, templateGeneralCriteria.getgCriID());
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

	public void updateOrSaveDetailedCriteria(TemplateDetailedCriteria templateDetailedCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TemplateDetailedCriteria dCri = (TemplateDetailedCriteria) session.get(TemplateDetailedCriteria.class, templateDetailedCriteria.getdCriID());
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

	public void deleteDetailedCriteria(TemplateDetailedCriteria templateDetailedCriteria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TemplateDetailedCriteria dCri = (TemplateDetailedCriteria) session.get(TemplateDetailedCriteria.class, templateDetailedCriteria.getdCriID());
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

}
