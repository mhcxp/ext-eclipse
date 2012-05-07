/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.derby.core.manager;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author dzh
 * @date Oct 27, 2011 11:31:00 AM
 */
public abstract class DerbyManager {

	public static final int TYPE_EMBEDDED = 1;

	public static final int TYPE_NET = 2;

	public static final DerbyManager newInstance(int type) {
		// if (!isValidType(type))
		if (TYPE_EMBEDDED == type)
			return new EmbeddedManager();
		if (TYPE_NET == type)
			return new NetManager();

		throw new java.lang.IllegalArgumentException(
				"Can't create DerbyManager because the type(" + type + ")"
						+ " is invalid");
	}

	// private static boolean isValidType(int type) {
	// boolean valid = false;
	// switch (type) {
	// case TYPE_NET:
	// valid = true;
	// break;
	// case TYPE_EMBEDDED:
	// valid = true;
	// break;
	// }
	//
	// return valid;
	// }

	public abstract Connection createConn(String sysHome, String dbName,
			Properties info);

	public abstract Connection getConn();

	public abstract String getDBName();

	public abstract void close();

}
