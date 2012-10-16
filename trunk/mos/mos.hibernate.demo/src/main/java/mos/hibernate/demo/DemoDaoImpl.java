package mos.hibernate.demo;

import mos.hibernate.manager.GenericDao;

import org.hibernate.Transaction;

/**
 * @author caiyu
 * @date 2012-10-15 обнГ4:06:11
 */

public class DemoDaoImpl extends GenericDao<DemoBean> {

	public DemoDaoImpl(String sessionFactoryId) {
		super(sessionFactoryId);
		// TODO Auto-generated constructor stub
	}

	public DemoBean findByName(String name) {
		Transaction tx = getSession().beginTransaction();
		tx.commit();
		return null;
	}
}
