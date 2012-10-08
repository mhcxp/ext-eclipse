package mos.hibernate.manager.config;

import mos.hibernate.manager.config.impl.DatabaseConfig;
import mos.hibernate.manager.config.impl.MappingConfig;

/**
 * Hibernate���ù����������ṩ���ݿ����ú�ӳ������
 * 
 * @author caiyu
 * @date 2012-9-26 ����10:51:54
 */

public final class HibernateConfigFactory {
	private static HibernateConfigFactory instance;

	private HibernateConfigFactory() {

	}

	public static HibernateConfigFactory getInstance() {
		if (instance == null)
			instance = new HibernateConfigFactory();
		return instance;
	}

	public DatabaseConfig getDatabaseConfig() {
		return new DatabaseConfig();
	}

	public MappingConfig getMappingConfig() {
		return new MappingConfig();
	}
}
