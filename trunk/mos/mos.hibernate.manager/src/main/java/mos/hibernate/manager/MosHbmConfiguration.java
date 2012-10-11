package mos.hibernate.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import mos.hibernate.manager.config.HibernateConfigFactory;
import mos.hibernate.manager.config.IHbmConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.hibernate.HibernateException;
import org.hibernate.InvalidMappingException;
import org.hibernate.MappingException;
import org.hibernate.cfg.Configuration;
import org.hibernate.util.ConfigHelper;

/**
 * @author caiyu
 * @date 2012-10-11 ÏÂÎç3:40:34
 */

public class MosHbmConfiguration extends Configuration {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4590539009267996973L;
	private static Log log = LogFactory.getLog(MosHbmConfiguration.class);

	protected InputStream getConfigurationInputStream(String resource)
			throws HibernateException {

		log.info("Configuration resource: " + resource);
		// try {
		// return FileLocator.openStream(Activator.getContext().getBundle(),
		// new Path(resource), false);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return ConfigHelper.getResourceAsStream(resource);
	}

	public Configuration addResource(String resourceName)
			throws MappingException {
		// try {
		//
		// return addInputStream(FileLocator.openStream(Activator.getContext()
		// .getBundle(), new Path(resourceName), false));
		// } catch (MappingException me) {
		// throw new InvalidMappingException("resource", resourceName, me);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return super.addResource(resourceName);
	}

	protected void parseMappingElement(Element subelement, String name) {
		Attribute rsrc = subelement.attribute("resource");
		Attribute file = subelement.attribute("file");
		Attribute jar = subelement.attribute("jar");
		Attribute pkg = subelement.attribute("package");
		Attribute clazz = subelement.attribute("class");
		if (rsrc != null) {
			log.debug(name + "<-" + rsrc);
			addResource(rsrc.getValue());
		} else if (jar != null) {
			log.debug(name + "<-" + jar);
			addJar(new File(jar.getValue()));
		} else if (pkg != null) {
			throw new MappingException(
					"An AnnotationConfiguration instance is required to use <mapping package=\""
							+ pkg.getValue() + "\"/>");
		} else if (clazz != null) {
			addPlugin(clazz.getValue());
		} else {
			if (file == null) {
				throw new MappingException(
						"<mapping> element in configuration specifies no attributes");
			}
			log.debug(name + "<-" + file);
			addFile(file.getValue());
		}
	}

	private void addPlugin(String sessionFactoryId) {
		// TODO Auto-generated method stub
		HbmConfigContainer config = HbmConfigContainerManager.getInstance()
				.getHbmConfigContainer(sessionFactoryId);
		if (config == null) {
			throw new MappingException(
					"<mapping> element in configuration specifies no attributes: "
							+ config);
		}

		// Document doc = DomUtil.getHbmXMLDocument(config.toXML());
		// System.out.println(doc.asXML());
		// add(doc);
	}

	public Configuration configure(Document document) throws HibernateException {
		log.info("configuring from XML document");
		return doConfigure(document);
	}
}
