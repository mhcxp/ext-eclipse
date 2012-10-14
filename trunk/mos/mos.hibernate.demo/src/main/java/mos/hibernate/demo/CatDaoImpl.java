package mos.hibernate.demo;

import java.util.List;

import mos.hibernate.manager.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 * @author caiyu
 * @date 2012-10-14 обнГ4:32:25
 */

public class CatDaoImpl extends GenericDao<Cat> {

	public CatDaoImpl(String sessionFactoryId) {
		super(sessionFactoryId);
	}

	public Cat findByName(String name) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Expression.eq("name", name));
		List<Cat> cats = crit.list();
		if (cats == null || cats.size() == 0)
			return null;
		return cats.get(0);
	}

}
