package ext.eclipse.hibernate.server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ext.eclipse.hibernate.configuration.MyConfiguration;

public class HibernateCommonServer {
	private static SessionFactory sessionFactory;

	private static final void create() {
		try {
			
			Configuration config = new MyConfiguration()
					.configure("example/example.cfg.xml");
			sessionFactory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		} finally {
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			create();
		return sessionFactory;
	}
}
