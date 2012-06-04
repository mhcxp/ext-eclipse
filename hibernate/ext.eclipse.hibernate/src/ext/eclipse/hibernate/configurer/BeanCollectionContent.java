package ext.eclipse.hibernate.configurer;

import org.dom4j.Element;

public class BeanCollectionContent implements IHbmConfigurer {
	public String[] attr;
	private final String KEY = "key";
	private final String MANY_TO_MANY = "many-to-many";
	private final String INDEX = "index";

	public BeanCollectionContent getInstance(String type) {
		BeanCollectionContent content = null;
		if (KEY.equals(type)) {
			content = new Key();
		} else if (MANY_TO_MANY.equals(type)) {
			content = new M2m();
		} else if (INDEX.equals(type)) {
			content = new Index();
		}
		return content;
	}

	public String getType(Class cls) {
		String type = null;
		if (cls == Key.class) {
			type = KEY;
		} else if (cls == M2m.class) {
			type = MANY_TO_MANY;
		} else if (cls == Index.class) {
			type = INDEX;
		}
		return type;
	}

	@Override
	public Element toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	class Key extends BeanCollectionContent {
		Key() {
			attr = new String[] {};
		}
	}

	class M2m extends BeanCollectionContent {

	}

	class Index extends BeanCollectionContent {

	}
}
