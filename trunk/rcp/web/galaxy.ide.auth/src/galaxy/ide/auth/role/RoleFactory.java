/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.auth.role;

import galaxy.ide.auth.role.impl.PCategoryImpl;
import galaxy.ide.auth.role.impl.PermissionImpl;
import galaxy.ide.auth.role.impl.RoleImpl;
import galaxy.ide.auth.role.impl.UserImpl;

/**
 * @author dzh
 * @date Nov 17, 2011 2:48:30 PM
 */
public class RoleFactory {

	public static final IRole newRole(String id) {
		return RoleImpl.newRole(id);
	}

	public static final IUser newUser(String id) {
		return UserImpl.newUser(id);
	}

	public static final IPCategory newCategory(String id) {
		return PCategoryImpl.newCategory(id);
	}

	public static final IPermission newPermission(String id) {
		return PermissionImpl.newPermission(id);
	}

}
