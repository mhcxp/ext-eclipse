package ext.eclipse.db.hsqldb;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import ext.eclipse.db.server.DBServer;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator {

	private static BundleContext context;

	public static final String BUNDLE_ID = "ext.eclipse.db.hsqldb";

	public static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		DBServer.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		DBServer.stop();
	}

}
