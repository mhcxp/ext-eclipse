package ext.eclipse.hibernate.server;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import ext.eclipse.hibernate.configuration.MyConfiguration;
import ext.eclipse.hibernate.configurer.DBConfigurerFactory;
import ext.eclipse.hibernate.util.DomUtil;
/**
 * 
 * @author caiyu
 * 
 */
public final class HibernatePluginServer {
	public final static HibernatePluginServer INSTANCE;
	static {
		INSTANCE = new HibernatePluginServer();
	}
	private SessionFactory sessionFactory;
	private String databaseId;

	/**
	 * 根据数据库ID创建SessionFactory
	 * 
	 * @param database_id
	 */
	public void createSessionFactory(String databaseId) {
		Document doc = null;
		if (sessionFactory != null)
			return;
		try {
			doc = DomUtil.getCfgXMLDocument(DBConfigurerFactory.INSTANCE
					.getDBConfigurer(databaseId).toXML());
			Configuration config = new MyConfiguration().configure(doc);
			sessionFactory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		} finally {
			if (doc != null)
				doc.clearContent();
			this.databaseId = databaseId;
		}
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			createSessionFactory(databaseId);
		return sessionFactory;
	}

	/**
	 * 执行hql
	 * 
	 * @param hql
	 * @return
	 */
	public List<?> query(String hql) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Query query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<?> result = query == null ? new ArrayList<Object>() : query.list();
		session.getTransaction().commit();

		return result;
	}

	/**
	 * 执行SQL
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> querySQL(String sql) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		SQLQuery query = null;
		try {
			query = session.createSQLQuery(sql);
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
	 * 执行更新\删除SQL
	 * 
	 * @param sql
	 * @return
	 */
	public int updateOrDeleteSQL(String sql) {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SQLQuery query = null;
		try {
			query = session.createSQLQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int i = query.executeUpdate();
		session.getTransaction().commit();
		session = null;
		return i;
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
			id = session.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			session.getTransaction().commit();
		} catch (JDBCException e) {
			id = null;
			session.getTransaction().rollback();
		}
		return id;
	}

	/**
	 * 删除JavaBean，如果返回为false，则 删除失败
	 * 
	 * @param bean
	 * @return
	 */
	public boolean delete(Object bean) {
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

	public List<?> query(String hql, int offset, int length) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		Query query = null;
		try {
			query = session.createQuery(hql);
			query.setFirstResult(offset);
			query.setMaxResults(length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<?> result = null;
		if (query == null) {
			result = new ArrayList<Object>();
		} else {
			result = query.list();
		}
		session.getTransaction().commit();

		return result;
	}

	public long getCount(Class<?> clazz) {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		long count = (Long) session.createQuery(
				"select count(*) from " + clazz.getSimpleName()).uniqueResult();
		session.getTransaction().commit();
		return count;
	}

	public boolean saveOrUpdate(Object bean) {
		Session session = getSessionFactory().getCurrentSession();

		session.beginTransaction();
		boolean success = false;
		try {
			session.saveOrUpdate(bean);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();
		return success;
	}
}
