package mos.hibernate.manager;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * @author caiyu
 * @date 2012-10-14 下午4:06:03
 */

public abstract class GenericDao<T> {
	private String sessionFactoryId;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDao(String sessionFactoryId) {
		this.sessionFactoryId = sessionFactoryId;
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	protected Class<T> getPersistentClass() {
		return persistentClass;
	}

	protected Session getSession() {
		SessionFactory sessionFactory = HbmConfigContainerManager.getInstance()
				.getSessionFactory(sessionFactoryId);
		if (sessionFactory == null)
			throw new IllegalStateException("DAO异常：SessionFactory ["
					+ this.sessionFactoryId + "] 未注册 ");
		Session session = sessionFactory.getCurrentSession();
		if (session == null)
			throw new IllegalStateException("DAO异常：SessionFactory ["
					+ this.sessionFactoryId + "] 的Session未初始化 ");
		return session;
	}

}
