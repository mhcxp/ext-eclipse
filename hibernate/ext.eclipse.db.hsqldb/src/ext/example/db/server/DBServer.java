package ext.example.db.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import ext.example.db.hsqldb.Activator;
import ext.example.db.hsqldb.manager.DatabaseThread;

public class DBServer {
	private static final String CONFIG_PATH = "resource/hsqldb.cfg.xml";

	public static synchronized void start() throws Exception {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				DatabaseThread db = new DatabaseThread();
				db.addDatabase("db/mydb");
				Display.getDefault().asyncExec(db);
			}
		}, 200);
	}

	// 关闭服务
	public static synchronized void stop() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/mydb;ifexists=true", "sa", "");
		conn.createStatement().execute("shutdown");
		conn.close();
	}

	public static Element getDBConfig() {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			doc = builder.parse(FileLocator.openStream(Activator.getContext()
					.getBundle(), new Path(CONFIG_PATH), false));
			return (Element) doc.getElementsByTagName("session-factory")
					.item(0);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

}
