package mos.hibernate.extender.util;

import mos.hibernate.extender.IHibernateConstants;
import mos.hibernate.manager.config.HibernateConfigFactory;
import mos.hibernate.manager.config.IHbmConfig;

import org.osgi.framework.Bundle;

/**
 * 插件头（bundle header）解析助手，可以解析数据库配置和映射配置
 * 
 * @author caiyu
 * @date 2012-9-26 上午10:22:05
 */

public class HibernateBundleHelper {
	private static HibernateBundleHelper instance;

	private HibernateBundleHelper() {
	}

	public static HibernateBundleHelper getInstance() {
		if (instance == null)
			instance = new HibernateBundleHelper();
		return instance;
	}

	/**
	 * 从bundle的Menifest.mf文件中获取数据库配置信息
	 * 
	 * @param bundle
	 * @return
	 */
	public IHbmConfig getDatabaseConfiguration(Bundle bundle) {

		IHbmConfig databaseConfig = HibernateConfigFactory.getInstance()
				.getDatabaseConfig();
		if (bundle == null)
			return databaseConfig;

		String header = (String) bundle.getHeaders().get(
				IHibernateConstants.DATABASE_CONFIGURATION);
		if (header == null)
			return databaseConfig;

		String clauses[] = header.split(",");
		for (int i = 0; i < clauses.length; i++) {
			String parts[] = clauses[i].trim().split("\\s*;\\s*");
			if (parts.length == 2) {
				databaseConfig.setProperty(IHbmConfig.P_DATABASE_ID, parts[0]);
				databaseConfig.setProperty(IHbmConfig.P_DATABASE_ID, parts[1]);
			}
			// map.put(parts[0], parts[1]);
		}

		return databaseConfig;
	}

	public IHbmConfig getMappingConfiguration(Bundle bundle) {
		if (bundle == null)
			return null;
		Object obj = bundle.getHeaders().get(
				IHibernateConstants.MAPPING_CONFIGURATION);
		if (obj == null)
			return null;

		System.out.println("mapping: " + obj);

		IHbmConfig mappingConfig = HibernateConfigFactory.getInstance()
				.getMappingConfig();

		return mappingConfig;
	}
}
