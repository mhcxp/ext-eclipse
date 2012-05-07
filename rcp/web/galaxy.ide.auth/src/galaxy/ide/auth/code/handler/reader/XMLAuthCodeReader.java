package galaxy.ide.auth.code.handler.reader;

import galaxy.ide.auth.Activator;
import galaxy.ide.auth.core.AuthCodeAction;
import galaxy.ide.auth.core.AuthCodeResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;

final class XMLAuthCodeReader implements IAuthCodeReader {

	public final static String CONFIG_PATH = "temp/permission_config.xml";
	private String projectName;
	private final Map<String, AuthCodeAction> actionCache = new HashMap<String, AuthCodeAction>();
	private final Map<String, AuthCodeResource> resourceCache = new HashMap<String, AuthCodeResource>();

	@Override
	public void setProject(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public Map<String, AuthCodeAction> getActionAuthCodeSet() {
		Assert.isNotNull(projectName);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			Object is = Platform.getBundle(Activator.BUNDLE_ID)
					.getEntry(CONFIG_PATH).getContent();
			doc = reader.read((FileInputStream) is);
			@SuppressWarnings("unchecked")
			List<Object> nodes = doc.selectNodes("//" + projectName
					+ "//actions//action");
			for (Object obj : nodes) {
				if (obj instanceof DefaultElement) {
					DefaultElement e = (DefaultElement) obj;
					Attribute id = e.attribute("id");
					Attribute name = e.attribute("name");
					Element value = e.element("value");
					if (id != null && name != null && value != null) {
						AuthCodeAction action = new AuthCodeAction();
						action.setId(id.getText());
						action.setName(name.getText());
						action.setValue(value.getText());
						actionCache.put(name.getText(), action);
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.resetHandlers();
			if (doc != null)
				doc.clearContent();
		}
		return actionCache;
	}

	@Override
	public Map<String, AuthCodeResource> getResourceAuthCodeSet() {
		Assert.isNotNull(projectName);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			Object is = Platform.getBundle(Activator.BUNDLE_ID)
					.getEntry(CONFIG_PATH).getContent();
			doc = reader.read((FileInputStream) is);
			@SuppressWarnings("unchecked")
			List<Object> nodes = doc.selectNodes("//" + projectName
					+ "//resources//resource");
			for (Object obj : nodes) {
				if (obj instanceof DefaultElement) {
					DefaultElement e = (DefaultElement) obj;
					Attribute id = e.attribute("id");
					Attribute ext = e.attribute("ext");
					Element value = e.element("value");
					if (id != null && ext != null && value != null) {
						AuthCodeResource resource = new AuthCodeResource();
						resource.setId(id.getText());
						resource.setType(ext.getText());
						resource.setValue(value.getText());
						resourceCache.put(ext.getText(), resource);
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.resetHandlers();
			if (doc != null)
				doc.clearContent();
		}
		return resourceCache;
	}

}
