package mos.hibernate.extender.handler;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

/**
 * @author caiyu
 * @date 2012-9-25 ����10:28:01
 */

public class HibernateExtenderListener implements BundleListener {
	private HibernateExtenderRegistry sessionFatotoryRegistry;

	public void bundleChanged(BundleEvent event) {
		// TODO Auto-generated method stub
		switch (event.getType()) {
		case BundleEvent.STARTED:
			sessionFatotoryRegistry.register(event.getBundle());
			break;
		case BundleEvent.STOPPING:
			sessionFatotoryRegistry.unregister(event.getBundle());
			break;
		}
	}

	public void activate(BundleContext context) {
		context.addBundleListener(this);
		sessionFatotoryRegistry = new HibernateExtenderRegistry();
		for (Bundle bundle : context.getBundles()) {
			sessionFatotoryRegistry.register(bundle);
		}
	}

	public void deactivate(BundleContext context) {
		context.removeBundleListener(this);
	}
}
