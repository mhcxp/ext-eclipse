/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.auth.role;

import java.util.Collection;

import net.sf.json.JSONString;

/**
 * @author dzh
 * @date Nov 15, 2011 11:16:58 AM 权限分类
 */
public interface IPCategory extends JSONString{

	String getID();

	String getName();

	void setName(String name);

	String getDescription();

	void setDesciption(String description);

	Collection<IPermission> permissions();

	boolean addPerm(IPermission perm);

	boolean removePerm(IPermission perm);

	boolean containPerm(IPermission perm);
}
