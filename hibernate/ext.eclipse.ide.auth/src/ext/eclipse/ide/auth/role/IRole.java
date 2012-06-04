/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package ext.eclipse.ide.auth.role;

import java.util.Collection;

/**
 * @author dzh
 * @date Nov 15, 2011 11:16:24 AM ��ɫ
 */
public interface IRole {

	String getId();

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	Collection<IPermission> getPermissions();

	boolean addPerm(IPermission perm);

	boolean removePerm(IPermission perm);

	boolean cantainPerm(IPermission perm);
}
