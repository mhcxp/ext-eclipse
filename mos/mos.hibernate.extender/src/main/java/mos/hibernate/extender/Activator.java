package mos.hibernate.extender;

import mos.hibernate.extender.handler.HibernateExtenderListener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author caiyu
 * @date 2012-9-25 ÉÏÎç10:27:29
 */

public class Activator implements BundleActivator {
	private HibernateExtenderListener listener = new HibernateExtenderListener();

	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		listener.activate(context);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		listener.deactivate(context);
	}

}
