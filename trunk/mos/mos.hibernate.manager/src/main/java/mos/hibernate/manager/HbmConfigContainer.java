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
 * hibernate���õ�����
 * 
 * @author caiyu
 * @date 2012-10-10 ����3:24:21
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
	 *            ʵ������ΪDatabaseConfig����databaseIdһ��
	 */
	public void setDatabaseConfig(IHbmConfig databaseConfig) {
		if (databaseConfig != null) {
			Object id = databaseConfig
					.getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
			throw new IllegalArgumentException("�����ݿ����õ������ݿ�ID [" + id
					+ "] �ѱ�ʹ�ã����������ļ�");
		}
		/**
		 * ��֤databaseConfig�Ϸ���
		 */
		if (databaseConfig instanceof DatabaseConfig
				&& sessionFatoryId.equals(databaseConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			this.databaseConfig = databaseConfig;
		} else {
			throw new IllegalArgumentException("����databaseConfig�����쳣");
		}
		dirty = true;
	}

	public IHbmConfig getDatabaseConfig() {
		return databaseConfig;
	}

	/**
	 * 
	 * @param mappingConfig
	 *            ʵ������ΪMappingConfig����databaseIdһ��
	 */
	public void addMappingConfig(IHbmConfig mappingConfig) {
		/**
		 * ��֤ӳ�����õĺϷ���
		 */
		if (mappingConfig instanceof MappingConfig
				&& !mappingConfigSet.contains(mappingConfig)
				&& sessionFatoryId.equals(mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			mappingConfigSet.add(mappingConfig);
		} else {
			throw new IllegalArgumentException("����mappingConfig�����쳣");
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
			throw new IllegalArgumentException("��ǰ������û�з���������ӳ�����õ���"
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
				throw new RuntimeException("û�п��õ����ݿ�����");

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
