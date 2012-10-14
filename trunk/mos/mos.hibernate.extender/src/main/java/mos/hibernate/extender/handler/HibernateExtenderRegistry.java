package mos.hibernate.extender.handler;

import java.util.HashSet;
import java.util.Set;

import mos.hibernate.extender.util.HibernateBundleHelper;
import mos.hibernate.manager.HbmConfigContainer;
import mos.hibernate.manager.HbmConfigContainerManager;
import mos.hibernate.manager.config.IHbmConfig;

import org.osgi.framework.Bundle;

/**
 * @author caiyu
 * @date 2012-9-25 上午10:18:17
 */

public final class HibernateExtenderRegistry {
	private Set<Bundle> activatedBundles;

	public HibernateExtenderRegistry() {
		this.activatedBundles = new HashSet<Bundle>();
	}

	public synchronized void register(Bundle bundle) {
		synchronized (this.activatedBundles) {
			if (activatedBundles.contains(bundle))
				return;
		}
		Set<IHbmConfig> databaseConfigSet = HibernateBundleHelper.getInstance()
				.getDatabaseConfiguration(bundle);
		Set<IHbmConfig> mappingConfigSet = HibernateBundleHelper.getInstance()
				.getMappingConfiguration(bundle);
		if (databaseConfigSet != null)
			for (IHbmConfig databaseConfig : databaseConfigSet) {
				String databaseId = (String) databaseConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
				if (databaseId != null) {
					HbmConfigContainer container = HbmConfigContainerManager
							.getInstance().createHbmConfigContainer(databaseId);
					container.setDatabaseConfig(databaseConfig);
				}
			}
		if (mappingConfigSet != null)
			for (IHbmConfig mappingConfig : mappingConfigSet) {
				String databaseId = (String) mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
				if (databaseId != null) {
					HbmConfigContainer container = HbmConfigContainerManager
							.getInstance().getHbmConfigContainer(databaseId);
					container.addMappingConfig(mappingConfig);
				}
			}
		activatedBundles.add(bundle);
	}

	/**
	 * 注销bundle内的数据库配置单
	 * 
	 * @param bundle
	 */
	public synchronized void unregister(Bundle bundle) {
		synchronized (this.activatedBundles) {
			if (!activatedBundles.contains(bundle))
				return;
			activatedBundles.remove(bundle);
		}
		Set<IHbmConfig> databaseConfigSet = HibernateBundleHelper.getInstance()
				.getDatabaseConfiguration(bundle);
		Set<IHbmConfig> mappingConfigSet = HibernateBundleHelper.getInstance()
				.getMappingConfiguration(bundle);
		if (databaseConfigSet != null)
			for (IHbmConfig databaseConfig : databaseConfigSet) {
				String databaseId = (String) databaseConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
				if (databaseId != null) {
					if (HbmConfigContainerManager.getInstance()
							.hasHbmConfigContainer(databaseId)) {
						HbmConfigContainerManager.getInstance().destroy(
								databaseId);
					}
				}
			}
		if (mappingConfigSet != null)
			for (IHbmConfig mappingConfig : mappingConfigSet) {
				String databaseId = (String) mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
				if (databaseId != null) {
					if (HbmConfigContainerManager.getInstance()
							.hasHbmConfigContainer(databaseId)) {
						HbmConfigContainer container = HbmConfigContainerManager
								.getInstance()
								.getHbmConfigContainer(databaseId);
						if (container != null)
							container.removeMappingConfig(mappingConfig);
					}
				}
			}
	}

}
