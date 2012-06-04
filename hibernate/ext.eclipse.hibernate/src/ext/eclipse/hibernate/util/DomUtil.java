package ext.eclipse.hibernate.util;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;

import ext.eclipse.hibernate.Activator;

public class DomUtil {
	public static Document getHbmXMLDocument(Element element) {
		Document doc = null;
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(FileLocator
					.openStream(Activator.getDefault().getBundle(), new Path(
							"resource/hibernate.hbm.xml"), false));
			Element sf = doc.getRootElement();
			for (Object e : element.elements()) {
				if (e instanceof Element) {
					sf.add((Element) ((Element) e).clone());
				}
			}
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return doc;
	}

	public static Document getCfgXMLDocument(Element element) {
		Document doc = null;
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(FileLocator
					.openStream(Activator.getDefault().getBundle(), new Path(
							"resource/hibernate.cfg.xml"), false));
			Element sf = doc.getRootElement().element("session-factory");
			for (Object e : element.elements()) {
				if (e instanceof Element) {
					sf.add((Element) ((Element) e).clone());
				}
			}
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return doc;
	}
}
