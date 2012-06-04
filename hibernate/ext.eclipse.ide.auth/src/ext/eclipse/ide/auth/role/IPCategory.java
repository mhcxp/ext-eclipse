/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package ext.eclipse.ide.auth.role;

import java.util.Collection;

import net.sf.json.JSONString;

/**
 * @author dzh
 * @date Nov 15, 2011 11:16:58 AM Ȩ�޷���
 */
public interface IPCategory {

	String getId();

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	Collection<IPermission> permissions();

	boolean addPerm(IPermission perm);

	boolean removePerm(IPermission perm);

	boolean containPerm(IPermission perm);

	String toJSONString();
}
