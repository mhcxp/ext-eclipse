package mos.hibernate.manager.config;

/**
 * hibernate≈‰÷√µ•
 * 
 * @author caiyu
 * @date 2012-9-26 …œŒÁ10:31:53
 */

public interface IHbmConfig {
	String P_SESSION_FACTORY_ID = "sessionFactoryId";
	String P_DATABASE_FILE = "databaseFile";
	String P_MAPPING_FILE = "mappingFile";

	int database = 0x0;
	int mapping = 0x1;

	void setProperty(String key, Object value);

	Object getProperty(String key);

	int getType();
}
