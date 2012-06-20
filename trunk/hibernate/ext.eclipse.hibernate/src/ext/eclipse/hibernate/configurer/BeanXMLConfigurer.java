package ext.eclipse.hibernate.configurer;

import org.dom4j.Element;
/**
 * 
 * @author caiyu
 * 
 */
public class BeanXMLConfigurer implements IHbmConfigurer {
	private String clazz;
	private String path;
	private Element element;

	@Override
	public Element toXML() {
		// TODO Auto-generated method stub
		return element;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

}
