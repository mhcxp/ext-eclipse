package mos.hibernate.manager;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author caiyu
 * @date 2012-9-25 ионГ10:35:10
 */

public class Activator implements BundleActivator {
	private BundleContext context;

	public BundleContext getContext() {
		return context;
	}

	public Bundle getBundle() {
		return context.getBundle();
	}

	public void start(BundleContext context) throws Exception {
		this.context = context;
	}

	public void stop(BundleContext context) throws Exception {
		this.context = null;
	}

}
