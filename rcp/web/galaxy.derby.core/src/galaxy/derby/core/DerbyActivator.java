package galaxy.derby.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DerbyActivator implements BundleActivator {

	private static BundleContext context;

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
		DerbyActivator.context = bundleContext;
		System.out.println("Derby Start!");
		// context.getDataFile("");
		// test();
	}

	/*
	 * 
	 */
	// private void test() {
	// String path = "";
	// String path = Platform.getInstanceLocation().getURL().getPath();
	// path = Platform.getInstallLocation().getURL().getPath();
	// System.out.println(path);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		DerbyActivator.context = null;
	}

}
