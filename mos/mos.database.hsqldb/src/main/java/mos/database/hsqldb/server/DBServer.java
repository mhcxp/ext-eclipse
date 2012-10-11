package mos.database.hsqldb.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;
import java.util.TimerTask;

public class DBServer {
	public synchronized static void start() throws Exception {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				DatabaseThread db = new DatabaseThread();
				db.addDatabase("db/hsqldb");
				db.run();
			}
		}, 100);
	}

	public static void stop() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/hsqldb;ifexists=true", "sa", "");
		conn.createStatement().execute("shutdown");
		conn.close();
	}

}
