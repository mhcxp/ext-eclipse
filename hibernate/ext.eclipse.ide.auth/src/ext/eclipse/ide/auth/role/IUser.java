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
 * @date Nov 16, 2011 3:52:42 PM
 */
public interface IUser {

	String getId();

	String getName();

	void setName(String name);

	String getPassword();

	void setPassword(String password);

	Collection<IRole> getRoles();

	void addRole(IRole role);

	void removeRole(IRole role);

	boolean hasRole(IRole role);

	String getUserInfo(String key); // ��ȡ�û���Ϣ

	void putUserInfo(String key, String value);// �����û���Ϣ

	void setId(String id);

}
