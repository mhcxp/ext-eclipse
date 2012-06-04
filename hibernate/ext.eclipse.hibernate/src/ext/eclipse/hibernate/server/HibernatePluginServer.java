package ext.eclipse.hibernate.server;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import ext.eclipse.hibernate.configuration.MyConfiguration;
import ext.eclipse.hibernate.configurer.DBConfigurerFactory;
import ext.eclipse.hibernate.util.DomUtil;

public final class HibernatePluginServer {
	public final static HibernatePluginServer INSTANCE;
	static {
		INSTANCE = new HibernatePluginServer();
	}
	private SessionFactory sessionFactory;
	private String id;

	public void create(String id) {
		Document doc = null;
		if (sessionFactory != null)
			return;
		try {
			doc = DomUtil.getCfgXMLDocument(DBConfigurerFactory.INSTANCE
					.getDBConfigurer(id).toXML());

			Configuration config = new MyConfiguration().configure(doc);
			sessionFactory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		} finally {
			if (doc != null)
				doc.clearContent();
			this.id = id;
		}
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			create(id);
		return sessionFactory;
	}

	/**
	 * 执行hql
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> query(String hql) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Query query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> result = null;
		if (query == null) {
			result = new ArrayList<Object>();
		} else {
			result = query.list();
		}
		session.getTransaction().commit();

		return result;
	}

	/**
	 * 更新JavaBean，如果返回为null，则更新失败
	 * 
	 * @param bean
	 * @return
	 */
	public boolean update(Object bean) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		boolean success = false;
		try {
			session.update(bean);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();
		return success;
	}

	/**
	 * 保存JavaBean，如果返回为null，则保存失败
	 * 
	 * @param bean
	 * @return
	 */
	public Object save(Object bean) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Object id = null;
		try {
			session.saveOrUpdate(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();
		return id;
	}

	/**
	 * 删除JavaBean，如果返回为false，则 删除失败
	 * 
	 * @param bean
	 * @return
	 */
	public boolean drop(Object bean) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		boolean success = false;
		try {
			session.delete(bean);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();
		return success;
	}
}
