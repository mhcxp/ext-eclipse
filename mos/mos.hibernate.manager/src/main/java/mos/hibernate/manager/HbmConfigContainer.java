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
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.ThreadLocalSessionContext;

/**
 * hibernate配置单容器
 * 
 * @author caiyu
 * @date 2012-10-10 下午3:24:21
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
	 *            实例必须为DatabaseConfig，且databaseId一致
	 */
	public void setDatabaseConfig(IHbmConfig databaseConfig) {
		// if (databaseConfig != null) {
		// Object id = databaseConfig
		// .getProperty(IHbmConfig.P_SESSION_FACTORY_ID);
		// // throw new IllegalArgumentException("该数据库配置单的数据库ID [" + id
		// // + "] 已被使用，请检查配置文件");
		// }
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
				&& !mappingConfigMap.containsKey(mappingConfig)
				&& sessionFatoryId.equals(mappingConfig
						.getProperty(IHbmConfig.P_SESSION_FACTORY_ID))) {
			String mappingId = (String) mappingConfig
					.getProperty(IHbmConfig.P_CLASSNAME);
			if (mappingId == null)
				throw new IllegalArgumentException(
						"参数mappingConfig内容异常,属性[IHbmConfig.P_CLASSNAME]不能为null");
			else
				mappingConfigMap.put(mappingId, mappingConfig);
		} else {
			throw new IllegalArgumentException("参数mappingConfig内容异常");
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
						"参数mappingConfig内容异常,属性[IHbmConfig.P_CLASSNAME]不能为null");
			else
				mappingConfigMap.remove(mappingId);
		} else {
			throw new IllegalArgumentException("当前容器内没有符合条件的映射配置单："
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
		if (this.isDirty())
			return createSessionFactory();
		return sessionFactory;
	}

	private void close() {
		if (sessionFactory != null)
			sessionFactory.close();
	}

	private SessionFactory createSessionFactory() {
		this.close();
		if (sessionFactory == null) {
			if (databaseConfig == null)
				throw new RuntimeException("没有可用的数据库配置");
			Document doc = toXML();
			if (doc != null) {
				Configuration hbmConfig = new MosHbmConfiguration()
						.configure(doc);
				sessionFactory = hbmConfig.buildSessionFactory();
				hbmConfig.setProperty("hibernate.current_session_context_class", ThreadLocalSessionContext.class.getName());
				doc.clearContent();
				dirty = false;
			}
		}
		return sessionFactory;
	}

	public void dispose() {
		if (mappingConfigMap != null) {
			mappingConfigMap.clear();
			mappingConfigMap = null;
		}
	}

	private Document toXML() {
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

			Element root = doc.getRootElement();
			if (mappingConfigMap != null) {
				for (IHbmConfig mappingConfig : mappingConfigMap.values()) {
					root.addElement("mapping").addAttribute(
							"resource",
							mappingConfig
									.getProperty(IHbmConfig.P_MAPPING_FILE)
									.toString());
				}
			}
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
