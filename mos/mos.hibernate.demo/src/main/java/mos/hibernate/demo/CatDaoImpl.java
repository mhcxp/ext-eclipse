package mos.hibernate.demo;

import java.util.List;

import mos.hibernate.manager.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

/**
 * @author caiyu
 * @date 2012-10-14 ÏÂÎç4:32:25
 */

public class CatDaoImpl extends GenericDao<Cat> {

	public CatDaoImpl(String sessionFactoryId) {
		super(sessionFactoryId);
	}

	public boolean addCat(Cat cat) {
		if (cat == null)
			return false;
		Transaction tx = getSession().beginTransaction();
		getSession().save(cat);
		tx.commit();
		return true;
	}

	public Cat findByName(String name) {
		Transaction tx = getSession().beginTransaction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Expression.eq("name", name));
		@SuppressWarnings("unchecked")
		List<Cat> cats = crit.list();
		tx.commit();
		if (cats == null || cats.size() == 0)
			return null;
		return cats.get(0);
	}

}
