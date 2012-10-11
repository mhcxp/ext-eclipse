package mos.hibernate.manager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.dom4j.Document;
import org.hibernate.SessionFactory;

import mos.hibernate.manager.config.IHbmConfig;
import mos.hibernate.manager.config.impl.DatabaseConfig;
import mos.hibernate.manager.config.impl.MappingConfig;

/**
 * hibernate配置单容器
 * 
 * @author caiyu
 * @date 2012-10-10 下午3:24:21
 */

public final class HbmConfigContainer {
	private String sessionFatoryId;
	private IHbmConfig databaseConfig;
	private Set<IHbmConfig> mappingConfigSet;

	private SessionFactory sessionFactory;
	private boolean dirty;

	HbmConfigContainer(String databaseId) {
		mappingConfigSet = Collections
				.synchronizedSet(new HashSet<IHbmConfig>());
		this.sessionFatoryId = databaseId;
		dirty = true;
	}

	public boolean isDirty() {
		return dirty;
	}

	public String getDatabaseId() {
		return sessionFatoryId;
	}

	/**
	 * 
	 * @param databaseConfig
	 *            实例必须为DatabaseConfig，且databaseId一致
	 */
	public void setDatabaseConfig(IHbmConfig databaseConfig) {
		if (databaseConfig != null) {
			Object id = databaseConfig
					.getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
			throw new IllegalArgumentException("该数据库配置单的数据库ID [" + id
					+ "] 已被使用，请检查配置文件");
		}
		/**
		 * 验证databaseConfig合法性
		 */
		if (databaseConfig instanceof DatabaseConfig
				&& sessionFatoryId.equals(databaseConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			this.databaseConfig = databaseConfig;
		} else {
			throw new IllegalArgumentException("参数databaseConfig内容异常");
		}
		dirty = true;
	}

	public IHbmConfig getDatabaseConfig() {
		return databaseConfig;
	}

	/**
	 * 
	 * @param mappingConfig
	 *            实例必须为MappingConfig，且databaseId一致
	 */
	public void addMappingConfig(IHbmConfig mappingConfig) {
		/**
		 * 验证映射配置的合法性
		 */
		if (mappingConfig instanceof MappingConfig
				&& !mappingConfigSet.contains(mappingConfig)
				&& sessionFatoryId.equals(mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			mappingConfigSet.add(mappingConfig);
		} else {
			throw new IllegalArgumentException("参数mappingConfig内容异常");
		}
		dirty = true;
	}

	public void removeMappingConfig(IHbmConfig mappingConfig) {
		if (mappingConfig instanceof MappingConfig
				&& mappingConfigSet.contains(mappingConfig)
				&& sessionFatoryId.equals(mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			mappingConfigSet.remove(mappingConfig);
		} else {
			throw new IllegalArgumentException("当前容器内没有符合条件的映射配置单："
					+ mappingConfig);
		}
		dirty = true;
	}

	public Set<IHbmConfig> getMappingConfigs() {
		if (mappingConfigSet == null)
			return null;
		return new HashSet<IHbmConfig>(mappingConfigSet);
	}

	public SessionFactory getSessionFactory() {
		if (dirty || sessionFactory == null) {
			if (databaseConfig == null)
				throw new RuntimeException("没有可用的数据库配置");

			dirty = false;
		}
		return sessionFactory;
	}

	public void dispose() {
		if (mappingConfigSet != null) {
			mappingConfigSet.clear();
			mappingConfigSet = null;
		}
	}

	public Document toXML() {
		// TODO Auto-generated method stub
		return null;
	}
}
