package mos.hibernate.manager.config.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mos.hibernate.manager.config.IHbmConfig;

/**
 * @author caiyu
 * @date 2012-9-29 ионГ11:05:33
 */

public abstract class AbstractConfig implements IHbmConfig {
	protected Map<String, Object> properties;

	AbstractConfig() {
		properties = new ConcurrentHashMap<String, Object>();
	}

	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

	public Object getProperty(String key) {
		return properties.get(key);
	}
}
