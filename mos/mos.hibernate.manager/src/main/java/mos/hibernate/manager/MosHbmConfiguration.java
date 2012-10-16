package mos.hibernate.manager;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author caiyu
 * @date 2012-10-11 ÏÂÎç3:40:34
 */

public class MosHbmConfiguration extends AnnotationConfiguration {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4590539009267996973L;
	private static Log log = LogFactory.getLog(MosHbmConfiguration.class);

	public void print(Object obj) {

		System.out.println("\n\n\n=========\n\n\n" + obj
				+ "\n\n\n==========\n\n\n");
	}

	public Configuration addResource(String resourceName)
			throws MappingException {
		if (resourceName.startsWith("bundle")) {
			URL url;
			try {
				url = new URL(resourceName);

				return addInputStream(url.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.addResource(resourceName);
	}

	// protected void parseMappingElement(Element subelement, String name) {
	// Attribute rsrc = subelement.attribute("resource");
	// Attribute file = subelement.attribute("file");
	// Attribute jar = subelement.attribute("jar");
	// Attribute pkg = subelement.attribute("package");
	// if (rsrc != null) {
	// log.debug(name + "<-" + rsrc);
	// addResource(rsrc.getValue());
	// } else if (jar != null) {
	// log.debug(name + "<-" + jar);
	// addJar(new File(jar.getValue()));
	// } else if (pkg != null) {
	// throw new MappingException(
	// "An AnnotationConfiguration instance is required to use <mapping package=\""
	// + pkg.getValue() + "\"/>");
	// } else {
	// if (file == null) {
	// throw new MappingException(
	// "<mapping> element in configuration specifies no attributes");
	// }
	// log.debug(name + "<-" + file);
	// addFile(file.getValue());
	// }
	// }

	public Configuration configure(Document document) throws HibernateException {
		log.info("configuring from XML document");
		return doConfigure(document);
	}
}
