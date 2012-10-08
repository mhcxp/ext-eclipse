package mos.hibernate.manager.config;

import mos.hibernate.manager.config.impl.DatabaseConfig;
import mos.hibernate.manager.config.impl.MappingConfig;

/**
 * Hibernate配置工厂，负责提供数据库配置和映射配置
 * 
 * @author caiyu
 * @date 2012-9-26 上午10:51:54
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
