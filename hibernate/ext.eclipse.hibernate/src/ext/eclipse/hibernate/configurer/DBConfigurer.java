package ext.eclipse.hibernate.configurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

/**
 * 
 * @author caiyu
 * 
 */
public class DBConfigurer implements IHbmConfigurer {

	private String id;
	private List<MappingConfigurer> mappingList = new ArrayList<MappingConfigurer>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public final static String KEY_URL = "connection.url";
	public final static String KEY_DRIVER = "connection.driver_class";
	public final static String KEY_USERNAME = "connection.username";
	public final static String KEY_PASSWORD = "connection.password";
	public final static String KEY_POOL_SIZE = "connection.pool_size";
	public final static String KEY_DIALECT = "dialect";
	public final static String KEY_SESSION_CLASS = "current_session_context_class";
	public final static String KEY_SHOW_SQL = "show_sql";
	public final static String KEY_HBM2DDL_AUTO = "hbm2ddl.auto";
	public final static String KEY_PROVIDER = "cache.provider_class";
	public static final String[] SORT = new String[] { KEY_DRIVER, KEY_URL,
			KEY_USERNAME, KEY_PASSWORD, KEY_POOL_SIZE, KEY_DIALECT,
			KEY_SESSION_CLASS, KEY_PROVIDER, KEY_SHOW_SQL, KEY_HBM2DDL_AUTO };

	private final Map<String, String> map = new ConcurrentHashMap<String, String>();

	public String getValue(String key) {
		return map.get(key);
	}

	public void setValue(String key, String value) {
		map.put(key, value);
	}

	public void addMappingConfigurer(MappingConfigurer mapping) {
		mappingList.add(mapping);
	}

	public void removeMappingConfigurer(MappingConfigurer mapping) {
		mappingList.remove(mapping);
	}

	@Override
	public Element toXML() {
		// TODO Auto-generated method stub
		Element root = DocumentFactory.getInstance().createElement(
				"session-factory");
		for (String key : SORT) {
			if (map.get(key) == null)
				continue;
			root.addElement("property").addAttribute("name", key)
					.setText(map.get(key));
		}
		for (MappingConfigurer mc : mappingList) {
			root.add(mc.toXML());
		}
		return root;
	}
}
