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

import pojo.Course;
import pojo.GeneralCriteria;
import pojo.TemplateDetailedCriteria;
import pojo.TemplateGeneralCriteria;

public class ManageTemplate {

	public ArrayList<TemplateGeneralCriteria> getGeneralCriteriaByCourseID(String cID) {
		Session session = null;
		Transaction tx = null;
		List<TemplateGeneralCriteria> gCris = null;
		try  {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TemplateGeneralCriteria> query = builder.createQuery(TemplateGeneralCriteria.class);
			Root<TemplateGeneralCriteria> root = query.from(TemplateGeneralCriteria.class);
			query.select(root).where(builder.equal(root.get("cID"), cID));
			Query<TemplateGeneralCriteria> q = session.createQuery(query);
			gCris  = q.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}finally {
			session.close();
		}
		return (ArrayList<TemplateGeneralCriteria>) gCris;
	}

	public ArrayList<TemplateDetailedCriteria> getDetailedCriterias(String gCriID) {
		Session session = null;
		Transaction tx = null;
		List<TemplateDetailedCriteria> dCris = null;
		try  {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TemplateDetailedCriteria> query = builder.createQuery(TemplateDetailedCriteria.class);
			Root<TemplateDetailedCriteria> root = query.from(TemplateDetailedCriteria.class);
			query.select(root).where(builder.equal(root.get("gCriID"), gCriID));
			Query<TemplateDetailedCriteria> q = session.createQuery(query);
			dCris  = q.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}finally {
			session.close();
		}
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
			session.saveOrUpdate(templateDetailedCriteria);
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
