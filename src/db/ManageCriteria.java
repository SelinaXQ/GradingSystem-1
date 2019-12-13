package db;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import pojo.DetailedCriteria;
import pojo.GeneralCriteria;
import pojo.Student;
import pojo.TemplateGeneralCriteria;

public class ManageCriteria {
	public ArrayList<GeneralCriteria> getGeneralCriteriaByCourseID(String cID) {
		Session session = null;
		Transaction tx = null;
		List<GeneralCriteria> gCris = null;
		try  {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<GeneralCriteria> query = builder.createQuery(GeneralCriteria.class);
			Root<GeneralCriteria> root = query.from(GeneralCriteria.class);
			query.select(root).where(builder.equal(root.get("cID"), cID));
			Query<GeneralCriteria> q = session.createQuery(query);
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
		return (ArrayList<GeneralCriteria>) gCris;
	}

	public ArrayList<DetailedCriteria> getDetailedCriterias(String gCriID) {
		Session session = null;
		Transaction tx = null;
		List<DetailedCriteria> dCris = null;
		try  {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<DetailedCriteria> query = builder.createQuery(DetailedCriteria.class);
			Root<DetailedCriteria> root = query.from(DetailedCriteria.class);
			query.select(root).where(builder.equal(root.get("gCriID"), gCriID));
			Query<DetailedCriteria> q = session.createQuery(query);
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
		//	GeneralCriteria gCri = (GeneralCriteria) session.get(GeneralCriteria.class, gCriteria.getgCriID());
		//	System.out.println("Database:" + gCri.getgCriID());
		//	session.merge(gCri);
			session.saveOrUpdate(gCriteria);
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
			session.saveOrUpdate(dCriteria);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteGeneralCriteriasByCID(String cid) {
		// TODO Auto-generated method stub
		
	}
}
