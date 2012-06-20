package ext.eclipse.hibernate.configurer;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import ext.eclipse.hibernate.configurer.constants.BeanConstants;
/**
 * 
 * @author caiyu
 * 
 */
public class BeanConfigurer implements IHbmConfigurer {
	private String clazz;
	private String table;
	private List<IHbmConfigurer> propertyList = new ArrayList<IHbmConfigurer>();

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void addProperty(IHbmConfigurer property) {
		propertyList.add(property);
	}

	public void removePropery(IHbmConfigurer property) {
		propertyList.remove(property);
	}

	@Override
	public Element toXML() {
		Element root = DocumentFactory.getInstance().createElement(
				"hibernate-mapping");
		Element cls = root.addElement(BeanConstants.CLASS)
				.addAttribute(BeanConstants.NAME, clazz)
				.addAttribute(BeanConstants.TABLE, table);
		for (IHbmConfigurer property : propertyList) {
			cls.add(property.toXML());
		}
		return root;
	}

}
