/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.auth.role;

import java.util.Collection;

/**
 * @author dzh
 * @date Nov 15, 2011 11:16:24 AM ½ÇÉ«
 */
public interface IRole {

	String getID();

	String getName();

	void setName(String name);

	String getDescription();

	void setDesciption(String description);

	Collection<IPermission> getPermissions();

	boolean addPerm(IPermission perm);

	boolean removePerm(IPermission perm);

	boolean cantainPerm(IPermission perm);
}
