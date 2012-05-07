/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.derby.core.manager;

import galaxy.derby.core.DerbyConstants;
import galaxy.derby.core.util.SqlFileExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author dzh
 * @date Oct 27, 2011 11:39:09 AM
 */
class EmbeddedManager extends DerbyManager implements DerbyConstants {

	private Connection conn;

	private String derby_system_home;

	private String db_name;

	EmbeddedManager() {
		initManager();
	}

	private void initManager() {

	}

	/**
	 * 增加链接.若连接已经存在,则先关闭现有连接,再创建新的连接.
	 * 
	 * @param sysHome
	 * @param dbName
	 * @param info
	 */
	public Connection createConn(String sysHome, String dbName, Properties info) {
		if (conn != null)
			closeConn();
		this.db_name = dbName;
		this.derby_system_home = sysHome;
		System.setProperty(DERBY_SYSTEM_HOME, sysHome); // TODO
		try {
			Class.forName(DRIVER_EMBEDDED).newInstance();
			conn = DriverManager.getConnection(DERBY_PROTOCOL + dbName
					+ ";create=false", info);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver " + DRIVER_EMBEDDED
					+ " not found in CLASSPATH");
		} catch (SQLException e) {
			if (DerbyConstants.DERBY_ERR_DBNotFound.equals(e.getSQLState())) {
				initDB(dbName, info);
			} else
				e.printStackTrace();
		}

		return conn;
	}

	private void initDB(String dbName, Properties info) {
		try {
			// Class.forName(DRIVER_EMBEDDED).newInstance();
			conn = DriverManager.getConnection(DERBY_PROTOCOL + dbName
					+ ";create=true", info);
			SqlFileExecutor.execute(conn, "/sql/init_table.sql");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * get current connection,maybe it's null
	 * 
	 * @return
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * 当管理器不在使用时,释放
	 */
	public void close() {
		closeConn();
	}

	private void closeConn() {
		if (conn != null)
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public String getDerbyPath() {
		return this.derby_system_home;
	}

	@Override
	public String getDBName() {
		return db_name;
	}

}
