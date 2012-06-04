package ext.eclipse.hibernate.configurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import ext.eclipse.hibernate.configurer.constants.DBConstants;
import ext.eclipse.hibernate.configurer.constants.MappingConstants;

/**
 * 数据库配置组装工厂类
 * 
 * @author caiyu
 * 
 */
public class DBConfigurerFactory {
	public final static DBConfigurerFactory INSTANCE;

	private final Map<String, DBConfigurer> dbmap = new HashMap<String, DBConfigurer>();
	static {
		INSTANCE = new DBConfigurerFactory();
	}

	private DBConfigurerFactory() {
		init();
	}

	private void init() {
		/* 数据库配置 */
		initDataBaseConfiguration();

		/* 映射配置 */
		initMappingDataBaseConfiguration();
	}

	private void initMappingDataBaseConfiguration() {
		// TODO Auto-generated method stub
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(MappingConstants.EXTENSIONS_ID);
		if (extensionPoint == null)
			return;
		IExtension[] extensions = extensionPoint.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] elements = extension
					.getConfigurationElements();
			for (IConfigurationElement element : elements) {
				MappingConfigurer configurer = new MappingConfigurer();
				String dbId = element.getAttribute(MappingConstants.DB_ID);
				configurer.setDbID(dbId);
				configurer.setBeanID(element
						.getAttribute(MappingConstants.BEAN_ID));

				DBConfigurer dbConfigurer = dbmap.get(dbId);
				if (dbConfigurer != null) {
					dbConfigurer.addMappingConfigurer(configurer);
				}
			}
		}
	}

	/**
	 * 数据库配置
	 */
	private void initDataBaseConfiguration() {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(DBConstants.EXTENSIONS_ID);
		if (extensionPoint == null)
			return;
		IExtension[] extensions = extensionPoint.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] elements = extension
					.getConfigurationElements();
			for (IConfigurationElement element : elements) {
				DBConfigurer configurer = new DBConfigurer();
				configurer.setId(element.getAttribute(DBConstants.DB_ID));
				for (String key : DBConfigurer.SORT) {
					String value = element.getAttribute(key);
					if (value == null)
						continue;
					configurer.setValue(key, value);
				}
				dbmap.put(configurer.getId(), configurer);
			}
		}
	}

	public Set<String> getAllKeys() {
		return dbmap.keySet();
	}

	public DBConfigurer getDBConfigurer(String key) {
		return dbmap.get(key);
	}

	/**
	 * Parse Configurer to Element
	 * 
	 * @param key
	 * @return
	 */
	public Element getDBConfigurerXML(String key) {
		return getDBConfigurer(key).toXML();
	}

	public void reset() {
		dbmap.clear();
		init();
	}
}
