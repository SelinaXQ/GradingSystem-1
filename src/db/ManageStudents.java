package db;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.*;
import pojo.*;

import antlr.collections.List;

public class ManageStudents {
	@SuppressWarnings({ "unchecked", "finally" })
	public ArrayList<Student> getStudents() {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	     Transaction tx = null;
	     List employees = null;
	      try{
	         tx = session.beginTransaction();
	         employees = (List) session.createQuery("FROM StudentInfo").list(); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	         return (ArrayList<Student>) employees;
	      }
	}
}
