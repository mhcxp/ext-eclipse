package mos.database.hsqldb;

import mos.database.hsqldb.server.DBServer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author caiyu
 * 
 */
public class Activator implements BundleActivator {
	private static BundleContext context;

	public static final String BUNDLE_ID = "mos.database.hsqldb";

	public static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		DBServer.start();
	}

	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
		DBServer.stop();
	}

}
