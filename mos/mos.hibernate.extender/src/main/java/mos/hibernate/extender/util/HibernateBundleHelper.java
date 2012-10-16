package mos.hibernate.extender.util;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import mos.hibernate.extender.IHibernateConstants;
import mos.hibernate.manager.config.HibernateConfigFactory;
import mos.hibernate.manager.config.IHbmConfig;

import org.osgi.framework.Bundle;

/**
 * ���ͷ��bundle header���������֣����Խ������ݿ����ú�ӳ������
 * 
 * @author caiyu
 * @date 2012-9-26 ����10:22:05
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
	 * ��bundle��Menifest.mf�ļ��л�ȡ���ݿ����õ�
	 * 
	 * @param bundle
	 * @return
	 */
	public Set<IHbmConfig> getDatabaseConfiguration(Bundle bundle) {

		if (bundle == null)
			return null;

		String header = (String) bundle.getHeaders().get(
				IHibernateConstants.DATABASE_CONFIGURATION);
		if (header == null)
			return null;

		Set<IHbmConfig> databaseConfigSet = new HashSet<IHbmConfig>();

		String clauses[] = header.split(",");
		for (int i = 0; i < clauses.length; i++) {
			String parts[] = clauses[i].trim().split("\\s*;\\s*");
			if (parts.length == 2) {
				IHbmConfig databaseConfig = HibernateConfigFactory
						.getInstance().getDatabaseConfig();
				databaseConfig.setProperty(IHbmConfig.P_SESSION_FACTORY_ID,
						parts[0].trim());
				URL url = bundle.getResource(parts[1].trim());
				databaseConfig.setProperty(IHbmConfig.P_DATABASE_FILE, url);
				databaseConfigSet.add(databaseConfig);
			}
		}

		return databaseConfigSet;
	}

	/**
	 * ��bundle��Menifest.mf�ļ��л�ȡӳ�����õ�
	 * 
	 * @param bundle
	 * @return
	 */
	public Set<IHbmConfig> getMappingConfiguration(Bundle bundle) {

		if (bundle == null)
			return null;
		String header = (String) bundle.getHeaders().get(
				IHibernateConstants.MAPPING_CONFIGURATION);
		if (header == null)
			return null;
		Set<IHbmConfig> mappingConfigSet = new HashSet<IHbmConfig>();
		String clauses[] = header.split(",");
		for (int i = 0; i < clauses.length; i++) {
			String parts[] = clauses[i].trim().split("\\s*;\\s*");
			if (parts.length == 2) {
				IHbmConfig mappingConfig = HibernateConfigFactory.getInstance()
						.getMappingConfig();
				mappingConfig.setProperty(IHbmConfig.P_SESSION_FACTORY_ID,
						parts[0].trim());

				try {
					Class<?> clazz = bundle.loadClass(parts[1]);
					mappingConfig.setProperty(IHbmConfig.P_CLASSNAME, clazz);
					mappingConfigSet.add(mappingConfig);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		return mappingConfigSet;
	}
}
