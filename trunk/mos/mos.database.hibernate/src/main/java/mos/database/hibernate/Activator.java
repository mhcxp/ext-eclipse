package mos.database.hibernate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("\n\n--------\n\nHibernate start! \n\n-------\n\n");
		// for (Bundle bundle : context.getBundles()) {
		// System.out.println(bundle.getBundleId() + " : "
		// + bundle.getSymbolicName());
		// }
		//
		// ServiceReference reference = context
		// .getServiceReference(PackageAdmin.class.getName());
		// PackageAdmin packageAdmin = (PackageAdmin) context
		// .getService(reference);
		//
		// Bundle[] bundles = packageAdmin.getBundles("mos.database.hsqldb",
		// null);
		// if (bundles != null) {
		// for (Bundle bundle : bundles) {
		// System.out.println("Bundle " + bundle.getBundleId() + " : "
		// + bundle.getSymbolicName());
		// }
		// } else
		// System.out.println("\n\nbundles == null\n\n");
		// // 加载数据库驱动
		// context.getBundle().loadClass("org.hsqldb.jdbcDriver");
		// // context.getBundle().get
		//
		// Configuration config = new Configuration();
		// config.configure("/config/hibernate.cfg.xml");
		// SessionFactory sf = config.buildSessionFactory();
		// Session session = sf.getCurrentSession();
		// session.beginTransaction();
		//
		// AdminImpl admin = new AdminImpl();
		// admin.setName("name");
		// admin.setPassword("pwd");
		// session.save(admin);
		// session.getTransaction().commit();

		// System.out.println(admin.getId());
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
