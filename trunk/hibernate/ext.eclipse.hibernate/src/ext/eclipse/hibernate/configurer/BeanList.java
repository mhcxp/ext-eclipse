package ext.eclipse.hibernate.configurer;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
/**
 * 
 * @author caiyu
 * 
 */
public class BeanList implements IHbmConfigurer {
	private String name;
	private String lazy;
	private String table;
	private List<BeanCollectionContent> contentList = new ArrayList<BeanCollectionContent>();

	public void addContent(BeanCollectionContent content) {
		contentList.add(content);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLazy() {
		return lazy;
	}

	public void setLazy(String lazy) {
		this.lazy = lazy;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@Override
	public Element toXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
