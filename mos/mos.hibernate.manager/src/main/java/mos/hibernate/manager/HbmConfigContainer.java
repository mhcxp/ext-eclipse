package mos.hibernate.manager;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mos.hibernate.manager.config.IHbmConfig;
import mos.hibernate.manager.config.impl.DatabaseConfig;
import mos.hibernate.manager.config.impl.MappingConfig;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.hibernate.SessionFactory;

/**
 * hibernate���õ�����
 * 
 * @author caiyu
 * @date 2012-10-10 ����3:24:21
 */

public final class HbmConfigContainer {
	private String sessionFatoryId;
	private IHbmConfig databaseConfig;
	private Map<String, IHbmConfig> mappingConfigMap;

	private SessionFactory sessionFactory;
	private boolean dirty;

	HbmConfigContainer(String databaseId) {
		mappingConfigMap = Collections
				.synchronizedMap(new HashMap<String, IHbmConfig>());
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
		// if (databaseConfig != null) {
		// Object id = databaseConfig
		// .getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
		// // throw new IllegalArgumentException("�����ݿ����õ������ݿ�ID [" + id
		// // + "] �ѱ�ʹ�ã����������ļ�");
		// }
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
				&& !mappingConfigMap.containsKey(mappingConfig)
				&& sessionFatoryId.equals(mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			String mappingId = (String) mappingConfig
					.getProperty(IHbmConfig.P_CLASSNAME);
			if (mappingId == null)
				throw new IllegalArgumentException(
						"����mappingConfig�����쳣,����[IHbmConfig.P_CLASSNAME]����Ϊnull");
			else
				mappingConfigMap.put(mappingId, mappingConfig);
		} else {
			throw new IllegalArgumentException("����mappingConfig�����쳣");
		}
		dirty = true;
	}

	public void removeMappingConfig(IHbmConfig mappingConfig) {
		if (mappingConfig instanceof MappingConfig
				&& mappingConfigMap.containsKey(mappingConfig)
				&& sessionFatoryId.equals(mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			String mappingId = (String) mappingConfig
					.getProperty(IHbmConfig.P_CLASSNAME);
			if (mappingId == null)
				throw new IllegalArgumentException(
						"����mappingConfig�����쳣,����[IHbmConfig.P_CLASSNAME]����Ϊnull");
			else
				mappingConfigMap.remove(mappingId);
		} else {
			throw new IllegalArgumentException("��ǰ������û�з���������ӳ�����õ���"
					+ mappingConfig);
		}
		dirty = true;
	}

	public HashMap<String, IHbmConfig> getMappingConfigs() {
		if (mappingConfigMap == null)
			return null;
		return new HashMap<String, IHbmConfig>(mappingConfigMap);
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
		if (mappingConfigMap != null) {
			mappingConfigMap.clear();
			mappingConfigMap = null;
		}
	}

	public Document toXML() {
		// TODO Auto-generated method stub
		if (databaseConfig == null)
			return null;
		URL dbConfigFileURL = (URL) databaseConfig
				.getProperty(IHbmConfig.P_DATABASE_FILE);
		if (dbConfigFileURL == null)
			return null;
		SAXReader reader = null;
		Document doc = null;
		try {
			reader = new SAXReader();
			doc = reader.read(dbConfigFileURL.openStream());
			if (mappingConfigMap != null) {
				for (IHbmConfig mappingConfig : mappingConfigMap.values()) {
					doc.addElement("mapping").addAttribute(
							"resource",
							mappingConfig
									.getProperty(IHbmConfig.P_MAPPING_FILE)
									.toString());
				}
			}
			System.out.println(doc.asXML());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.resetHandlers();
		}
		return doc;
	}
}
