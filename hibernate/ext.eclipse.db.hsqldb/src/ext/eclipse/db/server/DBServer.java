package ext.eclipse.db.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Display;

import ext.eclipse.db.hsqldb.Activator;
import ext.eclipse.db.hsqldb.manager.DatabaseThread;

public class DBServer {
	private static final String CONFIG_PATH = "resource/hsqldb.cfg.xml";

	public synchronized void start() throws Exception {
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
	public synchronized void stop() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/mydb;ifexists=true", "sa", "");
		conn.createStatement().execute("shutdown");
		conn.close();
	}

	public static InputStream getDBConfig() throws IOException {
		return FileLocator.openStream(Activator.getDefault().getBundle(),
				new Path(CONFIG_PATH), false);
	}

}
