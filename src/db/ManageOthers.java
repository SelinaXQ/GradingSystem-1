package db;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import antlr.collections.List;
import pojo.Account;

public class ManageOthers {
	public ArrayList<Account> getAccount(String userName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List accs = null;
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("username", userName));
		accs = (List) criteria.list();
		return (ArrayList<Account>) accs;
	}
}
