package ext.eclipse.db.hsqldb.manager;

import java.util.ArrayList;
import java.util.List;

import org.hsqldb.Server;
import org.hsqldb.util.DatabaseManager;
import org.hsqldb.util.SqlTool;
import org.hsqldb.util.SqlTool.SqlToolException;

public class DatabaseThread implements Runnable {
	private String address = "localhost";
	private int port = 9001;
	private boolean silent = true;
	private boolean trace = true;
	private List<String> databases = new ArrayList<String>();
	private String[] dbnames;

	public void addDatabase(String path) {
		databases.add(path);
	}

	public void run() {
		System.out.println("\n------------------\n\nHSQLDB STARTING\n\n------------------\n");
		DatabaseManager.main(new String[] { "" });
		String[] args = null;
		ArrayList<String> list = new ArrayList<String>();
		if (address != null) {
			list.add("-address");
			list.add(address);
		}
		if (port != 0) {
			list.add("-port");
			list.add("" + port);
		}
		list.add("-silent");
		list.add("" + silent);
		list.add("-trace");
		list.add("" + trace);
		for (int i = 0; i < databases.size(); i++) {
			list.add("-database." + i);
			list.add((String) databases.get(i));
			list.add("-dbname." + i);
			String database = (String) databases.get(i);
			String dbname = database.substring(database.lastIndexOf("/") + 1);
			list.add(dbname);
		}
		args = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			args[i] = (String) list.get(i);
			System.out.println(args);
		}
		System.out.println("\n------------------\n\nHSQLDB STARTED\n\n------------------\n");
		Server.main(args);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isSilent() {
		return silent;
	}

	public void setSilent(boolean silent) {
		this.silent = silent;
	}

	public boolean isTrace() {
		return trace;
	}

	public void setTrace(boolean trace) {
		this.trace = trace;
	}

	public String[] getDbnames() {
		return dbnames;
	}

	public void setDbnames(String[] dbnames) {
		this.dbnames = dbnames;
	}
}