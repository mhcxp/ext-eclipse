package ext.eclipse.hibernate.configurer;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import ext.eclipse.hibernate.configurer.constants.BeanConstants;

/**
 * 
 * @author caiyu
 * 
 */
public class IDGenerator implements IHbmConfigurer {
	private String clazz;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public Element toXML() {
		Element root = DocumentFactory.getInstance().createElement(
				BeanConstants.GENERATOR);
		root.addAttribute(BeanConstants.CLASS, clazz);
		return root;
	}

}
