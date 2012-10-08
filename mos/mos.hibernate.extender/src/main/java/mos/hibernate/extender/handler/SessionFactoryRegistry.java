package mos.hibernate.extender.handler;

import mos.hibernate.extender.util.HibernateBundleHelper;
import mos.hibernate.manager.config.IHbmConfig;

import org.osgi.framework.Bundle;

/**
 * @author caiyu
 * @date 2012-9-25 ÉÏÎç10:18:17
 */

public class SessionFactoryRegistry {
	private String sessionFactoryId;

	public void register(Bundle bundle) {
		// TODO Auto-generated method stub
		IHbmConfig databaseConfig = HibernateBundleHelper.getInstance()
				.getDatabaseConfiguration(bundle);
		IHbmConfig mappingConfig = HibernateBundleHelper.getInstance()
				.getMappingConfiguration(bundle);
		if (databaseConfig == null || mappingConfig == null)
			return;
		
		
	}

	public void unregister(Bundle bundle) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

}
