package ext.eclipse.hibernate.configurer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

import ext.eclipse.hibernate.configurer.constants.BeanConstants;

/**
 * JavaBean configurer factory
 * 
 * @author caiyu
 * 
 */
public class BeanConfigurerFactory {
	public final static BeanConfigurerFactory INSTANCE;
	private final Map<String, IHbmConfigurer> map = new HashMap<String, IHbmConfigurer>();
	static {
		INSTANCE = new BeanConfigurerFactory();
	}

	private BeanConfigurerFactory() {
		init();
	}

	private void init() {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(BeanConstants.EXTENSIONS_ID);
		if (extensionPoint == null)
			return;
		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement element : extension
					.getConfigurationElements()) {
				if (BeanConstants.PATH.equals(element.getName())) {
					handlePathExtension(element);
				} else if (BeanConstants.CLASS.equals(element.getName())) {
					handleClassExtension(element);
				}
			}
		}
	}

	/**
	 * Deal with "class" extension node
	 * 
	 * @param element
	 */
	private void handleClassExtension(IConfigurationElement element) {
		BeanConfigurer configurer = new BeanConfigurer();
		String name = element.getAttribute(BeanConstants.NAME);
		String table = element.getAttribute(BeanConstants.TABLE);
		configurer.setClazz(name);
		configurer.setTable(table);
		IConfigurationElement[] properties = element.getChildren();
		for (IConfigurationElement property : properties) {
			if (BeanConstants.LIST.equals(property.getName())) {
				BeanList bl = createListConfigurer(property);
				configurer.addProperty(bl);
			} else if (BeanConstants.ID.equals(property.getName())) {
				BeanProperty bp = createIdConfigurer(property);
				configurer.addProperty(bp);
			} else if (BeanConstants.PROPERTY.equals(property.getName())) {
				BeanProperty bp = createPropertyConfigurer(property);
				configurer.addProperty(bp);
			}
		}
		map.put(configurer.getClazz(), configurer);
	}

	/**
	 * Deal with "path" extension node
	 * 
	 * @param element
	 */
	private void handlePathExtension(IConfigurationElement element) {
		SAXReader reader = new SAXReader();
		BeanXMLConfigurer configurer = new BeanXMLConfigurer();
		String path = element.getAttribute(BeanConstants.PATH);
		String clz = element.getAttribute(BeanConstants.CLASS);
		Document doc = null;
		try {
			InputStream is = FileLocator.openStream(
					Platform.getBundle(element.getNamespaceIdentifier()),
					new Path(path), false);
			doc = reader.read(is);
			configurer.setElement(doc.getRootElement().createCopy());
		} catch (InvalidRegistryObjectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (doc != null)
				doc.clearContent();
			reader.resetHandlers();
		}
		map.put(clz, configurer);
	}

	private BeanList createListConfigurer(IConfigurationElement property) {
		BeanList bl = new BeanList();
		String name = property.getAttribute(BeanConstants.NAME);
		String table = property.getAttribute(BeanConstants.TABLE);
		String lazy = property.getAttribute(BeanConstants.LAZY);
		bl.setName(name);
		bl.setTable(table);
		if (lazy != null && "" != lazy)
			bl.setLazy(lazy);
		for (IConfigurationElement element : property.getChildren()) {
			if (BeanConstants.M2M.equals(element.getName())) {

			} else if (BeanConstants.KEY.equals(element.getName())) {
//				String column = element.getAttribute(BeanConstants.COLUMN);
			} else if (BeanConstants.INDEX.equals(element.getName())) {
			}
		}
		return bl;
	}

	private BeanProperty createPropertyConfigurer(IConfigurationElement property) {
		BeanProperty bp = new BeanProperty();
		String p_name = property.getAttribute(BeanConstants.NAME);
		String p_type = property.getAttribute(BeanConstants.TYPE);
		String p_column = property.getAttribute(BeanConstants.COLUMN);
		bp.setName(p_name);
		bp.setType(p_type);
		bp.setColumn(p_column);
		return bp;
	}

	private BeanProperty createIdConfigurer(IConfigurationElement property) {
		BeanProperty bp = new BeanProperty();
		IConfigurationElement[] generators = property
				.getChildren(BeanConstants.GENERATOR);
		bp.setPrimaryKey(true);
		if (generators.length == 1) {
			IConfigurationElement generator = generators[0];
			String g_class = generator.getAttribute(BeanConstants.CLASS);
			IDGenerator idg = new IDGenerator();
			idg.setClazz(g_class);
			bp.setGenerator(idg);
		}
		String p_name = property.getAttribute(BeanConstants.NAME);
		String p_type = property.getAttribute(BeanConstants.TYPE);
		String p_column = property.getAttribute(BeanConstants.COLUMN);
		bp.setName(p_name);
		bp.setType(p_type);
		bp.setColumn(p_column);
		return bp;
	}

	public Set<String> getAllKeys() {
		return map.keySet();
	}

	/**
	 * 鏍规嵁绫诲悕鏉ヨ幏寰桱avaBean閰嶇疆
	 * 
	 * @param cls
	 * @return
	 */
	public IHbmConfigurer getBeanConfigurer(String cls) {
		return map.get(cls);
	}

	public void reset() {
		map.clear();
		init();
	}
}
