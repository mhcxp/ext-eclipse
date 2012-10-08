package mos.hibernate.manager.config;

/**
 * @author caiyu
 * @date 2012-9-26 ионГ10:31:53
 */

public interface IHbmConfig {
	String P_DATABASE_ID = "databaseId";

	void setProperty(String key, Object value);

	Object getProperty(String key);
}
