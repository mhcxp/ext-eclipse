package ext.eclipse.hibernate.configurer;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import ext.eclipse.hibernate.configurer.constants.MappingConstants;

public class MappingConfigurer implements IHbmConfigurer {
	private String beanID;

	private String dbID;

	public String getBeanID() {
		return beanID;
	}

	public void setBeanID(String beanID) {
		this.beanID = beanID;
	}

	public String getDbID() {
		return dbID;
	}

	public void setDbID(String dbID) {
		this.dbID = dbID;
	}

	@Override
	public Element toXML() {
		Element element = DocumentFactory.getInstance()
				.createElement(MappingConstants.MAPPING)
				.addAttribute(MappingConstants.CLASS, beanID);
		return element;
	}
}
