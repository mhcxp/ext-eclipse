package ext.eclipse.hibernate.configurer;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import ext.eclipse.hibernate.configurer.constants.BeanConstants;

/**
 * 
 * @author caiyu
 * 
 */
public class BeanProperty implements IHbmConfigurer {
	private String name;
	private String type;
	private String column;
	private boolean isPrimaryKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public IDGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(IDGenerator generator) {
		this.generator = generator;
	}

	private IDGenerator generator;

	@Override
	public Element toXML() {
		String n = BeanConstants.PROPERTY;
		if (isPrimaryKey) {
			n = BeanConstants.ID;
		}
		Element root = DocumentFactory.getInstance().createElement(n);
		root.addAttribute(BeanConstants.NAME, name);
		if (type != null && !"".equals(type.trim())) {
			root.addAttribute(BeanConstants.TYPE, type);
		}
		if (column != null && !"".equals(column.trim())) {
			root.addAttribute(BeanConstants.COLUMN, column);
		}
		if (isPrimaryKey && generator != null) {
			root.add(generator.toXML());
		}
		return root;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

}
